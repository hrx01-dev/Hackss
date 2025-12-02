import { Lightbulb } from 'lucide-react';

interface SplashScreenProps {
  onComplete: () => void;
}

export function SplashScreen({ onComplete }: SplashScreenProps) {
  // Auto-advance after 2 seconds
  setTimeout(() => {
    onComplete();
  }, 2000);

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-green-50 via-emerald-50 to-teal-50">
      <div className="flex flex-col items-center gap-6 animate-fade-in">
        <div className="w-24 h-24 bg-gradient-to-br from-green-400 to-emerald-500 rounded-3xl flex items-center justify-center shadow-lg">
          <Lightbulb className="w-12 h-12 text-white" />
        </div>

        <div className="text-center">
          <h1 className="text-gray-900 mb-2">MediInsight</h1>
          <p className="text-gray-600">Your Offline Expert Companion</p>
        </div>
      </div>
    </div>
  );
}
