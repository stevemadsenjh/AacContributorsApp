package com.example.yumemi.data.net

import com.example.yumemi.domain.model.Contributor
import retrofit2.http.GET

interface IGithubSvc {
    @GET("contributors")
    suspend fun contributors(): List<Contributor>
}
