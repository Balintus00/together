package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.participant.rulesetter.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.model.EventPublicRuleOptions
import javax.inject.Inject

@HiltViewModel
class PublicEventRuleSetterViewModel @Inject constructor() : RainbowCakeViewModel<PublicEventRuleSetterState>(Loaded){

    val publicEventOptions = MutableLiveData<EventPublicRuleOptions>()

}