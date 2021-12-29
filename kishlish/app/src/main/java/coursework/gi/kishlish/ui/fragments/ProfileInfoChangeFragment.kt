package coursework.gi.kishlish.ui.fragments

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_profile_info.*
import kotlinx.android.synthetic.main.fragment_profile_info_change.*
import kotlinx.android.synthetic.main.fragment_sign.*

class ProfileInfoChangeFragment : Fragment(R.layout.fragment_profile_info_change) {


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        initFields()

    }

    private fun initFields() {
        profile_info_change_fullname.setText(USER.fullname)
        profile_info_change_username.setText(USER.username)
        profile_info_change_bio.setText(USER.bio)
        profile_info_change_profile_info.setOnClickListener {
            changeData()
            change()
        }
        profile_info_change_photo.setOnClickListener {
            changePhotoUser()
        }
    }

    private fun changeData() {
        val dataMap = mutableMapOf<String, Any>()
        dataMap[CHILD_BIO] = profile_info_change_bio.text.toString()
        dataMap[CHILD_FULLNAME] = profile_info_change_fullname.text.toString()

        if (dataMap[CHILD_USERNAME].toString().isEmpty() || dataMap[CHILD_FULLNAME].toString()
                .isEmpty()
        ) {
            showToast("Enter the data")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).updateChildren(dataMap)
                .addOnFailureListener { showToast(it.message.toString()) }
                .addOnSuccessListener {
                    USER.bio = dataMap[CHILD_BIO].toString()
                    USER.fullname = dataMap[CHILD_FULLNAME].toString()
                }
        }
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start(APP_ACTIVITY, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGES).child(CURRENT_UID)
            putImageToStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDataBase(it) {
                        showToast("запись в бд")
                        profile_info_image.downloadAndSetImage(it)
                        showToast("Dowload")
                        USER.photoUrl = it
                    }
                }
            }
        }
    }

    lateinit var newUsername: String

    fun change() {
        newUsername = profile_info_change_username.text.toString().lowercase()
        if (newUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if (it.hasChild(newUsername)) {
                        showToast("Такой пользователь существует")
                    } else {
                        changeUsername()
                    }
                })
        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(newUsername).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername()
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME).setValue(newUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUsername()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    USER.username = newUsername
                    fragmentManager?.popBackStack()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }
}