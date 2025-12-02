import { ArrowLeft, Moon, Info, ChevronRight, Sun } from 'lucide-react';
import { useState } from 'react';

interface SettingsScreenProps {
  onBack: () => void;
  theme: 'light' | 'dark';
  onThemeChange: (theme: 'light' | 'dark') => void;
}

export function SettingsScreen({ onBack, theme, onThemeChange }: SettingsScreenProps) {
  const [showThemeSelector, setShowThemeSelector] = useState(false);

  const handleThemeChange = (newTheme: 'light' | 'dark') => {
    onThemeChange(newTheme);
    setShowThemeSelector(false);
  };

  const settingsItems = [
    {
      icon: Info,
      title: 'About App',
      subtitle: 'Version 1.0.0',
      color: 'bg-gray-500'
    }
  ];

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      <div className="bg-gradient-to-r from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white px-6 py-4 flex items-center gap-4 sticky top-0">
        <button
          onClick={onBack}
          className="w-10 h-10 bg-white/20 rounded-full flex items-center justify-center hover:bg-white/30 transition-colors"
        >
          <ArrowLeft className="w-5 h-5" />
        </button>
        <h1 className="text-white">Settings</h1>
      </div>

      <div className="px-6 py-6">
        {/* Theme Selector */}
        <div className="bg-white dark:bg-gray-800 rounded-2xl shadow-sm overflow-hidden mb-6">
          <button
            onClick={() => setShowThemeSelector(!showThemeSelector)}
            className="w-full flex items-center gap-4 p-5 hover:bg-gray-50 dark:hover:bg-gray-700 transition-colors"
          >
            <div className={`w-10 h-10 ${theme === 'light' ? 'bg-amber-500' : 'bg-indigo-600'} rounded-xl flex items-center justify-center flex-shrink-0`}>
              {theme === 'light' ? (
                <Sun className="w-5 h-5 text-white" />
              ) : (
                <Moon className="w-5 h-5 text-white" />
              )}
            </div>
            <div className="flex-1 text-left">
              <h3 className="text-gray-900 dark:text-white">Theme</h3>
              <p className="text-gray-500 dark:text-gray-400 text-sm">
                {theme === 'light' ? 'Light Mode' : 'Dark Mode'}
              </p>
            </div>
            <ChevronRight className={`w-5 h-5 text-gray-400 flex-shrink-0 transition-transform ${showThemeSelector ? 'rotate-90' : ''}`} />
          </button>

          {showThemeSelector && (
            <div className="px-5 pb-5 space-y-3">
              {/* Light Theme Option */}
              <button
                onClick={() => handleThemeChange('light')}
                className={`w-full p-4 rounded-xl border-2 transition-all ${
                  theme === 'light'
                    ? 'border-green-500 bg-green-50'
                    : 'border-gray-200 dark:border-gray-700 bg-white dark:bg-gray-700 hover:border-gray-300'
                }`}
              >
                <div className="flex items-center gap-3 mb-3">
                  <div className="w-10 h-10 bg-gradient-to-br from-green-400 to-emerald-500 rounded-lg flex items-center justify-center">
                    <Sun className="w-5 h-5 text-white" />
                  </div>
                  <div className="text-left">
                    <h4 className="text-gray-900 dark:text-white">Light Mode</h4>
                    <p className="text-gray-500 dark:text-gray-400 text-sm">Green & White</p>
                  </div>
                  {theme === 'light' && (
                    <div className="ml-auto w-6 h-6 bg-green-500 rounded-full flex items-center justify-center">
                      <svg className="w-4 h-4 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                      </svg>
                    </div>
                  )}
                </div>
                <div className="flex gap-2">
                  <div className="flex-1 h-8 bg-gradient-to-r from-green-100 to-emerald-100 rounded"></div>
                  <div className="flex-1 h-8 bg-white border border-gray-200 rounded"></div>
                </div>
              </button>

              {/* Dark Theme Option */}
              <button
                onClick={() => handleThemeChange('dark')}
                className={`w-full p-4 rounded-xl border-2 transition-all ${
                  theme === 'dark'
                    ? 'border-indigo-500 bg-indigo-50 dark:bg-indigo-900/30'
                    : 'border-gray-200 dark:border-gray-700 bg-white dark:bg-gray-700 hover:border-gray-300'
                }`}
              >
                <div className="flex items-center gap-3 mb-3">
                  <div className="w-10 h-10 bg-gradient-to-br from-slate-700 to-indigo-900 rounded-lg flex items-center justify-center">
                    <Moon className="w-5 h-5 text-white" />
                  </div>
                  <div className="text-left">
                    <h4 className="text-gray-900 dark:text-white">Dark Mode</h4>
                    <p className="text-gray-500 dark:text-gray-400 text-sm">Slate & Indigo</p>
                  </div>
                  {theme === 'dark' && (
                    <div className="ml-auto w-6 h-6 bg-indigo-500 rounded-full flex items-center justify-center">
                      <svg className="w-4 h-4 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                      </svg>
                    </div>
                  )}
                </div>
                <div className="flex gap-2">
                  <div className="flex-1 h-8 bg-gradient-to-r from-slate-800 to-slate-900 rounded"></div>
                  <div className="flex-1 h-8 bg-indigo-900 rounded"></div>
                </div>
              </button>
            </div>
          )}
        </div>

        {/* Other Settings */}
        <div className="bg-white dark:bg-gray-800 rounded-2xl shadow-sm overflow-hidden">
          {settingsItems.map((item, index) => {
            const Icon = item.icon;
            return (
              <button
                key={index}
                className={`w-full flex items-center gap-4 p-5 hover:bg-gray-50 dark:hover:bg-gray-700 transition-colors ${
                  index !== settingsItems.length - 1 ? 'border-b border-gray-100 dark:border-gray-700' : ''
                }`}
              >
                <div className={`w-10 h-10 ${item.color} rounded-xl flex items-center justify-center flex-shrink-0`}>
                  <Icon className="w-5 h-5 text-white" />
                </div>
                <div className="flex-1 text-left">
                  <h3 className="text-gray-900 dark:text-white">{item.title}</h3>
                  <p className="text-gray-500 dark:text-gray-400 text-sm">{item.subtitle}</p>
                </div>
                <ChevronRight className="w-5 h-5 text-gray-400 flex-shrink-0" />
              </button>
            );
          })}
        </div>

        <div className="mt-8 text-center">
          <p className="text-gray-500 dark:text-gray-400 text-sm mb-2">MediInsight</p>
          <p className="text-gray-400 dark:text-gray-500 text-xs">All data stored securely on your device</p>
        </div>
      </div>
    </div>
  );
}