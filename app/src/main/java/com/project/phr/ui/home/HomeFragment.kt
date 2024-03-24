package com.project.phr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.phr.R
import com.project.phr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming you have set the IDs for the CardViews in your XML
        binding.HomeCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appointmentsFragment2)
        }

        binding.medicationcard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_medicationsFragment2)
        }

        binding.Notescard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notesFragment2)
        }

        binding.Testresultscard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_testResultsFragment2)
        }

        binding.historycard.setOnClickListener {


        }

        binding.Orivewcard.setOnClickListener {
            // Navigate to the corresponding Fragment for "Orview"
            // Assuming you have the navigation action defined in your nav_graph.xml
            // For example: findNavController().navigate(R.id.action_homeFragment_to_orviewFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
