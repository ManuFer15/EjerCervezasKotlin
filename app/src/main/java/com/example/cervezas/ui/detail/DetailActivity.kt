package com.example.cervezas.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.cervezas.R
import com.example.cervezas.databinding.ActivityDetailsBinding
import com.example.domain.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel.uiModel.observe(this) { model ->
            when (model) {
                is DetailViewModel.UiModel.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.detailContainer.visibility = View.GONE
                }
                is DetailViewModel.UiModel.Content -> {
                    binding.progress.visibility = View.GONE
                    binding.detailContainer.visibility = View.VISIBLE
                    model.beer?.let { showBeerDetail(it) }
                }
            }
        }
    }

    private fun showBeerDetail(beer: Item) {
        supportActionBar?.title = beer.name

        binding.backdrop.load(beer.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }

        binding.summary.text = beer.description ?: ""

        val infoHtml = buildString {
            beer.tagline?.let { append("<b>Lema:</b> $it<br/>") }
            beer.firstBrewed?.let { append("<b>Fecha de elaboración:</b> $it<br/>") }
            beer.abv?.let { append("<b>Graduación (ABV):</b> $it%<br/>") }
            beer.ibu?.let { append("<b>Amargor (IBU):</b> $it<br/>") }
            val pairings = beer.foodPairing
            if (!pairings.isNullOrEmpty()) {
                append("<b>Maridaje sugerido:</b> ${pairings.joinToString(", ")}<br/>")
            }
        }

        binding.detailInfo.text = Html.fromHtml(infoHtml, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
