package com.example.githubusersearch.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.R
import com.example.githubusersearch.data.model.UserDetailDto
import com.example.githubusersearch.data.model.UserDto
import com.example.githubusersearch.databinding.ActivitySearchBinding
import com.example.githubusersearch.domain.NetworkResult
import com.example.githubusersearch.presentation.detail.DetailsActivity
import com.example.githubusersearch.util.hide
import com.example.githubusersearch.util.show
import kotlinx.coroutines.launch
import kotlin.jvm.java

class SearchActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels()

    private val adapter by lazy {
        UserAdapter(onUserClick = { user ->
            navigateToDetailsActivity(user)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpSearch()
        setUpObservers()
    }

    private fun setUpRecyclerView() {
        binding.userList.adapter = adapter
        binding.userList.setHasFixedSize(true)
        binding.userList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            }
        )
        binding.userList.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpSearch() {
        binding.searchButton.setOnClickListener {
            viewModel.searchUsers(binding.searchInput.text.toString())
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is NetworkResult.Loading -> {
                        binding.progressBar.show()
                        binding.userList.hide()
                        binding.errorMessage.hide()
                    }

                    is NetworkResult.Success -> {
                        binding.progressBar.hide()
                        binding.userList.show()
                        adapter.setUsers(state.data)
                        binding.errorMessage.hide()
                    }

                    is NetworkResult.Error -> {
                        binding.progressBar.hide()
                        binding.userList.hide()
                        binding.errorMessage.show()
                        binding.errorMessage.text = state.message
                    }

                    is NetworkResult.Empty -> {
                        binding.progressBar.hide()
                        binding.userList.hide()
                        binding.errorMessage.show()
                        binding.errorMessage.text = "No users found"
                    }

                }
            }
        }
    }

    fun navigateToDetailsActivity(user: UserDto) {
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra("username", user.username)
        })
    }
}