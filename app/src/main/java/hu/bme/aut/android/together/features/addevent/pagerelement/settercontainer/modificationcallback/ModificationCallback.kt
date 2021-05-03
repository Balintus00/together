package hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.modificationcallback

interface ModificationCallback {

    fun getCurrentEventTitle(): String

    fun modifyEventTitle(newTitle: String)

}