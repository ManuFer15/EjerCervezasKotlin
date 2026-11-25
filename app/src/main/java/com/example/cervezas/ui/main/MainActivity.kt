package com.example.cervezas.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.cervezas.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // RecyclerView, búsqueda y ViewBinding se quedan en esta Activity.
        // Cuando haga falta cargar: mainViewModel.refresh()
        // Cuando el usuario elige un elemento, el ViewModel emite Navigation
        // y desde aquí se abre DetailActivity con el id:
        //
        // startActivity(
        //     Intent(this, DetailActivity::class.java)
        //         .putExtra(DetailViewModel.ITEM_ID, item.id)
        // )
    }
}
