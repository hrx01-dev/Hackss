import { ArrowLeft, Lightbulb, BookOpen, TrendingUp, Bell } from 'lucide-react';

interface NotificationsScreenProps {
  onBack: () => void;
}

export function NotificationsScreen({ onBack }: NotificationsScreenProps) {
  const insights = [
    {
      icon: Lightbulb,
      title: 'Daily Tip: Morning Routine',
      description: 'Start your day with a 5-minute planning session to boost productivity by up to 30%.',
      time: '8:00 AM',
      color: 'from-green-300 to-emerald-400'
    },
    {
      icon: BookOpen,
      title: 'New Guide Available',
      description: 'Check out our latest guide on effective time management strategies.',
      time: '2 hours ago',
      color: 'from-teal-300 to-cyan-400'
    },
    {
      icon: TrendingUp,
      title: 'Weekly Progress Update',
      description: 'You\'ve completed 12 expert sessions this week. Keep up the great work!',
      time: '5 hours ago',
      color: 'from-green-400 to-teal-500'
    },
    {
      icon: Bell,
      title: 'Reminder: Daily Check-in',
      description: 'Don\'t forget to log your daily achievements and reflections.',
      time: 'Yesterday',
      color: 'from-emerald-400 to-green-500'
    },
    {
      icon: Lightbulb,
      title: 'Expert Insight',
      description: 'Taking regular breaks can improve focus and reduce mental fatigue.',
      time: '2 days ago',
      color: 'from-green-300 to-emerald-400'
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
        <h1 className="text-white">Daily Insights</h1>
      </div>

      <div className="px-6 py-6 space-y-4">
        {insights.map((insight, index) => {
          const Icon = insight.icon;
          return (
            <div key={index} className="bg-white dark:bg-gray-800 rounded-2xl shadow-sm p-5 hover:shadow-md transition-shadow">
              <div className="flex gap-4">
                <div className={`w-12 h-12 bg-gradient-to-br ${insight.color} rounded-xl flex items-center justify-center flex-shrink-0`}>
                  <Icon className="w-6 h-6 text-white" />
                </div>
                <div className="flex-1">
                  <h3 className="text-gray-900 dark:text-white mb-1">{insight.title}</h3>
                  <p className="text-gray-600 dark:text-gray-400 text-sm mb-2">{insight.description}</p>
                  <span className="text-gray-400 dark:text-gray-500 text-xs">{insight.time}</span>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}