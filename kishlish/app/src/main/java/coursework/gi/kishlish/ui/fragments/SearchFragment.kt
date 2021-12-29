package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.adapters.KishlishAdapter
import coursework.gi.kishlish.ui.models.Kishlish
import coursework.gi.kishlish.ui.utilits.APP_ACTIVITY
import coursework.gi.kishlish.ui.utilits.NODE_KISHLISHS
import coursework.gi.kishlish.ui.utilits.REF_DATABASE_ROOT
import kotlinx.android.synthetic.main.fragment_kishlish.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kishlishArrayList: ArrayList<Kishlish>

    override fun onStart() {
        super.onStart()
        recyclerView = kishlish_recycler
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        kishlishArrayList = arrayListOf<Kishlish>()
        getKishlishData()
    }

    private fun getKishlishData() {
        REF_DATABASE_ROOT.child(NODE_KISHLISHS).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (kishlishSnapshot in snapshot.children) {
                        val kishlish = kishlishSnapshot.getValue(Kishlish::class.java)
                        kishlishArrayList.add(kishlish!!)
                    }
                    recyclerView.adapter = KishlishAdapter(kishlishArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}