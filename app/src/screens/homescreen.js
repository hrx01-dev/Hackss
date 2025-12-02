import { ArrowLeft, Pill, Clock, Calendar, Repeat, FileText, Package } from 'lucide-react';
import { useState } from 'react';

interface AddMedicineScreenProps {
  onBack: () => void;
}

export function AddMedicineScreen({ onBack }: AddMedicineScreenProps) {
  const [formData, setFormData] = useState({
    medicineName: '',
    dosage: '',
    frequency: '',
    time: '',
    duration: '',
    quantity: '',
    instructions: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle form submission
    alert('Medicine added successfully!');
    onBack();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      <div className="bg-gradient-to-r from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white px-6 py-4 flex items-center gap-4 sticky top-0">
        <button
          onClick={onBack}
          className="w-10 h-10 bg-white/20 rounded-full flex items-center justify-center hover:bg-white/30 transition-colors"
        >
          <ArrowLeft className="w-5 h-5" />
        </button>
        <h1 className="text-white">Add Medicine</h1>
      </div>

      <div className="px-6 py-6">
        <form onSubmit={handleSubmit} className="space-y-5">
          {/* Medicine Name */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Medicine Name</label>
            <div className="relative">
              <Pill className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <input
                type="text"
                name="medicineName"
                value={formData.medicineName}
                onChange={handleChange}
                placeholder="Enter medicine name"
                required
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors placeholder-gray-500 dark:placeholder-gray-400"
              />
            </div>
          </div>

          {/* Dosage */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Dosage</label>
            <input
              type="text"
              name="dosage"
              value={formData.dosage}
              onChange={handleChange}
              placeholder="e.g., 500mg, 1 tablet"
              required
              className="w-full px-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors placeholder-gray-500 dark:placeholder-gray-400"
            />
          </div>

          {/* Frequency */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Frequency</label>
            <div className="relative">
              <Repeat className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <select
                name="frequency"
                value={formData.frequency}
                onChange={handleChange}
                required
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors appearance-none"
              >
                <option value="">Select frequency</option>
                <option value="once">Once a day</option>
                <option value="twice">Twice a day</option>
                <option value="thrice">Three times a day</option>
                <option value="asneeded">As needed</option>
              </select>
            </div>
          </div>

          {/* Time */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Time to Take</label>
            <div className="relative">
              <Clock className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <select
                name="time"
                value={formData.time}
                onChange={handleChange}
                required
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors appearance-none"
              >
                <option value="">Select time</option>
                <option value="morning">Morning</option>
                <option value="afternoon">Afternoon</option>
                <option value="evening">Evening</option>
                <option value="night">Night</option>
                <option value="beforemeals">Before meals</option>
                <option value="aftermeals">After meals</option>
              </select>
            </div>
          </div>

          {/* Duration */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Duration</label>
            <div className="relative">
              <Calendar className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <input
                type="text"
                name="duration"
                value={formData.duration}
                onChange={handleChange}
                placeholder="e.g., 7 days, 2 weeks"
                required
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors placeholder-gray-500 dark:placeholder-gray-400"
              />
            </div>
          </div>

          {/* Quantity */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Quantity</label>
            <div className="relative">
              <Package className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <input
                type="text"
                name="quantity"
                value={formData.quantity}
                onChange={handleChange}
                placeholder="e.g., 30 tablets"
                required
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors placeholder-gray-500 dark:placeholder-gray-400"
              />
            </div>
          </div>

          {/* Instructions */}
          <div>
            <label className="block text-gray-700 dark:text-gray-300 mb-2">Special Instructions</label>
            <div className="relative">
              <FileText className="absolute left-4 top-4 w-5 h-5 text-gray-400 dark:text-gray-500" />
              <textarea
                name="instructions"
                value={formData.instructions}
                onChange={handleChange}
                placeholder="Any special instructions..."
                rows={4}
                className="w-full pl-12 pr-4 py-3 bg-white dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-200 dark:border-gray-700 rounded-xl focus:outline-none focus:border-green-500 dark:focus:border-green-600 transition-colors resize-none placeholder-gray-500 dark:placeholder-gray-400"
              />
            </div>
          </div>

          {/* Submit Button */}
          <button
            type="submit"
            className="w-full bg-gradient-to-r from-green-400 to-emerald-500 dark:from-green-600 dark:to-emerald-700 text-white py-4 rounded-xl hover:shadow-lg transition-shadow"
          >
            Add Medicine
          </button>
        </form>
      </div>
    </div>
  );
}