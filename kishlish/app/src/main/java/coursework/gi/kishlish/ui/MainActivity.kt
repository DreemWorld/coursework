package coursework.gi.kishlish.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.snap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.ActivityMainBinding
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.fragments.MainMenuFragment
import coursework.gi.kishlish.ui.fragments.ProfileInfoFragment
import coursework.gi.kishlish.ui.models.User
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.activity_main.*
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

        main_menu_menu.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.main_menu_menu_profile -> { replaceFragment(ProfileInfoFragment()) }
                R.id.main_menu_menu_friends -> { replaceFragment(ProfileInfoFragment()) }
            }
        }
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(User::class.java) ?: User()
            })
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
        initFirebase()
        initUser()
    }
}