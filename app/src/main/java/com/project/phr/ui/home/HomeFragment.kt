package com.project.phr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.phr.R
import com.project.phr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonAppointments?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appointmentsFragment2)
        }

        binding?.buttonMedications?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_medicationsFragment2)
        }

        binding?.buttonNotes?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notesFragment2)
        }

        binding?.buttonTestresults?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_testResultsFragment2)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
