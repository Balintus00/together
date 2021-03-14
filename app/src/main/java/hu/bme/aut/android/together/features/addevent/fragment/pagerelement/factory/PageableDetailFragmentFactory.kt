package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory

import androidx.fragment.app.Fragment
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.*

// The order of the instances is important
enum class PageableDetailFragmentFactory {
    NameAdder {
        override fun getFragment(): Fragment {
            return NameAdderFragment()
        }
    },
    VisibilityChooser {
        override fun getFragment(): Fragment {
            return VisibilityChooserFragment()
        }
    },
    CategoryPicker {
        override fun getFragment(): Fragment {
            return CategoryPickerFragment()
        }
    },
    PhotoUploader {
        override fun getFragment(): Fragment {
            return PhotoUploaderFragment()
        }
    },
    DateSetter {
        override fun getFragment(): Fragment {
            return DateSetterFragment()
        }
    },
    PlacePicker {
        override fun getFragment(): Fragment {
            return PlacePickerFragment()
        }
    },
    DescriptionGiver {
        override fun getFragment(): Fragment {
            return DescriptionGiverFragment()
        }
    };

    abstract fun getFragment(): Fragment
}