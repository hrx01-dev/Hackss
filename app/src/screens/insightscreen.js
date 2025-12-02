import { ArrowLeft, Pill, Clock, Calendar, User, FileText, Hash } from 'lucide-react';

interface InsightsScreenProps {
  onBack: () => void;
}

export function InsightsScreen({ onBack }: InsightsScreenProps) {
  // Mock medicine data - in a real app, this would come from storage
  const prescriptions = [
    {
      id: 'RX001',
      patientName: 'John Doe',
      date: 'Nov 30, 2025',
      medicines: [
        {
          name: 'Amoxicillin',
          dosage: '500mg',
          frequency: 'Three times a day',
          time: 'After meals',
          duration: '7 days',
          quantity: '21 tablets',
          instructions: 'Take with plenty of water. Complete the full course.'
        },
        {
          name: 'Paracetamol',
          dosage: '650mg',
          frequency: 'As needed',
          time: 'When required for fever',
          duration: '5 days',
          quantity: '10 tablets',
          instructions: 'Do not exceed 4 doses in 24 hours. Take with food if stomach upset occurs.'
        }
      ]
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
        <h1 className="text-white">Medical Insights</h1>
      </div>

      <div className="px-6 py-6">
        {prescriptions.map((prescription) => (
          <div key={prescription.id} className="bg-white dark:bg-gray-800 rounded-3xl shadow-lg p-6 mb-6 border-2 border-green-100 dark:border-green-900">
            {/* Prescription Header */}
            <div className="border-b-2 border-gray-100 dark:border-gray-700 pb-4 mb-6">
              <div className="flex items-center justify-between mb-4">
                <div>
                  <h2 className="text-gray-900 dark:text-white mb-1">MediInsight</h2>
                  <p className="text-gray-500 dark:text-gray-400 text-sm">Digital Prescription</p>
                </div>
                <div className="w-16 h-16 bg-gradient-to-br from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 rounded-2xl flex items-center justify-center">
                  <FileText className="w-8 h-8 text-white" />
                </div>
              </div>

              {/* Patient & Prescription Info */}
              <div className="grid grid-cols-2 gap-4 mt-4">
                <div className="flex items-start gap-2">
                  <User className="w-4 h-4 text-green-500 dark:text-green-400 mt-1 flex-shrink-0" />
                  <div>
                    <p className="text-gray-500 dark:text-gray-400 text-xs">Patient Name</p>
                    <p className="text-gray-900 dark:text-white">{prescription.patientName}</p>
                  </div>
                </div>
                <div className="flex items-start gap-2">
                  <Calendar className="w-4 h-4 text-green-500 dark:text-green-400 mt-1 flex-shrink-0" />
                  <div>
                    <p className="text-gray-500 dark:text-gray-400 text-xs">Date</p>
                    <p className="text-gray-900 dark:text-white">{prescription.date}</p>
                  </div>
                </div>
                <div className="flex items-start gap-2 col-span-2">
                  <Hash className="w-4 h-4 text-green-500 dark:text-green-400 mt-1 flex-shrink-0" />
                  <div>
                    <p className="text-gray-500 dark:text-gray-400 text-xs">Prescription ID</p>
                    <p className="text-gray-900 dark:text-white">{prescription.id}</p>
                  </div>
                </div>
              </div>
            </div>

            {/* Medicines List */}
            <div className="space-y-6">
              <h3 className="text-gray-900 dark:text-white flex items-center gap-2">
                <Pill className="w-5 h-5 text-green-500 dark:text-green-400" />
                Prescribed Medications
              </h3>

              {prescription.medicines.map((medicine, index) => (
                <div key={index} className="bg-gradient-to-br from-green-50 to-emerald-50 dark:from-green-900/20 dark:to-emerald-900/20 rounded-2xl p-5 border border-green-100 dark:border-green-900">
                  {/* Medicine Name & Dosage */}
                  <div className="flex items-start justify-between mb-4">
                    <div>
                      <h4 className="text-gray-900 dark:text-white mb-1">{medicine.name}</h4>
                      <p className="text-green-600 dark:text-green-400">{medicine.dosage}</p>
                    </div>
                    <div className="bg-white dark:bg-gray-700 px-3 py-1 rounded-full">
                      <span className="text-gray-700 dark:text-gray-300 text-sm">#{index + 1}</span>
                    </div>
                  </div>

                  {/* Medicine Details Grid */}
                  <div className="grid grid-cols-2 gap-3 mb-4">
                    <div className="bg-white dark:bg-gray-800 rounded-xl p-3">
                      <div className="flex items-center gap-2 mb-1">
                        <Clock className="w-3 h-3 text-green-500 dark:text-green-400" />
                        <p className="text-gray-500 dark:text-gray-400 text-xs">Frequency</p>
                      </div>
                      <p className="text-gray-900 dark:text-white text-sm">{medicine.frequency}</p>
                    </div>

                    <div className="bg-white dark:bg-gray-800 rounded-xl p-3">
                      <div className="flex items-center gap-2 mb-1">
                        <Clock className="w-3 h-3 text-green-500 dark:text-green-400" />
                        <p className="text-gray-500 dark:text-gray-400 text-xs">Time</p>
                      </div>
                      <p className="text-gray-900 dark:text-white text-sm">{medicine.time}</p>
                    </div>

                    <div className="bg-white dark:bg-gray-800 rounded-xl p-3">
                      <div className="flex items-center gap-2 mb-1">
                        <Calendar className="w-3 h-3 text-green-500 dark:text-green-400" />
                        <p className="text-gray-500 dark:text-gray-400 text-xs">Duration</p>
                      </div>
                      <p className="text-gray-900 dark:text-white text-sm">{medicine.duration}</p>
                    </div>

                    <div className="bg-white dark:bg-gray-800 rounded-xl p-3">
                      <div className="flex items-center gap-2 mb-1">
                        <Pill className="w-3 h-3 text-green-500 dark:text-green-400" />
                        <p className="text-gray-500 dark:text-gray-400 text-xs">Quantity</p>
                      </div>
                      <p className="text-gray-900 dark:text-white text-sm">{medicine.quantity}</p>
                    </div>
                  </div>

                  {/* Instructions */}
                  <div className="bg-white dark:bg-gray-800 rounded-xl p-3">
                    <div className="flex items-center gap-2 mb-2">
                      <FileText className="w-3 h-3 text-green-500 dark:text-green-400" />
                      <p className="text-gray-500 dark:text-gray-400 text-xs">Instructions</p>
                    </div>
                    <p className="text-gray-700 dark:text-gray-300 text-sm leading-relaxed">{medicine.instructions}</p>
                  </div>
                </div>
              ))}
            </div>

            {/* Footer */}
            <div className="mt-6 pt-4 border-t border-gray-100 dark:border-gray-700">
              <p className="text-gray-500 dark:text-gray-400 text-xs text-center">
                This is a digital prescription stored securely on your device.
              </p>
              <p className="text-gray-400 dark:text-gray-500 text-xs text-center mt-1">
                Generated by MediInsight Offline Expert System
              </p>
            </div>
          </div>
        ))}

        <div className="text-center mt-6">
          <p className="text-gray-500 dark:text-gray-400 text-sm">
            🔒 All prescriptions stored securely offline
          </p>
        </div>
      </div>
    </div>
  );
}