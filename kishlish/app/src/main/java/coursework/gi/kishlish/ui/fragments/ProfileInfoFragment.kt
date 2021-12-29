package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.utilits.*
import kotlinx.android.synthetic.main.fragment_profile_info.*

class ProfileInfoFragment : Fragment(R.layout.fragment_profile_info) {
    override fun onStart() {
        super.onStart()

        profile_info_exit.setOnClickListener {
            AUTH.signOut()
            APP_ACTIVITY.replaceActivity(RegisterActivity())
        }
        profile_info_change.setOnClickListener { replaceFragment(ProfileInfoChangeFragment()) }
    }

    override fun onResume() {
        super.onResume()
        initFields()
    }

    private fun initFields() {
        profile_info_bio.text = USER.bio
        profile_info_fullname.text = USER.fullname
        profile_info_username.text = USER.username
        profile_info_image.downloadAndSetImage(USER.photoUrl)
    }
}