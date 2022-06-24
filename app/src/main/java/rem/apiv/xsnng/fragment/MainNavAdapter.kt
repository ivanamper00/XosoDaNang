package rem.apiv.xsnng.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rem.apiv.xsnng.R
import rem.apiv.xsnng.databinding.ItemNavMainBinding
import rem.apiv.xsnng.databinding.ItemNavMenuBinding
import rem.apiv.xsnng.fragment.MainFragment

class MainNavAdapter(
    private val listener : MainFragment.Listener
): RecyclerView.Adapter<MainNavAdapter.Holder>() {

    val data = Data.items.filterIndexed { index, _ ->
        index != 0
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemNavMainBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_nav_main, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            val content = data[position]
            menuName.text = content.title
            root.setOnClickListener { listener.onSelectedItem(position + 1) }
        }
    }

    override fun getItemCount(): Int = data.size
}