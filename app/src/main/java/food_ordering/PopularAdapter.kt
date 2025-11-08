package food_ordering

import com.example.myproject.R


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PopularAdapter(private val itemList: List<PopularItem>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.popularImage)
        val name: TextView = view.findViewById(R.id.popularName)
        val price: TextView = view.findViewById(R.id.popularPrice)
        val addBtn: Button = view.findViewById(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = itemList[position]
        holder.image.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.price.text = item.price
    }

    override fun getItemCount(): Int = itemList.size
}
