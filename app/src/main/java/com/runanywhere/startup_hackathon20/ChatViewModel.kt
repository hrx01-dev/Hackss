package com.runanywhere.startup_hackathon20

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.runanywhere.sdk.public.RunAnywhere
import com.runanywhere.sdk.public.extensions.listAvailableModels
import com.runanywhere.sdk.models.ModelInfo
import com.runanywhere.startup_hackathon20.database.ChatMessageEntity
import com.runanywhere.startup_hackathon20.database.ChatMessageRepository
import com.runanywhere.startup_hackathon20.database.MedicineDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Simple Message Data Class
data class ChatMessage(
    val id: Long = 0,
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

// ViewModel
class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChatMessageRepository
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _availableModels = MutableStateFlow<List<ModelInfo>>(emptyList())
    val availableModels: StateFlow<List<ModelInfo>> = _availableModels

    private val _downloadProgress = MutableStateFlow<Float?>(null)
    val downloadProgress: StateFlow<Float?> = _downloadProgress

    private val _currentModelId = MutableStateFlow<String?>(null)
    val currentModelId: StateFlow<String?> = _currentModelId

    private val _statusMessage = MutableStateFlow<String>("Initializing...")
    val statusMessage: StateFlow<String> = _statusMessage

    init {
        val database = MedicineDatabase.getDatabase(application)
        val chatMessageDao = database.chatMessageDao()
        repository = ChatMessageRepository(chatMessageDao)

        loadAvailableModels()
        loadMessagesFromDatabase()
    }

    private fun loadMessagesFromDatabase() {
        viewModelScope.launch {
            repository.recent20Messages.collect { dbMessages ->
                _messages.value = dbMessages.map { entity ->
                    ChatMessage(
                        id = entity.id,
                        text = entity.text,
                        isUser = entity.isUser,
                        timestamp = entity.timestamp
                    )
                }
            }
        }
    }

    private fun loadAvailableModels() {
        viewModelScope.launch {
            try {
                val models = listAvailableModels()
                _availableModels.value = models
                _statusMessage.value = "Ready - Please download and load a model"
            } catch (e: Exception) {
                _statusMessage.value = "Error loading models: ${e.message}"
            }
        }
    }

    fun downloadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Downloading model..."
                RunAnywhere.downloadModel(modelId).collect { progress ->
                    _downloadProgress.value = progress
                    _statusMessage.value = "Downloading: ${(progress * 100).toInt()}%"
                }
                _downloadProgress.value = null
                _statusMessage.value = "Download complete! Loading model..."

                // Automatically load the model after download
                loadModel(modelId)

                // Refresh the available models list to update download status
                loadAvailableModels()
            } catch (e: Exception) {
                _statusMessage.value = "Download failed: ${e.message}"
                _downloadProgress.value = null
            }
        }
    }

    fun loadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Loading model..."
                val success = RunAnywhere.loadModel(modelId)
                if (success) {
                    _currentModelId.value = modelId
                    _statusMessage.value = "Model loaded! Ready to chat."
                } else {
                    _statusMessage.value = "Failed to load model"
                }
            } catch (e: Exception) {
                _statusMessage.value = "Error loading model: ${e.message}"
            }
        }
    }

    fun sendMessage(text: String) {
        if (_currentModelId.value == null) {
            _statusMessage.value = "Please load a model first"
            return
        }

        viewModelScope.launch {
            // Save user message to database
            val userMessageEntity = ChatMessageEntity(
                text = text,
                isUser = true
            )
            repository.insertMessage(userMessageEntity)

            _isLoading.value = true

            try {
                // Generate response with streaming
                var assistantResponse = ""
                var assistantMessageId: Long? = null

                RunAnywhere.generateStream(text).collect { token ->
                    assistantResponse += token

                    // Save or update assistant message in database
                    if (assistantMessageId == null) {
                        // First token - create new assistant message
                        val assistantEntity = ChatMessageEntity(
                            text = assistantResponse,
                            isUser = false
                        )
                        assistantMessageId = repository.insertMessage(assistantEntity)
                    } else {
                        // Update existing assistant message
                        val assistantEntity = ChatMessageEntity(
                            id = assistantMessageId!!,
                            text = assistantResponse,
                            isUser = false
                        )
                        repository.insertMessage(assistantEntity)
                    }
                }
            } catch (e: Exception) {
                // Save error message to database
                val errorEntity = ChatMessageEntity(
                    text = "Error: ${e.message}",
                    isUser = false
                )
                repository.insertMessage(errorEntity)
            }

            _isLoading.value = false
        }
    }

    fun clearAllMessages() {
        viewModelScope.launch {
            repository.deleteAllMessages()
        }
    }

    fun refreshModels() {
        loadAvailableModels()
    }
}
