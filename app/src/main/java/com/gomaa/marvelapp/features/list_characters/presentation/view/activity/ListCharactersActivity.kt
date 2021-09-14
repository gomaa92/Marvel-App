package com.gomaa.marvelapp.features.list_characters.presentation.view.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.util.LIMIT_PAGE_COUNT
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.CharacterEntity
import com.gomaa.marvelapp.features.list_characters.presentation.view.adapter.ListCharactersAdapter
import com.gomaa.marvelapp.features.list_characters.presentation.view.listener.OnCharacterClickedListener
import com.gomaa.marvelapp.features.list_characters.presentation.viewmodel.ListCharactersViewModel
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_characters.*


@AndroidEntryPoint
class ListCharactersActivity : AppCompatActivity(), OnCharacterClickedListener {

    lateinit var adapter: ListCharactersAdapter
    private val viewModel: ListCharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_characters)
        viewModel.listCharacters()
        bindListCharacters()
        initListRecyclerView()
        loadMore()

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

    override fun onCharacterClicked(item: CharacterEntity) {

    }
}