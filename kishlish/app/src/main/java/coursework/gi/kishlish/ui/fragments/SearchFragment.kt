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
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kishlishArrayList: ArrayList<Kishlish>

    override fun onStart() {
        super.onStart()
        recyclerView = search_recycler
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        search_btn.setOnClickListener {
            kishlishArrayList = arrayListOf<Kishlish>()
            val dataKishlishs = arrayListOf<String>()

            searchCurrentKishlishs(dataKishlishs)
            getKishlishData(dataKishlishs)

        }
    }

        private fun searchCurrentKishlishs(dataKishkishs: ArrayList<String>) {
            REF_DATABASE_ROOT.child(NODE_KISHLISHS_USER).child(search_user.text.toString())
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
    private fun findUserId(finderId: ArrayList<String>) : String? {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { kishlishSnapshot ->
                        if (kishlishSnapshot.key.toString() == search_user.text.toString()) {
                            finderId.add(kishlishSnapshot.value.toString())
                            showToast(finderId[0])
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return finderId[0]!!
    }

//    private fun searchUsersKishlishs(finderId: ArrayList<String>, dataKishkishs: ArrayList<String>) {
//        REF_DATABASE_ROOT.child(NODE_KISHLISHS_USER).child(finderId[0])
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        snapshot.children.forEach { kishlishSnapshot ->
//                            dataKishkishs.add(kishlishSnapshot.key.toString())
//                        }
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//            })
//    }

//    private fun getKishlishData(dataKishkishs: ArrayList<String>) {
//        REF_DATABASE_ROOT.child(NODE_KISHLISHS).addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    snapshot.children.forEach { kishlishSnapshot ->
//                        dataKishkishs.forEach { dataKish ->
//                            if (kishlishSnapshot.key.toString() == dataKish) {
//                                val kishlish = kishlishSnapshot.getValue(Kishlish::class.java)
//                                kishlishArrayList.add(kishlish!!)
//                            }
//                        }
//                    }
//                    recyclerView.adapter = KishlishAdapter(kishlishArrayList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }
}