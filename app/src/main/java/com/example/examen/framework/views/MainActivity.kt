package com.example.examen

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.data.network.NetworkModuleDI
import com.example.examen.data.repository.CharacterRepository
import com.example.examen.domain.GetCharacters
import com.example.examen.presentation.characterAdapter
import com.example.examen.presentation.CharacterViewModel
import com.example.examen.presentation.CharacterViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory(GetCharacters(CharacterRepository(NetworkModuleDI.api)))
    }
    private lateinit var adapter: characterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        adapter = characterAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe characters LiveData
        viewModel.characters.observe(this, Observer { characters ->
            adapter.setCharacters(characters)
        })

        // Observe loading state LiveData
        viewModel.loading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Observe error LiveData
        viewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                // Display error message to the user (e.g., Toast or Snackbar)
            }
        })

        // Scroll listener for pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!viewModel.loading.value!! && totalItemCount <= (lastVisibleItem + 5)) {
                    // Load more characters when reaching near the end of the list
                    viewModel.fetchNextPage()
                }
            }
        })
    }
}