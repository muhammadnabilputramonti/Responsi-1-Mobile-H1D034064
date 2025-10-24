package com.example.responsi1mobileh1d023063.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023063.databinding.FragmentSquadBinding // Pastikan nama package binding Anda benar
import com.example.responsi1mobileh1d023063.ui.TeamViewModel
import com.example.responsi1mobileh1d023063.ui.adapter.PlayerAdapter

class SquadFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()

    private var _binding: FragmentSquadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSquad.layoutManager = LinearLayoutManager(context)

        viewModel.teamData.observe(viewLifecycleOwner) { team ->
            val squad = team?.squad

            if (squad != null && squad.isNotEmpty()) {
                val playersWithPosition = squad.filter { it.position != null }

                val adapter = PlayerAdapter(playersWithPosition)
                binding.rvSquad.adapter = adapter
            } else if (team != null) {
                Toast.makeText(context, "Data Squad tidak ditemukan.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.rvSquad.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}