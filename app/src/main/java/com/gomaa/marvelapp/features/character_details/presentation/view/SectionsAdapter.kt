package com.gomaa.marvelapp.features.character_details.presentation.view

import android.view.View
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.presentation.BaseRecyclerView
import com.gomaa.marvelapp.base.util.loadImage
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.ItemEntity
import kotlinx.android.synthetic.main.item_section.view.*


class SectionsAdapter :
    BaseRecyclerView<ItemEntity, BaseRecyclerView.BaseRecyclerViewHolder<ItemEntity>>(
    ) {
    override fun getLayout(type: Int): Int {
        return R.layout.item_section
    }

    override fun getViewHolder(view: View, type: Int): BaseRecyclerViewHolder<ItemEntity> {
        return CharacterArtsViewHolder(view)
    }

    inner class CharacterArtsViewHolder(itemView: View) :
        BaseRecyclerView.BaseRecyclerViewHolder<ItemEntity>(itemView) {
        override fun onBind(item: ItemEntity) {
            loadImage(itemView.context, item.resourceURI, itemView.artImageView)
            itemView.artNameTextView.text = item.name
        }
    }

}