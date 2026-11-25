package com.example.cervezas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cervezas.R
import com.example.cervezas.databinding.ActivityMainBinding
import com.example.cervezas.ui.detail.DetailActivity
import com.example.cervezas.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Cervezas"

        adapter = BeerAdapter { beer ->
            mainViewModel.onItemClicked(beer)
        }

        binding.recycler.layoutManager = GridLayoutManager(this, 3)
        binding.recycler.adapter = adapter

        mainViewModel.uiModel.observe(this) { model ->
            when (model) {
                is MainViewModel.UiModel.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.emptyView.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                }
                is MainViewModel.UiModel.Content -> {
                    binding.progress.visibility = View.GONE
                    if (model.items.isEmpty()) {
                        binding.emptyView.visibility = View.VISIBLE
                        binding.recycler.visibility = View.GONE
                    } else {
                        binding.emptyView.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE
                        adapter.submitList(model.items)
                    }
                }
                is MainViewModel.UiModel.Navigation -> {
                    binding.progress.visibility = View.GONE
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra(DetailViewModel.ITEM_ID, model.item.id)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.queryHint = "Buscar cerveza..."
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.search(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
