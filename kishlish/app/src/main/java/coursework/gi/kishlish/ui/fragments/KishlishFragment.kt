package coursework.gi.kishlish.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.models.KishlishModel
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_kishlish.*
import kotlinx.android.synthetic.main.kishlish_item.view.*

class KishlishFragment : Fragment(R.layout.fragment_kishlish) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FirebaseRecyclerAdapter<KishlishModel, KishlishHolder>
    private lateinit var refKishlish: DatabaseReference
    private lateinit var refKish: DatabaseReference
    private lateinit var refKishListener: AppValueEventListener
    private val mapListener = hashMapOf<DatabaseReference, AppValueEventListener>()

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = kishlish_recycler
        showToast("1")
        refKishlish = REF_DATABASE_ROOT.child(NODE_KISHLISHS)
        showToast("2")

        val options = FirebaseRecyclerOptions.Builder<KishlishModel>()
            .setQuery(refKishlish, KishlishModel::class.java)
            .build()

        adapter = object : FirebaseRecyclerAdapter<KishlishModel, KishlishHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KishlishHolder {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.kishlish_item,
                    parent,
                    false
                )
                return KishlishHolder(view)
            }

            override fun onBindViewHolder(
                holder: KishlishHolder,
                position: Int,
                model: KishlishModel
            ) {
                refKish = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
                refKishListener = AppValueEventListener {
                    val kishlish = it.getKishlishModel()
                    holder.description.text = kishlish.description
                    holder.name.text = kishlish.name
                    holder.price.text = kishlish.price
                    holder.image.downloadAndSetImage(kishlish.photoUrl)
                }

                refKish.addValueEventListener(refKishListener)
                mapListener[refKish] = refKishListener
            }

        }
        recyclerView.adapter = adapter
        adapter.startListening()
    }


    class KishlishHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.kishlish_name
        val price: TextView = view.kishlish_price
        val description: TextView = view.kishlish_description
        val image: ImageView = view.kishlish_image
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
        mapListener.forEach {
            it.key.removeEventListener(it.value)
        }
    }

}