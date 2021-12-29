package coursework.gi.kishlish.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.models.Kishlish
import coursework.gi.kishlish.ui.utilits.downloadAndSetImage
import kotlinx.android.synthetic.main.kishlish_item.view.*

class KishlishAdapter(private val kishlishList: ArrayList<Kishlish>) :
    RecyclerView.Adapter<KishlishAdapter.KishlishHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KishlishHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.kishlish_item, parent, false)
        return KishlishHolder(itemView)
    }

    override fun onBindViewHolder(holder: KishlishHolder, position: Int) {
        val currentItem = kishlishList[position]

            holder.name.text = currentItem.name
            holder.description.text = currentItem.description
            holder.price.text = currentItem.price
            holder.image.downloadAndSetImage(currentItem.photoUrl)
    }

    override fun getItemCount(): Int {
        return kishlishList.size
    }


    class KishlishHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.kishlish_name
        val description: TextView = itemView.kishlish_description
        val price: TextView = itemView.kishlish_price
        val image: ImageView = itemView.kishlish_image
    }


}