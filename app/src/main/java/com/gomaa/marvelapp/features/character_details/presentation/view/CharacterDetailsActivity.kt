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


const val EXTRA_CHARACTER = "EXTRA_CHARACTER"

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity(), SectionsAdapter.SectionsListener {
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var mCharacterDetailsAdapter: CharacterDetailsAdapter
    private val arts = ArrayList<SectionEntity>()
    private var comicsSection: SectionEntity? = null
    private var eventsSection: SectionEntity? = null
    private var seriesSection: SectionEntity? = null
    private var storiesSection: SectionEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        initRecyclerView()
        getExtra()
        bindCharacterDetails()
        onBackLayoutPressed()
        bindComicsCharacterDetails()
        bindEventsCharacterDetails()
        bindSeriesCharacterDetails()
        bindStoriesCharacterDetails()
    }

    private fun initRecyclerView() {
        mCharacterDetailsAdapter =
            CharacterDetailsAdapter(this)
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


    }

    private fun bindAdapterData() {
        arts.clear()
        if (comicsSection != null && !comicsSection!!.items.isNullOrEmpty()) {
            arts.add(comicsSection!!)
        }
        if (storiesSection != null && !storiesSection!!.items.isNullOrEmpty())
            arts.add(storiesSection!!)
        if (seriesSection != null && !seriesSection!!.items.isNullOrEmpty())
            arts.add(seriesSection!!)
        if (eventsSection != null && !eventsSection!!.items.isNullOrEmpty())
            arts.add(eventsSection!!)
        if (!arts.isNullOrEmpty()) {
            mCharacterDetailsAdapter.setItems(arts)
        }

    }

    private fun initComics(comic: ComicsEntity): SectionEntity {
        val ids = ArrayList<String>()
        comic.items?.forEach {
            ids.add(it.resourceURI.substringAfterLast("/"))
        }
        viewModel.getComicsItemDetails(ids)



        return SectionEntity(
            getString(R.string.comics),
            comic.available,
            comic.collectionURI,
            comic.items,
            comic.returned
        )

    }

    private fun bindComicsCharacterDetails() {
        viewModel.getComicsDetailsLiveDate()
            .observe(this, { response ->
                comicsSection?.items?.forEachIndexed { index, itemEntity ->
                    itemEntity.thumbnail = response[index]?.data?.results!![0].thumbnail
                }
                bindAdapterData()

            })
    }

    private fun bindEventsCharacterDetails() {
        viewModel.getEventsDetailsLiveDate()
            .observe(this, { response ->
                eventsSection?.items?.forEachIndexed { index, itemEntity ->
                    itemEntity.thumbnail = response[index]?.data?.results!![0].thumbnail
                }
                bindAdapterData()

            })
    }

    private fun bindSeriesCharacterDetails() {
        viewModel.getSeriesDetailsLiveDate()
            .observe(this, { response ->
                seriesSection?.items?.forEachIndexed { index, itemEntity ->
                    itemEntity.thumbnail = response[index]?.data?.results!![0].thumbnail
                }
                bindAdapterData()

            })
    }

    private fun bindStoriesCharacterDetails() {
        viewModel.getStoriesDetailsLiveDate()
            .observe(this, { response ->
                storiesSection?.items?.forEachIndexed { index, itemEntity ->
                    itemEntity.thumbnail = response[index]?.data?.results!![0].thumbnail
                }
                bindAdapterData()

            })
    }

    private fun initEvents(event: EventsEntity): SectionEntity {
        val ids = ArrayList<String>()
        event.items?.forEach {
            ids.add(it.resourceURI.substringAfterLast("/"))
        }
        viewModel.getEventsItemDetails(ids)

        return SectionEntity(
            getString(R.string.events),
            event.available,
            event.collectionURI,
            event.items,
            event.returned
        )

    }

    private fun initStories(story: StoriesEntity): SectionEntity {
        val ids = ArrayList<String>()
        story.items?.forEach {
            ids.add(it.resourceURI.substringAfterLast("/"))
        }
        viewModel.getStoriesItemDetails(ids)

        return SectionEntity(
            getString(R.string.stories),
            story.available,
            story.collectionURI,
            story.items,
            story.returned
        )

    }

    private fun initSeries(series: SeriesEntity): SectionEntity {
        val ids = ArrayList<String>()
        series.items?.forEach {
            ids.add(it.resourceURI.substringAfterLast("/"))
        }
        viewModel.getSeriesItemDetails(ids)
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

    override fun onItemPressed(item: ItemEntity) {
        val url = item.thumbnail?.path + "." + item.thumbnail?.extension

        OverlayFragment.newInstance(url).show(supportFragmentManager, OverlayFragment.TAG)

    }
}