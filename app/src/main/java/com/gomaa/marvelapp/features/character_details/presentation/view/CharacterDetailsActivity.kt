package com.gomaa.marvelapp.features.character_details.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.util.loadImage
import com.gomaa.marvelapp.features.character_details.presentation.viewmodel.CharacterDetailsViewModel
import com.gomaa.marvelapp.features.list_characters.domain.model.entity.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_character_details.*
import java.util.*


const val EXTRA_CHARACTER = "EXTRA_CHARACTER"

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity() {
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var mCharacterDetailsAdapter: CharacterDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        initRecyclerView()
        getExtra()
        bindCharacterDetails()
        onBackLayoutPressed()
    }

    private fun initRecyclerView() {
        mCharacterDetailsAdapter =
            CharacterDetailsAdapter()
        artWorksRecyclerView.adapter = mCharacterDetailsAdapter

    }

    private fun getExtra() {
        val id = intent.getIntExtra(EXTRA_CHARACTER, 0)
        viewModel.listCharacters(id)
    }

    private fun bindCharacterDetails() {
        viewModel.getCharacterDetailsLiveDate()
            .observe(this, {
                if (it?.charactersEntity != null) {
                    bindScreenData(it.charactersEntity.characterEntity[0])
                }
            })
    }

    private fun bindScreenData(item: CharacterEntity) {
        characterDetailsProgressbar.visibility = View.GONE
        loadImage(this, item.thumbnail.path + "." + item.thumbnail.extension, characterImageView)
        characterTitle.text = item.name
        characterDescription.text = item.description

        if (item.description.isNullOrEmpty())
            characterDescriptionTitle.visibility = View.GONE

        val arts = ArrayList<SectionEntity>()
        var comicsSection: SectionEntity? = null
        var eventsSection: SectionEntity? = null
        var seriesSection: SectionEntity? = null
        var storiesSection: SectionEntity? = null

        if (item.comics != null) {
            comicsSection = initComics(item.comics)
        }

        if (item.events != null) {
            eventsSection = initEvents(item.events)
        }
        if (item.series != null) {
            seriesSection = initSeries(item.series)
        }
        if (item.stories != null) {
            storiesSection = initStories(item.stories)
        }

        if (comicsSection != null && !comicsSection.items.isNullOrEmpty())
            arts.add(comicsSection)
        if (storiesSection != null && !storiesSection.items.isNullOrEmpty())
            arts.add(storiesSection)
        if (seriesSection != null && !seriesSection.items.isNullOrEmpty())
            arts.add(seriesSection)
        if (eventsSection != null && !eventsSection.items.isNullOrEmpty())
            arts.add(eventsSection)
        if (!arts.isNullOrEmpty())
            mCharacterDetailsAdapter.addItems(arts)
    }

    private fun initComics(comic: ComicsEntity): SectionEntity {
        return SectionEntity(
            getString(R.string.comics),
            comic.available,
            comic.collectionURI,
            comic.items,
            comic.returned
        )

    }

    private fun initEvents(event: EventsEntity): SectionEntity {
        return SectionEntity(
            getString(R.string.events),
            event.available,
            event.collectionURI,
            event.items,
            event.returned
        )

    }

    private fun initStories(story: StoriesEntity): SectionEntity {
        return SectionEntity(
            getString(R.string.stories),
            story.available,
            story.collectionURI,
            story.items,
            story.returned
        )

    }

    private fun initSeries(series: SeriesEntity): SectionEntity {
        return SectionEntity(
            getString(R.string.series),
            series.available,
            series.collectionURI,
            series.items,
            series.returned
        )

    }

    private fun onBackLayoutPressed() {
        backLayout.setOnClickListener {
            super.onBackPressed()
        }
    }
}