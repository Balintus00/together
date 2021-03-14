package hu.bme.aut.android.together.features.addevent.interfaces

interface EventAddingPagerContainer {

    fun pageTo(position: Int)

    fun pageBack()

    fun eventCreated()

    fun eventDiscarded()
}