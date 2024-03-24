
package com.project.phr.ui.medications

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.phr.databinding.FragmentAddMedicationDialogBinding
import com.project.phr.model.Medication

class AddMedicationDialogFragment : DialogFragment() {

    interface AddMedicationDialogListener {
        fun onAddMedication(medication: Medication)
    }

    var listener: AddMedicationDialogListener? = null

    private var _binding: FragmentAddMedicationDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAddMedicationDialogBinding.inflate(layoutInflater)

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Medication")
            .setView(binding.root)
            .setPositiveButton("Add") { dialog, which ->
                val name = binding.medicationNameEditText.text.toString()
                val dosage = binding.editTextMedicationDosage.text.toString()
                val frequency = binding.editTextMedicationFrequency.text.toString()


                if (name.isNotEmpty() && dosage.isNotEmpty() && frequency.isNotEmpty()) {
                    val medication = Medication(name = name, dosage = dosage, frequency = frequency)
                    listener?.onAddMedication(medication)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


