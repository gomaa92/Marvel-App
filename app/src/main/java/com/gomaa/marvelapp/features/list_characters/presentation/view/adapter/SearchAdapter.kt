package com.gomaa.marvelapp.features.list_characters.presentation.view.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.presentation.BaseRecyclerView
import com.gomaa.marvelapp.base.util.loadImage
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.CharacterEntity
import kotlinx.android.synthetic.main.item_search.view.*
import java.text.Normalizer
import javax.inject.Inject
import kotlin.math.min


class SearchAdapter @Inject constructor() :
    BaseRecyclerView<CharacterEntity, BaseRecyclerView.BaseRecyclerViewHolder<CharacterEntity>>(

    ) {
    override fun getLayout(type: Int): Int {
        return R.layout.item_search
    }

    override fun getViewHolder(view: View, type: Int): BaseRecyclerViewHolder<CharacterEntity> {
        return ListSearchCharactersViewHolder(view)
    }

    inner class ListSearchCharactersViewHolder(itemView: View) :
        BaseRecyclerView.BaseRecyclerViewHolder<CharacterEntity>(itemView) {

        override fun onBind(item: CharacterEntity) {

            loadImage(
                itemView.context,
                "${item.thumbnail.path}.${item.thumbnail.extension}",
                itemView.searchItemImage
            )

            itemView.searchItemName.text =
                highlightText(itemView.context, item.searchText, item.name)


        }
    }

    fun highlightText(context: Context, search: String?, originalText: String): CharSequence {
        if (search != null && !search.equals("", ignoreCase = true)) {
            val normalizedText: String =
                Normalizer.normalize(originalText, Normalizer.Form.NFD).lowercase()
            var start = normalizedText.indexOf(search)
            return if (start < 0) {
                originalText
            } else {
                val highlighted: Spannable = SpannableString(originalText)
                while (start >= 0) {
                    val spanStart = min(start, originalText.length)
                    val spanEnd = min(start + search.length, originalText.length)
                    highlighted.setSpan(
                        ForegroundColorSpan(context.resources.getColor(R.color.red_b6151c)),
                        spanStart,
                        spanEnd,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    start = normalizedText.indexOf(search, spanEnd)
                }
                highlighted
            }
        }
        return originalText
    }


}