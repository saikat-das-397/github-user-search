package com.example.githubusersearch.presentation.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.githubusersearch.R
import com.example.githubusersearch.databinding.ActivityDetailsBinding
import com.example.githubusersearch.domain.NetworkResult
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
        val username = intent.getStringExtra("username")
        if (username != null) {
            viewModel.loadUserDetail(username)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is NetworkResult.Loading -> {
                        binding.avatar.setImageResource(R.drawable.ic_avatar)
                        binding.username.text = "" // Clear the username
                    }

                    is NetworkResult.Success -> {
                        binding.avatar.load(state.data.avatarUrl)
                        binding.username.text = state.data.username
                        binding.name.text = state.data.name ?: "No name"
                        binding.bio.text = state.data.bio ?: "No bio"
                        binding.followers.text = state.data.followers.toString()
                        binding.following.text = state.data.following.toString()
                        binding.publicRepos.text = state.data.publicRepos.toString()
                        binding.profileUrl.text = state.data.profileUrl
                    }

                    is NetworkResult.Error -> {
                        binding.avatar.setImageResource(R.drawable.ic_avatar)
                        binding.username.text = state.message
                    }

                    is NetworkResult.Empty -> {
                        binding.avatar.setImageResource(R.drawable.ic_avatar)
                        binding.username.text = "No user found"
                    }
                }
            }
        }
    }
}