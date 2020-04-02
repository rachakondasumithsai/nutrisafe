package com.example.phase1proj.adapter

import android.content.Context
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
import com.example.phase1proj.views.activity.ItemActivity
import kotlinx.android.synthetic.main.child_card_view_list.view.*

class SpecificCategoryListAdapter(
    private val itemList: List<Item>
) : RecyclerView.Adapter<SpecificCategoryListAdapter.MyViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        val view: View
        val layoutInflater = LayoutInflater.from(parent.context)

        // Add the view to be called and inflated for the adapter class
        view = layoutInflater.inflate(child_card_view_list, parent, false)

        // return that view
        return MyViewHolder1(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {

        // Find the drawable image for each nutrient using the holder which will bind to the
        // view
        val resID = holder.itemView.context.resources.getIdentifier(
            itemList[position].thumbnail,
            "drawable",
            holder.itemView.context.packageName
        )

        // Assign the properties to the holder view for each item in the list
        holder.itemTitle.text = itemList[position].name
        holder.itemThumbnail.setImageResource(resID)

        // Open a new activity when the user clicks on the a item name/image layout
        holder.cardLayout.setOnClickListener {
            openActivity(holder, itemList[position])
        }
    }

    // Call this function once the user clicks on an image.
    // Each image will open an activity which contains the nutrient information like
    // calories, fat, fiber, etc.
    private fun openActivity(holder: MyViewHolder1, item: Item) {

        // Intent is a description of an operation that is being performed like opening a new
        // activity, or a fragment.
        val intent = Intent(holder.cardLayout.context, ItemActivity::class.java)

        // Add any information to pass to the new activity . This is more like a parameter passing
        // to function.
        intent.putExtra("itemName", item.name)
        intent.putExtra("itemCategory", item.category)
        intent.putExtra("itemThumbnail", item.thumbnail)
        intent.putExtra("itemNutrient", item.nutrient)

        // Start the activity with the intent which will have extra data and with no other options
        ContextCompat.startActivity(holder.cardLayout.context, intent, null)
    }

    // Returns the number of items present in the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Required for the data to be shown in the xml
    class MyViewHolder1(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var itemTitle: TextView = itemsView.findViewById<View>(R.id.ItemTitle) as TextView
        var itemThumbnail: ImageView = itemsView.findViewById<View>(R.id.ItemThumbnail) as ImageView
        var cardLayout: ConstraintLayout = itemsView.item
        var context: Context? = itemsView.context
    }
}