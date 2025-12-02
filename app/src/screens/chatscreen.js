import { Send, ArrowLeft, WifiOff } from 'lucide-react';
import { useState } from 'react';

interface ChatScreenProps {
  onBack: () => void;
}

interface Message {
  text: string;
  isUser: boolean;
}

export function ChatScreen({ onBack }: ChatScreenProps) {
  const [messages, setMessages] = useState<Message[]>([
    { text: 'Hello! I\'m your offline expert assistant. How can I help you today?', isUser: false }
  ]);
  const [inputText, setInputText] = useState('');

  const suggestions = [
    'Give me a tip',
    'Explain this',
    'Best practices',
    'How to improve'
  ];

  const handleSend = () => {
    if (!inputText.trim()) return;

    setMessages([...messages,
      { text: inputText, isUser: true },
      { text: 'I understand your question. Here\'s my expert advice based on my offline knowledge...', isUser: false }
    ]);
    setInputText('');
  };

  const handleSuggestion = (suggestion: string) => {
    setMessages([...messages,
      { text: suggestion, isUser: true },
      { text: 'Great question! Let me provide you with expert guidance on this topic...', isUser: false }
    ]);
  };

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 flex flex-col">
      <div className="bg-gradient-to-r from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white px-6 py-4 flex items-center gap-4">
        <button
          onClick={onBack}
          className="w-10 h-10 bg-white/20 rounded-full flex items-center justify-center hover:bg-white/30 transition-colors"
        >
          <ArrowLeft className="w-5 h-5" />
        </button>
        <div className="flex-1">
          <h1 className="text-white">Expert Chat</h1>
          <div className="flex items-center gap-1 text-sm opacity-90">
            <WifiOff className="w-3 h-3" />
            <span>Offline</span>
          </div>
        </div>
      </div>

      <div className="flex-1 overflow-y-auto px-6 py-6 space-y-4">
        {messages.map((message, index) => (
          <div key={index} className={`flex ${message.isUser ? 'justify-end' : 'justify-start'}`}>
            <div className={`max-w-[80%] p-4 rounded-2xl ${
              message.isUser
                ? 'bg-gradient-to-br from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white'
                : 'bg-white dark:bg-gray-800 text-gray-900 dark:text-white shadow-sm'
            }`}>
              <p className="text-sm leading-relaxed">{message.text}</p>
            </div>
          </div>
        ))}

        {messages.length <= 1 && (
          <div className="space-y-3 mt-6">
            <p className="text-gray-500 dark:text-gray-400 text-sm text-center mb-4">Quick suggestions:</p>
            <div className="grid grid-cols-2 gap-2">
              {suggestions.map((suggestion, index) => (
                <button
                  key={index}
                  onClick={() => handleSuggestion(suggestion)}
                  className="bg-white dark:bg-gray-800 text-gray-700 dark:text-gray-300 px-4 py-3 rounded-xl text-sm hover:shadow-md transition-shadow border border-gray-200 dark:border-gray-700"
                >
                  {suggestion}
                </button>
              ))}
            </div>
          </div>
        )}
      </div>

      <div className="p-4 bg-white dark:bg-gray-800 border-t border-gray-200 dark:border-gray-700">
        <div className="flex gap-2">
          <input
            type="text"
            value={inputText}
            onChange={(e) => setInputText(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && handleSend()}
            placeholder="Type your question..."
            className="flex-1 bg-gray-100 dark:bg-gray-700 text-gray-900 dark:text-white px-4 py-3 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-400 dark:focus:ring-green-600 placeholder-gray-500 dark:placeholder-gray-400"
          />
          <button
            onClick={handleSend}
            className="bg-gradient-to-br from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white w-12 h-12 rounded-xl flex items-center justify-center hover:shadow-lg transition-shadow"
          >
            <Send className="w-5 h-5" />
          </button>
        </div>
      </div>
    </div>
  );
}