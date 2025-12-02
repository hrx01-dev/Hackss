import { Wifi, Zap, Shield, ChevronRight } from 'lucide-react';
import { useState } from 'react';

interface OnboardingScreensProps {
  onComplete: () => void;
}

export function OnboardingScreens({ onComplete }: OnboardingScreensProps) {
  const [currentScreen, setCurrentScreen] = useState(0);

  const screens = [
    {
      icon: Wifi,
      title: 'Works 100% Offline',
      description: 'Access expert guidance anytime, anywhere. No internet connection required.',
      color: 'from-green-400 to-emerald-500'
    },
    {
      icon: Zap,
      title: 'Instant Expert Guidance',
      description: 'Get immediate answers and insights powered by advanced AI technology.',
      color: 'from-teal-400 to-cyan-500'
    },
    {
      icon: Shield,
      title: 'Safe, Private, Always Available',
      description: 'Your data stays on your device. Complete privacy and security guaranteed.',
      color: 'from-green-500 to-teal-500'
    }
  ];

  const handleNext = () => {
    if (currentScreen < screens.length - 1) {
      setCurrentScreen(currentScreen + 1);
    } else {
      onComplete();
    }
  };

  const screen = screens[currentScreen];
  const Icon = screen.icon;

  return (
    <div className="min-h-screen bg-white flex flex-col">
      <div className="flex-1 flex flex-col items-center justify-center px-6 py-12">
        <div className={`w-32 h-32 bg-gradient-to-br ${screen.color} rounded-full flex items-center justify-center shadow-xl mb-8`}>
          <Icon className="w-16 h-16 text-white" />
        </div>

        <h2 className="text-gray-900 text-center mb-4">{screen.title}</h2>
        <p className="text-gray-600 text-center max-w-sm">{screen.description}</p>
      </div>

      <div className="px-6 pb-12">
        <div className="flex gap-2 justify-center mb-8">
          {screens.map((_, index) => (
            <div
              key={index}
              className={`h-2 rounded-full transition-all ${
                index === currentScreen ? 'w-8 bg-green-500' : 'w-2 bg-gray-300'
              }`}
            />
          ))}
        </div>

        <button
          onClick={handleNext}
          className="w-full bg-gradient-to-r from-green-400 to-emerald-500 text-white py-4 rounded-2xl flex items-center justify-center gap-2 hover:shadow-lg transition-shadow"
        >
          {currentScreen === screens.length - 1 ? 'Get Started' : 'Next'}
          <ChevronRight className="w-5 h-5" />
        </button>

        {currentScreen < screens.length - 1 && (
          <button
            onClick={onComplete}
            className="w-full text-gray-500 py-4 mt-2"
          >
            Skip
          </button>
        )}
      </div>
    </div>
  );
}
