package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_add_new_kishlish.*

class AddNewKishlishFragment : Fragment(R.layout.fragment_add_new_kishlish) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = "Add kishlish"
    }

    override fun onResume() {
        super.onResume()
        add_kish_btn.setOnClickListener {

            val dataMap = mutableMapOf<String, Any>()
            dataMap[CHILD_CURRENT_USER] = USER.username
            dataMap[CHILD_NAME] = add_kish_name.text.toString()
            dataMap[CHILD_DESCRIPTION] = add_kish_description.text.toString()
            dataMap[CHILD_PRICE] = add_kish_price.text.toString()
            REF_DATABASE_ROOT.child(NODE_KISHLISHS)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val count = snapshot.childrenCount.toString()
                        REF_DATABASE_ROOT.child(NODE_KISHLISHS).child(count).updateChildren(dataMap)
                            .addOnCompleteListener { updateData ->
                                if (updateData.isSuccessful) {
                                    REF_DATABASE_ROOT.child(NODE_KISHLISHS_USER).child(CURRENT_UID)
                                        .child(count).setValue(count)
                                        .addOnSuccessListener { showToast("Successful") }
                                        .addOnFailureListener { showToast(updateData.exception?.message.toString()) }
                                    APP_ACTIVITY.replaceActivity(MainActivity())
                                }
                            }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        showToast(error.message)
                    }
                })
        }
    }

}
