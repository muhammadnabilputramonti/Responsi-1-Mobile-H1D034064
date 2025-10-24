package com.example.responsi1mobileh1d023063.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.responsi1mobileh1d023063.R
import com.example.responsi1mobileh1d023063.databinding.FragmentHistoryBinding

/**
 * Fragment yang menampilkan sejarah lengkap klub Sunderland AFC.
 */
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFullHistory.text = getString(R.string.sunderland_club_history_full)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}