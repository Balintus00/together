package hu.bme.aut.android.together.features.searchevent.searcher.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.android.together.R
import javax.inject.Inject

@HiltViewModel
class EventQueryViewModel @Inject constructor(@ApplicationContext context: Context) :
    RainbowCakeViewModel<EventQueryState>(DefaultState) {

    var startDateText = MutableLiveData(context.getString(R.string.event_query_default_value_today))
    var startTimeText = MutableLiveData(context.getString(R.string.event_query_default_value_now))
    var endDateText = MutableLiveData(context.getString(R.string.event_query_default_value_date_indefinite))
    var endTimeText = MutableLiveData(context.getString(R.string.event_query_default_value_time_indefinite))


}