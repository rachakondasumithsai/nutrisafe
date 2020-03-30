package com.example.phase1proj.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.phase1proj.views.ItemActivity
import com.example.phase1proj.R
import com.example.phase1proj.adapter.ChildRecyclerViewAdapter.MyViewHolder
import com.example.phase1proj.model.Item
import kotlinx.android.synthetic.main.child_card_view_list.view.*

class ChildRecyclerViewAdapter(
    private val itemList: List<Item>
) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val layoutInflater = LayoutInflater.from(parent.context)
        view = layoutInflater.inflate(R.layout.child_card_view_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val resID = holder.itemView.context.resources.getIdentifier(
            itemList[position].thumbnail,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.itemTitle.text = itemList[position].name
        holder.itemThumbnail.setImageResource(resID)

        holder.textLayout.setOnClickListener {
            openActivity(holder, itemList[position])
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemsView: View) : ViewHolder(itemsView) {
        var itemTitle: TextView
        var itemThumbnail: ImageView


        var textLayout = itemsView.item

        init {
            itemTitle =
                itemsView.findViewById<View>(R.id.ItemTitle) as TextView
            itemThumbnail =
                itemsView.findViewById<View>(R.id.ItemThumbnail) as ImageView
        }
    }

    private fun openActivity(holder: ChildRecyclerViewAdapter.MyViewHolder, item: Item) {

        val intent = Intent(holder.textLayout.context, ItemActivity::class.java)
        intent.putExtra("itemName", item.name)
        intent.putExtra("itemCategory", item.category)
        intent.putExtra("itemThumbnail", item.thumbnail)
        intent.putExtra("itemNutrient", item.nutrient)


        ContextCompat.startActivity(holder.textLayout.context, intent, null)

    }

}