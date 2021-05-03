package hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.factory

import androidx.fragment.app.Fragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.category.fragment.CategoryPickerFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.date.fragment.DateSetterFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.description.fragment.DescriptionGiverFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.name.fragment.NameAdderFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.photo.fragment.PhotoUploaderFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.place.fragment.PlacePickerFragment
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.visibility.fragment.VisibilityChooserFragment

/**
 * Factory class, which responsibility is to be able to create the fragments, that will be used
 * for the pages of the event creation process.
 */
enum class PageableDetailSetterFragmentFactory {

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's name.
     */
    NameAdder {
        override fun getFragment(): Fragment {
            return NameAdderFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's visibility.
     */
    VisibilityChooser {
        override fun getFragment(): Fragment {
            return VisibilityChooserFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's category.
     */
    CategoryPicker {
        override fun getFragment(): Fragment {
            return CategoryPickerFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's image.
     */
    PhotoUploader {
        override fun getFragment(): Fragment {
            return PhotoUploaderFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's date.
     */
    DateSetter {
        override fun getFragment(): Fragment {
            return DateSetterFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's location.
     */
    PlacePicker {
        override fun getFragment(): Fragment {
            return PlacePickerFragment()
        }
    },

    /**
     * This factory's fragment can be used, to create an user interface, on which the user can
     * set the event's description.
     */
    DescriptionGiver {
        override fun getFragment(): Fragment {
            return DescriptionGiverFragment()
        }
    };

    /**
     * Creates a new Fragment reference.
     */
    abstract fun getFragment(): Fragment
}