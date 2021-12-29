package coursework.gi.kishlish.ui.utilits

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import coursework.gi.kishlish.ui.models.Kishlish
import coursework.gi.kishlish.ui.models.User

const val NODE_USERS = "users"

const val CHILD_ID = "id"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_EMAIL = "email"
const val CHILD_PASSWORD = "password"
const val CHILD_PHOTO_URL = "photoUrl"
const val CHILD_BIO = "bio"

const val NODE_KISHLISHS = "kishlishs"
const val CHILD_NAME = "name"
const val CHILD_DESCRIPTION = "description"
const val CHILD_PRICE = "price"
const val CHILD_CURRENT_USER = "current_user"

const val NODE_KISHLISHS_USER = "kishlishs_user"

const val FOLDER_PROFILE_IMAGES = "profile_images"
const val FOLDER_KISHLISH_IMAGES = "kishlish_images"
const val FOLDER_ARCHIVE_KISHLISH_IMAGES = "archive_kishlish_images"

lateinit var USER: User
lateinit var CURRENT_UID: String
lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference

inline fun putImageToStorage(uri: Uri, path: StorageReference, crossinline function: () -> Unit) {
    path.putFile(uri)
        .addOnSuccessListener { function() }
        .addOnFailureListener {
            showToast(it.message.toString())
        }
}

inline fun getUrlFromStorage(path: StorageReference, crossinline function: (url: String) -> Unit) {
    path.downloadUrl
        .addOnSuccessListener { function(it.toString()) }
        .addOnFailureListener { showToast(it.message.toString()) }
}

inline fun putUrlToDataBase(url: String, crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .child(CHILD_PHOTO_URL).setValue(url)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}

fun initUser() {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(User::class.java) ?: User()
            if (USER.username.isEmpty()) {
                USER.username = CURRENT_UID
            }
        })
}

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    USER = User()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
}

inline fun initUser(crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(User::class.java) ?: User()
            if (USER.username.isEmpty()) {
                USER.username = CURRENT_UID
            }
            function()
        })
}


fun DataSnapshot.getKishlishModel(): Kishlish =
    this.getValue(Kishlish::class.java) ?: Kishlish()
