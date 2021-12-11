package coursework.gi.kishlish.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import coursework.gi.kishlish.databinding.ActivityMainBinding
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.fragments.MainMenuFragment
import coursework.gi.kishlish.ui.utilits.AUTH
import coursework.gi.kishlish.ui.utilits.replaceActivity
import coursework.gi.kishlish.ui.utilits.replaceFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser!=null) {
            replaceFragment(MainMenuFragment(), false)
        } else {
                replaceActivity(RegisterActivity())
        }
    }

    private fun initFields() {
        AUTH = FirebaseAuth.getInstance()
    }
}