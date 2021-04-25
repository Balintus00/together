package hu.bme.aut.android.together.features.eventcontrol.communication.newslist.viewmodel

import hu.bme.aut.android.together.model.presentation.EventNews

sealed class NewsListState

object Loading: NewsListState()

data class NewsListLoaded(val newsList: List<EventNews>): NewsListState()