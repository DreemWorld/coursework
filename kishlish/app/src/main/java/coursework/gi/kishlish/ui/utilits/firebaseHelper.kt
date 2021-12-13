package coursework.gi.kishlish.ui.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import coursework.gi.kishlish.ui.models.User

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"

lateinit var USER: User
lateinit var UID: String
lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
}