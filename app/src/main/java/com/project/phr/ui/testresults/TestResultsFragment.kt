package com.project.phr.ui.testresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.phr.databinding.FragmentTestResultsBinding

class TestResultsFragment : Fragment() {
    private var binding: FragmentTestResultsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestResultsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup RecyclerView, bind ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
