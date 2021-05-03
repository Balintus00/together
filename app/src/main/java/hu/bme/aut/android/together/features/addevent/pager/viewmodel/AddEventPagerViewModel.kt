package hu.bme.aut.android.together.features.addevent.pager.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.model.presentation.EventDetails
import javax.inject.Inject

@HiltViewModel
class AddEventPagerViewModel @Inject constructor(): RainbowCakeViewModel<AddEventPagerState>(Loaded) {

    val addableEvent: MutableLiveData<EventDetails> = MutableLiveData(EventDetails(
        0L,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        0,
        0,
        isPrivate = false,
        isParticipant = false,
        isOrganiser = false
    ))

}