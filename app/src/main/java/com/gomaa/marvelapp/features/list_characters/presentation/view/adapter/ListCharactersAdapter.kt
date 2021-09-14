package com.gomaa.marvelapp.features.list_characters.presentation.view.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.presentation.BaseRecyclerView
import com.gomaa.marvelapp.base.util.loadImage
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.CharacterEntity
import com.gomaa.marvelapp.features.list_characters.presentation.view.listener.OnCharacterClickedListener
import kotlinx.android.synthetic.main.item_list_characters.view.*
import javax.inject.Inject

class ListCharactersAdapter @Inject constructor(
    var mListener: OnCharacterClickedListener,
) : BaseRecyclerView<CharacterEntity, BaseRecyclerView.BaseRecyclerViewHolder<CharacterEntity>>(

) {
    override fun getLayout(type: Int): Int {
        return R.layout.item_list_characters
    }

    override fun getViewHolder(view: View, type: Int): BaseRecyclerViewHolder<CharacterEntity> {
        return ListCharactersViewHolder(view)
    }

    inner class ListCharactersViewHolder(itemView: View) :
        BaseRecyclerView.BaseRecyclerViewHolder<CharacterEntity>(itemView) {

        override fun onBind(item: CharacterEntity) {
            itemView.setOnClickListener {
                mListener.onCharacterClicked(item)
            }
            Log.d(
                "ListCharactersView",
                "onBind: ${item.thumbnail.path}.${item.thumbnail.extension}"
            )

            loadImage(
                itemView.context,
                "${item.thumbnail.path}.${item.thumbnail.extension}",
                itemView.characterImageView
            )



            itemView.characterTitleTextView.text = item.name


        }
    }


}