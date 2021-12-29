package coursework.gi.kishlish.ui.fragments

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.theartofdev.edmodo.cropper.CropImage
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.models.Kishlish
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_add_new_kishlish.*
import kotlinx.android.synthetic.main.fragment_profile_info.*

class AddNewKishlishFragment : Fragment(R.layout.fragment_add_new_kishlish) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = "Add kishlish"
    }

    override fun onResume() {
        super.onResume()

        add_kish_with_image.setOnClickListener {
        }

        add_kish_btn.setOnClickListener {
            addKishlish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_KISHLISH_IMAGES).child(CURRENT_UID)
            putImageToStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDataBase(it) {
                        Kishlish.photoUrl = it
                        addKishlish()
                    }
                }
            }
        }
    }

    fun addKishlish() {
        val dataMap = mutableMapOf<String, Any>()
        dataMap[CHILD_CURRENT_USER] = USER.username
        dataMap[CHILD_NAME] = add_kish_name.text.toString()
        dataMap[CHILD_DESCRIPTION] = add_kish_description.text.toString()
        dataMap[CHILD_PRICE] = add_kish_price.text.toString()
        dataMap[CHILD_PHOTO_URL] = Kishlish.photoUrl
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
    private fun changePhotoKishlish() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start(APP_ACTIVITY, this)
    }
}
