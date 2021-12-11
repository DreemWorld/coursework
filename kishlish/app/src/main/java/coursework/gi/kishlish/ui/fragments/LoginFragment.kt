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



class LoginFragment() : Fragment(R.layout.fragment_login) {

    private lateinit var email: String
    private lateinit var password: String

    override fun onStart() {
        super.onStart()

        label_to_register.setOnClickListener {
            replaceFragment(SignFragment()) }


        btn_login.setOnClickListener {
            email = login_email.text.toString()
            password = login_password.text.toString()
            if (email.isEmpty() || password.isEmpty()) { showToast("Данные не введены") }
            else { checkData() }
        }
    }

    private fun checkData() {
        AUTH.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { check ->
                if (check.isSuccessful) {
                    showToast("Добро пожаловать")
                    (activity as RegisterActivity).replaceActivity(MainActivity())
                } else {
                    showToast(check.exception?.message.toString())
                }
            }
    }
}