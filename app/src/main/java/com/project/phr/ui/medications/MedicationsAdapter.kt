package com.project.phr.ui.medications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.phr.databinding.MedicationItemBinding
import com.project.phr.model.Medication

class MedicationsAdapter(private val onClick: (Medication) -> Unit) :
    RecyclerView.Adapter<MedicationsAdapter.ViewHolder>() {

    private var medications: List<Medication> = emptyList()

    inner class ViewHolder(private val binding: MedicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(medication: Medication) {
            binding.missionTv.text = medication.name // Assuming 'name' is a property in Medication
            // If 'isFinished' or similar state exists in your Medication model, use it here. Otherwise, remove or adjust.
            binding.root.setOnClickListener { onClick(medications[bindingAdapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(MedicationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medications[position])
    }

    override fun getItemCount(): Int = medications.size

    fun setTasks(medications: List<Medication>) {
        this.medications = medications
        notifyDataSetChanged() // Reminder: for more efficient updates, consider using ListAdapter with DiffUtil
    }
}
