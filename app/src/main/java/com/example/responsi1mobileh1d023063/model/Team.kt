package com.example.responsi1mobileh1d023063.model

data class TeamResponse(
    val id: Int,
    val name: String,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,

    val coach: Coach?,

    val squad: List<Player>?
)

data class Coach(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val nationality: String?
)
data class Player(
    val id: Int,
    val name: String,
    val position: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val shirtNumber: Int?
)