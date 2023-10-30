package com.imtiaz.retrofitwithflow.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imtiaz.retrofitwithflow.model.Post
import com.imtiaz.retrofitwithflow.repository.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            PostRepository.getPost()
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                /*Flow API is not lifeCycle aware, thats why i have converted into live data*/
                .collect{response->
                    responseLiveData.value = response

                }
        }
    }
}
