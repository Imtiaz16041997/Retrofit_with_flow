package com.imtiaz.retrofitwithflow.network

import com.imtiaz.retrofitwithflow.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPost() : List<Post>
}