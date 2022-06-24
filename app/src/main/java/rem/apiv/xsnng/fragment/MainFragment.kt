package rem.apiv.xsnng.fragment

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import rem.apiv.xsnng.R
import rem.apiv.xsnng.base.BaseFragment
import rem.apiv.xsnng.binding.viewBinding
import rem.apiv.xsnng.databinding.FragmentMainBinding

class MainFragment(
    private val listener: Listener
): BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {


    override val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private val navAdapter by lazy { MainNavAdapter(listener) }

    override fun setupViews() {
        with(binding){
            navRecycler.layoutManager = LinearLayoutManager(requireContext())
            navRecycler.adapter = navAdapter

            welcomeText.text = HtmlCompat.fromHtml(Data.items[0].desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun viewModelObservers() {

    }

    interface Listener{
        fun onSelectedItem(position: Int)
    }
}