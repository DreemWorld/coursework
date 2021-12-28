package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_sign.*


class SignFragment : Fragment(R.layout.fragment_sign) {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String

    override fun onStart() {
        super.onStart()
        label_to_login.setOnClickListener { replaceFragment(LoginFragment()) }
        btn_register.setOnClickListener {
            email = sign_email.text.toString()
            password = sign_password.text.toString()
            username = sign_username.text.toString()
            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                showToast("Enter the data")
            } else {
                createUserAndDatabaseFields()
            }
        }
    }

    private fun createUserAndDatabaseFields() {
        AUTH.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { create ->
                if (create.isSuccessful) {

                    val uid = AUTH.currentUser?.uid.toString()
                    val dataMap = mutableMapOf<String, Any>()

                    dataMap[CHILD_ID] = uid
                    dataMap[CHILD_EMAIL] = sign_email.text.toString()
                    dataMap[CHILD_USERNAME] = sign_username.text.toString()
                    dataMap[CHILD_PASSWORD] = sign_password.text.toString()
                    dataMap[CHILD_FULLNAME] = ""
                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                        .addOnCompleteListener { updateData ->
                            if (updateData.isSuccessful) {
                                showToast("Welcome")
                                APP_ACTIVITY.replaceActivity(MainActivity())
                            } else {
                                showToast(updateData.exception?.message.toString())
                            }
                        }
                } else {
                    showToast(create.exception?.message.toString())
                }
            }

    }

}