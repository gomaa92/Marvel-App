package com.gomaa.marvelapp.features.list_characters.presentation.view.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.features.list_characters.presentation.view.adapter.SearchAdapter
import com.gomaa.marvelapp.features.list_characters.presentation.viewmodel.ListCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search_characters.*

@AndroidEntryPoint
class SearchCharactersActivity : AppCompatActivity() {
    private lateinit var adapter: SearchAdapter
    private val viewModel: ListCharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_characters)
        window.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.search_bg_color)))
        window.statusBarColor = ContextCompat.getColor(this, R.color.black_1b1b1b)
        initListRecyclerView()
        search()
        bindResult()
        cancel()
    }

    private fun initListRecyclerView() {

        adapter =
            SearchAdapter()
        searchRecyclerView.adapter = adapter

    }

    private fun search() {
        searchInput.doAfterTextChanged {
            if (searchInput.text.toString().isNotEmpty())
                viewModel.listCharacters(searchInput.text.toString(),true)
            else
                adapter.clearItems()
        }
    }

    private fun bindResult() {
        viewModel.getListSearchCharactersLiveDate()
            .observe(this, {
                if (it?.charactersEntity != null) {
                    it.charactersEntity.characterEntity.forEach { entity ->
                        entity.searchText = searchInput.text.toString()
                    }
                    adapter.setItems(it.charactersEntity.characterEntity)

                }

            })
    }

    private fun cancel() {
        cancelSearch.setOnClickListener {
            super.onBackPressed()
        }
    }
}