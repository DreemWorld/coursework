package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.utilits.AUTH
import coursework.gi.kishlish.ui.utilits.USER
import coursework.gi.kishlish.ui.utilits.replaceActivity
import coursework.gi.kishlish.ui.utilits.replaceFragment
import kotlinx.android.synthetic.main.fragment_profile_info.*

class ProfileInfoFragment : Fragment(R.layout.fragment_profile_info) {
    override fun onStart() {
        super.onStart()
        profile_info_exit.setOnClickListener {
            AUTH.signOut()
            (activity as MainActivity).replaceActivity(RegisterActivity())
        }
        profile_info_change.setOnClickListener { replaceFragment(ProfileInfoChangeFragment()) }
    }

    override fun onResume() {
        super.onResume()
        initFields()
    }

    private fun initFields() {
        profile_info_bio.text = USER.password
        profile_info_fullname.text = USER.id
        profile_info_username.text = USER.username

        // TODO add image from storage profile_info_image
    }
}