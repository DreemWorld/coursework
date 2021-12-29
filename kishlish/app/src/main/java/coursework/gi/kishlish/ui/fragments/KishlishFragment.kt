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
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_kishlish.*

class KishlishFragment : Fragment(R.layout.fragment_kishlish) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kishlishArrayList: ArrayList<Kishlish>

    override fun onStart() {
        super.onStart()
        recyclerView = kishlish_recycler
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        kishlishArrayList = arrayListOf<Kishlish>()
        val dataKishkishs = arrayListOf<String>()

        searchCurrentKishlishs(dataKishkishs)
        getKishlishData(dataKishkishs)

    }

    private fun searchCurrentKishlishs(dataKishkishs: ArrayList<String>) {
        REF_DATABASE_ROOT.child(NODE_KISHLISHS_USER).child(CURRENT_UID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        snapshot.children.forEach { kishlishSnapshot ->
                            dataKishkishs.add(kishlishSnapshot.key.toString())
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun getKishlishData(dataKishkishs: ArrayList<String>) {
        REF_DATABASE_ROOT.child(NODE_KISHLISHS).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { kishlishSnapshot ->
                        dataKishkishs.forEach { dataKish ->
                            if (kishlishSnapshot.key.toString() == dataKish) {
                                val kishlish = kishlishSnapshot.getValue(Kishlish::class.java)
                                kishlishArrayList.add(kishlish!!)
                            }
                        }
                    }
                    recyclerView.adapter = KishlishAdapter(kishlishArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}