package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.viewmodel

import hu.bme.aut.android.together.ui.model.EventNews

sealed class NewsListState

object Loading: NewsListState()

data class NewsListLoaded(val newsList: List<EventNews>): NewsListState()