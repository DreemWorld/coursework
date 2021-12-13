package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.MainActivity
import coursework.gi.kishlish.ui.activity.RegisterActivity
import coursework.gi.kishlish.ui.utilits.AUTH
import coursework.gi.kishlish.ui.utilits.replaceActivity
import kotlinx.android.synthetic.main.fragment_profile_info.*

class ProfileInfoFragment : Fragment(R.layout.fragment_profile_info) {
    override fun onStart() {
        super.onStart()
        profile_info_exit.setOnClickListener {
            AUTH.signOut()
            (activity as MainActivity).replaceActivity(RegisterActivity())
        }
    }
}