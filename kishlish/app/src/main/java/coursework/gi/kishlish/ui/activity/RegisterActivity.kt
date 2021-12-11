package coursework.gi.kishlish.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coursework.gi.kishlish.databinding.ActivityRegisterBinding
import coursework.gi.kishlish.ui.fragments.LoginFragment
import coursework.gi.kishlish.ui.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        replaceFragment(LoginFragment(), false)
    }

}