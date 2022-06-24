package rem.apiv.xsnng.fragment

import androidx.core.text.HtmlCompat
import rem.apiv.xsnng.R
import rem.apiv.xsnng.base.BaseFragment
import rem.apiv.xsnng.binding.viewBinding
import rem.apiv.xsnng.databinding.FragmentContentBinding

class ContentFragment(
    private val content: DataContentModel
): BaseFragment<FragmentContentBinding> (R.layout.fragment_content){

    override val binding: FragmentContentBinding by viewBinding(FragmentContentBinding::bind)

    override fun setupViews() {
        with(binding){
            menuName.text = content.title
            menuDesc.text = HtmlCompat.fromHtml(content.desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
            banner.setBackgroundResource(content.image)
        }
    }

    override fun viewModelObservers() {
    }
}