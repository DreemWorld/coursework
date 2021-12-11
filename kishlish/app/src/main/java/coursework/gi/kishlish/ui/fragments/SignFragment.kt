package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.utilits.AUTH
import coursework.gi.kishlish.ui.utilits.replaceActivity
import coursework.gi.kishlish.ui.utilits.replaceFragment
import coursework.gi.kishlish.ui.utilits.showToast
import kotlinx.android.synthetic.main.fragment_login.*
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
            if (email.isEmpty() || password.isEmpty() || username.isEmpty())
            {
                showToast("Данные не введены")
            }
            else { createUser() }
        }
    }

    private fun createUser() {
        AUTH.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { create ->
                if (create.isSuccessful) {
                    showToast("Добро пожаловать")
                    (activity as RegisterActivity).replaceActivity(MainActivity())
                } else {
                    showToast(create.exception?.message.toString())
                }
            }
    }

}