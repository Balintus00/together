package hu.bme.aut.android.together.features.addevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import hu.bme.aut.android.together.databinding.FragmentAddEventPagerBinding
import hu.bme.aut.android.together.features.addevent.adapter.AddEventPagerAdapter
import kotlin.math.roundToInt

class AddEventPagerFragment : Fragment() {

    private lateinit var pagerAdapter: AddEventPagerAdapter
    private lateinit var binding: FragmentAddEventPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEventPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPagerAdapter()
    }

    private fun setUpPagerAdapter() {
        pagerAdapter = AddEventPagerAdapter(this)
        binding.vp2AddEventPager.adapter = pagerAdapter
        setPageChangingBehaviour()
    }

    private fun setPageChangingBehaviour() {
        binding.vp2AddEventPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.lpiAddEventProgress.progress = calculateCurrentPagingProgress(position)
            }
        })
    }

    private fun calculateCurrentPagingProgress(position: Int): Int {
        return ((position + 1).toFloat() / pagerAdapter.itemCount.toFloat() * 100).roundToInt()
    }
}