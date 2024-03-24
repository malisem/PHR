package com.project.phr.ui.medications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.phr.R
import com.project.phr.databinding.MedicationItemBinding
import com.project.phr.model.Medication

class MedicationsAdapter(private val onClickDelete: (String) -> Unit) :
    ListAdapter<Medication, MedicationsAdapter.ViewHolder>(MedicationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MedicationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medication = getItem(position)
        holder.bind(medication)
    }

    class ViewHolder(private val binding: MedicationItemBinding,
                     private val onClickDelete: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(medication: Medication) {
            binding.medicationNameTextView.text = medication.name
            // Assuming you have an ImageView or a Button for deletion in your MedicationItemBinding
            binding.deleteButton.setOnClickListener {
                onClickDelete(medication.id)
            }
        }
    }
}

class MedicationDiffCallback : DiffUtil.ItemCallback<Medication>() {
    override fun areItemsTheSame(oldItem: Medication, newItem: Medication): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Medication, newItem: Medication): Boolean =
        oldItem == newItem
}
