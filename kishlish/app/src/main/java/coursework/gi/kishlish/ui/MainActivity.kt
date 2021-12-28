package coursework.gi.kishlish.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.ActivityMainBinding
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.fragments.AddNewKishlishFragment
import coursework.gi.kishlish.ui.fragments.ProfileInfoFragment
import coursework.gi.kishlish.ui.models.UserModel
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY = this
        initFields()
        initFunc()

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> {
                    toolBar.title = "My KishLish"
                    replaceFragment(AddNewKishlishFragment(), addStack = false)
                    true
                }
                R.id.miProfile -> {
                    toolBar.title = "Profile"
                    replaceFragment(ProfileInfoFragment(), addStack = false)
                    true
                }
                else -> false
            }
        }

        main_add_kishlish.setOnClickListener {
            replaceFragment(
                AddNewKishlishFragment(),
                addStack = false
            )
        }
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(UserModel::class.java) ?: UserModel()
            })
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            replaceFragment(ProfileInfoFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }

    private fun initFields() {
        toolBar = supportActionBar!!
        AUTH = FirebaseAuth.getInstance()
        initFirebase()
        initUser()
        2
    }

}