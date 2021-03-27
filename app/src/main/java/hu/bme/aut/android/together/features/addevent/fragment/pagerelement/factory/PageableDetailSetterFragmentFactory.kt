package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory

import androidx.fragment.app.Fragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.*

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