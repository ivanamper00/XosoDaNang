package rem.apiv.xsnng

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import rem.apiv.xsnng.databinding.ItemNavMenuBinding
import rem.apiv.xsnng.fragment.Data
import rem.apiv.xsnng.fragment.MainFragment

class NavAdapter(
    private val listener : MainFragment.Listener
): RecyclerView.Adapter<NavAdapter.Holder>() {

    var selectedPos: Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemNavMenuBinding.bind(itemView)
        }
    }

    val data = Data.items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_nav_menu, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){

            val content = data[position]

            menuTitle.text = content.title

            if(selectedPos == position){
                selectedImageView.visibility = View.VISIBLE
                menuTitle.setTextColor(ContextCompat.getColor(root.context, R.color.yellow))
            }else {
                selectedImageView.visibility = View.INVISIBLE
                menuTitle.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            }

            root.setOnClickListener {
                selectedPos = holder.adapterPosition
                listener.onSelectedItem(position)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}