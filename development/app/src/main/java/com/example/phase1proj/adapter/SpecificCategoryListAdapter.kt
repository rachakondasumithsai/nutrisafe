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
import com.example.phase1proj.views.ItemActivity
import com.example.phase1proj.R
import com.example.phase1proj.R.layout.child_card_view_list
import com.example.phase1proj.model.Item
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
        holder.itemTitle.text = itemList[position].name
        holder.itemThumbnail.setImageResource(itemList[position].thumbnail!!)
        holder.cardLayout.setOnClickListener {
            openActivity(holder, itemList[position].name)
        }
    }

    private fun openActivity(holder: MyViewHolder1, Name: String?) {

        val intent = Intent(holder.cardLayout.context, ItemActivity::class.java)
        intent.putExtra("itemName", Name)
        ContextCompat.startActivity(holder.cardLayout.context, intent, null)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder1(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var itemTitle: TextView
        var itemThumbnail: ImageView
        var cardLayout: ConstraintLayout

        init {
            itemTitle =
                itemsView.findViewById<View>(R.id.ItemTitle) as TextView
            itemThumbnail =
                itemsView.findViewById<View>(R.id.ItemThumbnail) as ImageView
            cardLayout = itemsView.item
        }
    }
}