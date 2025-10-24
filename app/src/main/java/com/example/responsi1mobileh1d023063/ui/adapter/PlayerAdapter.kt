package com.example.responsi1mobileh1d023063.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023063.R
import com.example.responsi1mobileh1d023063.databinding.ItemPlayerBinding
import com.example.responsi1mobileh1d023063.model.Player

class PlayerAdapter(private val playerList: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.tvPlayerName.text = player.name
            binding.tvPlayerPosition.text = player.position

            val colorResId = when (player.position) {

                // === KATEGORI FORWARD (MERAH)
                "Offence", "Centre-Forward", "Left Winger", "Right Winger",
                "Striker", "Secondary Striker", "Forward" ->
                    R.color.color_forward

                // === KATEGORI MIDFIELDER (HIJAU)
                "Midfield", "Central Midfield", "Attacking Midfield", "Defensive Midfield",
                "Left Midfield", "Right Midfield", "Central Attacking Midfield" ->
                    R.color.color_midfielder

                // === KATEGORI DEFENDER (BIRU)
                "Defence", "Centre-Back", "Right-Back", "Left-Back", "Full-Back", "Wing Back" ->
                    R.color.color_defender

                // === KATEGORI GOALKEEPER (KUNING)
                "Goalkeeper" ->
                    R.color.color_goalkeeper

                else ->
                    R.color.color_unknown
            }

            val color = ContextCompat.getColor(binding.root.context, colorResId)
            binding.cardPlayer.setCardBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount(): Int = playerList.size
}