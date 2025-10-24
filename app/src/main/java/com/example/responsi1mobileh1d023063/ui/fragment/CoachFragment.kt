package com.example.responsi1mobileh1d023063.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.responsi1mobileh1d023063.databinding.FragmentCoachBinding
import com.example.responsi1mobileh1d023063.ui.TeamViewModel

class CoachFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()
    private var _binding: FragmentCoachBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.teamData.observe(viewLifecycleOwner) { team ->
            val coach = team?.coach
            coach?.let {
                val fullName = "${it.firstName ?: ""} ${it.lastName ?: ""}".trim()

                binding.tvCoachName.text = fullName
                binding.tvCoachDOB.text = it.dateOfBirth ?: "N/A"
                binding.tvCoachNationality.text = it.nationality ?: "N/A"

                binding.contentLayout.visibility = View.VISIBLE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.contentLayout.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}