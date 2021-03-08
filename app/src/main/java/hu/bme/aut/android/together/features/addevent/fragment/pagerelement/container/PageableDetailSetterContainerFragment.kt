package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.container

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentPageableDetailSetterContainerBinding
import hu.bme.aut.android.together.features.addevent.fragment.PagerContainer
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.*
import kotlinx.android.synthetic.main.swipe_button_bar.*

class PageableDetailSetterContainerFragment : Fragment() {

    companion object {
        private const val CONTAINED_FRAGMENT_ID_KEY = "CONTAINED_FRAGMENT_ID_KEY"

        @JvmStatic
        fun newInstance(containedFragmentFactoryOrdinal: Int): PageableDetailSetterContainerFragment {
            return PageableDetailSetterContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTAINED_FRAGMENT_ID_KEY, containedFragmentFactoryOrdinal)
                }
            }
        }
    }

    //TODO It's really important to document, that the parentFragment must implement PagerContainer interface
    //TODO Using dependency injection pattern would be better than that.
    private lateinit var pagerContainer: PagerContainer

    private lateinit var containedFragmentFactory: ContainedFragmentFactory

    private lateinit var binding: FragmentPageableDetailSetterContainerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerContainer = parentFragment as PagerContainer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveContainedFragmentFactory()
    }

    private fun retrieveContainedFragmentFactory() {
        val ordinal = arguments?.getInt(CONTAINED_FRAGMENT_ID_KEY)
            ?: throw IllegalArgumentException(
                "EventDetailSetterContainerFragment didn't receive" +
                        " ContainedFragmentFactory ordinal value in arguments Bundle! Use static" +
                        " newInstance() factory method to instantiate this fragment!"
            )
        require(ordinal >= 0 && ordinal < ContainedFragmentFactory.values().size)
        containedFragmentFactory = ContainedFragmentFactory.values()[ordinal]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageableDetailSetterContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContainedFragment()
        setNavigationButtonsBehaviour()
    }

    private fun addContainedFragment() {
        childFragmentManager.beginTransaction()
            .replace(binding.fcvEventDetailAdder.id, containedFragmentFactory.getFragment())
            .commit()

    }

    private fun setNavigationButtonsBehaviour() {
        setBackNavigatingButtonBehaviour()
        setForwardNavigatingButtonBehaviour()
    }

    private fun setBackNavigatingButtonBehaviour() {
        containedFragmentFactory.ordinal.let { position ->
            if (position == 0)
                ibtnLeft.visibility = View.GONE
            else
                ibtnLeft.setOnClickListener {
                    pagerContainer.pageTo(position - 1)
                }
        }
    }

    private fun setForwardNavigatingButtonBehaviour() {
        containedFragmentFactory.ordinal.let { position ->
            ibtnRight.setOnClickListener {
                pagerContainer.pageTo(position + 1)
            }
        }
    }

    // The order of the instances is important
    enum class ContainedFragmentFactory {
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
}