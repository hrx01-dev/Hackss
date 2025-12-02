import { User, Lock, Mail } from 'lucide-react';
import { useState } from 'react';

interface AuthScreenProps {
  onComplete: () => void;
}

export function AuthScreen({ onComplete }: AuthScreenProps) {
  const [activeTab, setActiveTab] = useState<'login' | 'signup'>('login');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onComplete();
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-emerald-50 flex items-center justify-center px-6 relative overflow-hidden">
      {/* Floating Bubbles */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute w-20 h-20 bg-green-200/30 rounded-full top-[10%] left-[10%] animate-float-slow"></div>
        <div className="absolute w-32 h-32 bg-emerald-200/20 rounded-full top-[20%] right-[15%] animate-float-medium"></div>
        <div className="absolute w-24 h-24 bg-teal-200/25 rounded-full bottom-[30%] left-[5%] animate-float-fast"></div>
        <div className="absolute w-16 h-16 bg-green-300/30 rounded-full bottom-[15%] right-[20%] animate-float-slow"></div>
        <div className="absolute w-28 h-28 bg-emerald-100/40 rounded-full top-[50%] left-[20%] animate-float-medium"></div>
        <div className="absolute w-20 h-20 bg-teal-200/20 rounded-full top-[70%] right-[10%] animate-float-fast"></div>
        <div className="absolute w-36 h-36 bg-green-100/25 rounded-full bottom-[40%] right-[5%] animate-float-slow"></div>
      </div>

      <div className="w-full max-w-md relative z-10">
        <div className="text-center mb-8">
          <h1 className="text-gray-900 mb-2">Welcome Back</h1>
          <p className="text-gray-600">Sign in to continue</p>
        </div>

        <div className="bg-white rounded-3xl shadow-xl p-6">
          <div className="flex gap-2 mb-6 bg-gray-100 p-1 rounded-xl">
            <button
              onClick={() => setActiveTab('login')}
              className={`flex-1 py-3 rounded-lg transition-all ${
                activeTab === 'login'
                  ? 'bg-white text-green-600 shadow'
                  : 'text-gray-600'
              }`}
            >
              Login
            </button>
            <button
              onClick={() => setActiveTab('signup')}
              className={`flex-1 py-3 rounded-lg transition-all ${
                activeTab === 'signup'
                  ? 'bg-white text-green-600 shadow'
                  : 'text-gray-600'
              }`}
            >
              Signup
            </button>
          </div>

          <form onSubmit={handleSubmit}>
            {activeTab === 'signup' && (
              <div className="mb-4">
                <label className="block text-gray-700 mb-2">Name</label>
                <div className="relative">
                  <User className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                  <input
                    type="text"
                    placeholder="Enter your name"
                    className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:border-green-500 transition-colors"
                  />
                </div>
              </div>
            )}

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Username</label>
              <div className="relative">
                <Mail className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                <input
                  type="text"
                  placeholder="Enter your username"
                  className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:border-blue-500 transition-colors"
                />
              </div>
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Password</label>
              <div className="relative">
                <Lock className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                <input
                  type="password"
                  placeholder="Enter your password"
                  className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:border-blue-500 transition-colors"
                />
              </div>
            </div>

            {activeTab === 'signup' && (
              <div className="mb-4">
                <label className="block text-gray-700 mb-2">Confirm Password</label>
                <div className="relative">
                  <Lock className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                  <input
                    type="password"
                    placeholder="Confirm your password"
                    className="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:border-green-500 transition-colors"
                  />
                </div>
              </div>
            )}

            <button
              type="submit"
              className="w-full bg-gradient-to-r from-green-400 to-emerald-500 text-white py-4 rounded-xl hover:shadow-lg transition-shadow mt-6"
            >
              {activeTab === 'login' ? 'Login' : 'Create Account'}
            </button>
          </form>

          <p className="text-gray-500 text-center mt-6 text-sm">
            🔒 Data stored securely offline
          </p>
        </div>
      </div>
    </div>
  );
}
