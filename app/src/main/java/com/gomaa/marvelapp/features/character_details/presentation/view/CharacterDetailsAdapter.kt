package com.gomaa.marvelapp.features.character_details.presentation.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.presentation.BaseRecyclerView
import kotlinx.android.synthetic.main.item_character_details.view.*

class CharacterDetailsAdapter :
    BaseRecyclerView<SectionEntity, BaseRecyclerView.BaseRecyclerViewHolder<SectionEntity>>() {
    override fun getLayout(type: Int): Int {
        return R.layout.item_character_details
    }

    override fun getViewHolder(view: View, type: Int): BaseRecyclerViewHolder<SectionEntity> {
        return CharacterArtsViewHolder(view)
    }

    inner class CharacterArtsViewHolder(itemView: View) :
        BaseRecyclerView.BaseRecyclerViewHolder<SectionEntity>(itemView) {
        override fun onBind(item: SectionEntity) {
            itemView.sectionTitleTextView.text = item.title
            itemView.sectionRecyclerView.layoutManager =
                object : LinearLayoutManager(itemView.context, HORIZONTAL, false) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        lp?.width = width / 3
                        return super.checkLayoutParams(lp)
                    }
                }
            val adapter = SectionsAdapter()
            itemView.sectionRecyclerView.adapter = adapter

            adapter.addItems(item.items)
        }
    }

}
