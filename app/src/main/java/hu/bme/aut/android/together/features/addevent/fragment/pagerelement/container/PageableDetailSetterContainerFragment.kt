package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.container

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentPageableDetailSetterContainerBinding
import hu.bme.aut.android.together.features.addevent.interfaces.EventAddingPagerContainer
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailFragmentFactory
import kotlin.properties.Delegates

class PageableDetailSetterContainerFragment : Fragment() {

    companion object {
        private const val CONTAINED_FRAGMENT_ID_KEY = "CONTAINED_FRAGMENT_ID_KEY"
        private const val SHOW_BACK_KEY = "SHOW_BACK_KEY"
        private const val SHOW_FORWARD_KEY = "SHOW_FORWARD_KEY"

        @JvmStatic
        fun newInstance(
            containedFragmentFactoryOrdinal: Int,
            showBackKey: Boolean = true,
            showForwardKey: Boolean = true
        ): PageableDetailSetterContainerFragment {
            return PageableDetailSetterContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTAINED_FRAGMENT_ID_KEY, containedFragmentFactoryOrdinal)
                    putBoolean(SHOW_BACK_KEY, showBackKey)
                    putBoolean(SHOW_FORWARD_KEY, showForwardKey)
                }
            }
        }
    }

    //TODO It's really important to document, that the parentFragment must implement PagerContainer interface
    //TODO Using dependency injection pattern would be better than that.
    private lateinit var eventAddingPagerContainer: EventAddingPagerContainer

    private lateinit var pageableDetailFragmentFactory: PageableDetailFragmentFactory

    private var showBackNavigationButton by Delegates.notNull<Boolean>()

    private var showForwardNavigationButton by Delegates.notNull<Boolean>()

    private lateinit var binding: FragmentPageableDetailSetterContainerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        eventAddingPagerContainer = parentFragment as EventAddingPagerContainer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveArguments()
    }

    private fun retrieveArguments() {
        retrieveNavigationButtonArgs()
        retrieveContainedFragmentFactory()
    }

    private fun retrieveNavigationButtonArgs() {
        retrieveBackNavigationButtonArgs()
        retrieveForwardNavigationButtonArgs()
    }

    private fun retrieveBackNavigationButtonArgs() {
        showBackNavigationButton = arguments?.getBoolean(SHOW_BACK_KEY) ?: true
    }

    private fun retrieveForwardNavigationButtonArgs() {
        showForwardNavigationButton = arguments?.getBoolean(SHOW_FORWARD_KEY) ?: true
    }

    private fun retrieveContainedFragmentFactory() {
        val ordinal = arguments?.getInt(CONTAINED_FRAGMENT_ID_KEY)
            ?: throw IllegalArgumentException(
                "EventDetailSetterContainerFragment didn't receive" +
                        " ContainedFragmentFactory ordinal value in arguments Bundle! Use static" +
                        " newInstance() factory method to instantiate this fragment!"
            )
        require(ordinal >= 0 && ordinal < PageableDetailFragmentFactory.values().size)
        pageableDetailFragmentFactory = PageableDetailFragmentFactory.values()[ordinal]
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
            .replace(binding.fcvEventDetailAdder.id, pageableDetailFragmentFactory.getFragment())
            .commit()

    }

    private fun setNavigationButtonsBehaviour() {
        setBackNavigatingButtonBehaviour()
        setForwardNavigatingButtonBehaviour()
    }

    private fun setBackNavigatingButtonBehaviour() {
        pageableDetailFragmentFactory.ordinal.let { position ->
            if (showBackNavigationButton)
                binding.swipeButtonBar.ibtnLeft.setOnClickListener {
                    eventAddingPagerContainer.pageTo(position - 1)
                }
            else
                binding.swipeButtonBar.ibtnLeft.visibility = View.GONE
        }
    }

    private fun setForwardNavigatingButtonBehaviour() {
        pageableDetailFragmentFactory.ordinal.let { position ->
            if (showForwardNavigationButton)
                binding.swipeButtonBar.ibtnRight.setOnClickListener {
                    eventAddingPagerContainer.pageTo(position + 1)
                }
            else
                binding.swipeButtonBar.ibtnRight.visibility = View.GONE
        }
    }
}