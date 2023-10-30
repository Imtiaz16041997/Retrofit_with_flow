package com.imtiaz.retrofitwithflow.repository

import com.imtiaz.retrofitwithflow.model.Post
import com.imtiaz.retrofitwithflow.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository {

    companion object{
        fun getPost(): Flow<List<Post>> = flow{
            val response = RetrofitBuilder.api.getPost()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}