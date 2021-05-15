package hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.participant.rulesetter.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.model.presentation.EventPublicRuleOptions
import javax.inject.Inject

@HiltViewModel
class PublicEventRuleSetterViewModel @Inject constructor() : RainbowCakeViewModel<PublicEventRuleSetterState>(Loaded){

    val publicEventOptions = MutableLiveData<EventPublicRuleOptions>()

}