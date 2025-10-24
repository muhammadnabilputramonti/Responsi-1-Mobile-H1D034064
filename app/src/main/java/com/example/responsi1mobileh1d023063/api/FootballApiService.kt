package com.example.responsi1mobileh1d023063.api

import com.example.responsi1mobileh1d023063.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApiService {

    @GET("v4/teams/{id-klub}")
    suspend fun getTeamDetails(
        @Path("id-klub") teamId: Int = 71
    ): Response<TeamResponse>
}
