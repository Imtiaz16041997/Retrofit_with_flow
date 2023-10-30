package com.imtiaz.retrofitwithflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imtiaz.retrofitwithflow.adapter.PostAdapter
import com.imtiaz.retrofitwithflow.model.Post
import com.imtiaz.retrofitwithflow.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val TAG="main"
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel:PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.getPost()
        postViewModel.responseLiveData.observe(this, Observer {
            Log.d(TAG, "onCreate: ${it[0].body}")
            postAdapter.setPostData(it as ArrayList<Post>)
            progressBar.visibility= View.GONE
            recyclerView.visibility=View.VISIBLE
        })
    }

    private fun initUi(){
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        postAdapter = PostAdapter(this, ArrayList())

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}