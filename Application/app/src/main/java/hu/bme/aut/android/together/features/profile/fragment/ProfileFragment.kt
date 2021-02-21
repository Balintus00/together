package hu.bme.aut.android.together.features.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    private fun setToolbar(){
        with(binding.tbProfile){
            setNavigationIcon(R.drawable.ic_action_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.profile_toolbar_menu)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.actionProfileSettingsOption -> {
                        // TODO opening settings
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
    }
}