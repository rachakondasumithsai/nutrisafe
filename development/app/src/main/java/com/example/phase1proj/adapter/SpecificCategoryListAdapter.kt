package com.example.phase1proj.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.phase1proj.R
import com.example.phase1proj.R.layout.child_card_view_list
import com.example.phase1proj.model.Item
import com.example.phase1proj.views.ItemActivity
import kotlinx.android.synthetic.main.child_card_view_list.view.*

class SpecificCategoryListAdapter(
    private val itemList: List<Item>
) : RecyclerView.Adapter<SpecificCategoryListAdapter.MyViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        val view: View
        val layoutInflater = LayoutInflater.from(parent.context)
        view = layoutInflater.inflate(child_card_view_list, parent, false)
        return MyViewHolder1(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {

        val resID = holder.itemView.context.resources.getIdentifier(
            itemList[position].thumbnail,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.itemTitle.text = itemList[position].name
        holder.itemThumbnail.setImageResource(resID)
        holder.cardLayout.setOnClickListener {
            openActivity(holder, itemList[position])
        }
    }

    private fun openActivity(holder: MyViewHolder1, item: Item) {

        val intent = Intent(holder.cardLayout.context, ItemActivity::class.java)
        intent.putExtra("itemName", item.name)
        intent.putExtra("itemCategory", item.category)
        intent.putExtra("itemThumbnail", item.thumbnail)
        intent.putExtra("itemNutrient",item.nutrient)

        ContextCompat.startActivity(holder.cardLayout.context, intent, null)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder1(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var itemTitle: TextView = itemsView.findViewById<View>(R.id.ItemTitle) as TextView
        var itemThumbnail: ImageView = itemsView.findViewById<View>(R.id.ItemThumbnail) as ImageView
        var cardLayout: ConstraintLayout = itemsView.item

        var context = itemsView.context

    }
}