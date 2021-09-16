package com.gomaa.marvelapp.features.list_characters.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.util.LIMIT_PAGE_COUNT
import com.gomaa.marvelapp.features.character_details.presentation.view.CharacterDetailsActivity
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.CharacterEntity
import com.gomaa.marvelapp.features.list_characters.presentation.view.adapter.ListCharactersAdapter
import com.gomaa.marvelapp.features.list_characters.presentation.view.listener.OnCharacterClickedListener
import com.gomaa.marvelapp.features.list_characters.presentation.viewmodel.ListCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_characters.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val EXTRA_CHARACTER = "EXTRA_CHARACTER"

@AndroidEntryPoint
class ListCharactersActivity : AppCompatActivity(), OnCharacterClickedListener {

    private lateinit var adapter: ListCharactersAdapter
    private val viewModel: ListCharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_characters)
        viewModel.listCharacters()
        bindListCharacters()
        initListRecyclerView()
        loadMore()
        goToSearchScreen()

    }

    private fun loadMore() {
        refreshLayout.setOnLoadMoreListener {
            viewModel.listCharacters(null)
        }

    }

    private fun initListRecyclerView() {

        adapter =
            ListCharactersAdapter(this)
        listCharactersRecyclerView.adapter = adapter
        listCharactersRecyclerView.bringToFront()

    }

    private fun bindListCharacters() {
        viewModel.getListCharactersLiveDate()
            .observe(this, {
                listCharactersProgressbar.visibility = View.GONE
                if (it?.charactersEntity != null) {
                    if (it.charactersEntity.offset != null)
                        viewModel.setOffset(it.charactersEntity.offset)
                    adapter.addItems(it.charactersEntity.characterEntity)
                    refreshLayout.finishLoadMore()
                    refreshLayout.setEnableLoadMore(it.charactersEntity.characterEntity.size >= LIMIT_PAGE_COUNT)

                } else {
                    refreshLayout.finishLoadMore()
                    refreshLayout.setEnableLoadMore(false)
                }

            })
    }

    private fun goToSearchScreen() {
        search.setOnClickListener {
            val intent = Intent(this, SearchCharactersActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        homeForegroundColor.visibility = View.GONE
    }

    override fun onCharacterClicked(item: CharacterEntity) {
        homeForegroundColor.visibility = View.VISIBLE
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(EXTRA_CHARACTER, item.id)
        startActivity(intent)

    }
}