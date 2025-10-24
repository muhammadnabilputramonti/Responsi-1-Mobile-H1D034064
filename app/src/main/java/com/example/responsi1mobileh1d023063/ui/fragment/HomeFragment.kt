package com.example.responsi1mobileh1d023063.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.responsi1mobileh1d023063.R
import com.example.responsi1mobileh1d023063.databinding.FragmentHomeBinding
import com.example.responsi1mobileh1d023063.ui.TeamViewModel

class HomeFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigationListeners()

        viewModel.teamData.observe(viewLifecycleOwner) { team ->
            team?.let {
                binding.tvClubName.text = it.name

                val summaryText = "${it.shortName ?: it.tla} | Founded: ${it.founded ?: "N/A"}"
                binding.tvShortNameAndFounded.text = summaryText
                binding.tvVenue.text = it.venue ?: "N/A"

                binding.tvClubSummary.text = getString(R.string.sunderland_club_history)

            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.scrollView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupNavigationListeners() {
        binding.cardHeadCoach.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_coachFragment)
        }

        binding.cardTeamSquad.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_squadFragment)
        }

        binding.cardClubHistory.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_historyFragment)

            binding.scrollView.smoothScrollTo(0, binding.cardSummary.bottom)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}