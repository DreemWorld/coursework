package coursework.gi.kishlish.ui.fragments

import androidx.fragment.app.Fragment
import coursework.gi.kishlish.R
import coursework.gi.kishlish.ui.utilits.USER
import kotlinx.android.synthetic.main.fragment_profile_info_change.*

class ProfileInfoChangeFragment : Fragment(R.layout.fragment_profile_info_change) {

    override fun onStart() {
        super.onStart()
        initFields()
    }

    private fun initFields() {
        if (USER.fullname.isNotEmpty()) {
            val fullNameList = USER.fullname.split(' ')
            profile_info_change_name.setText(fullNameList[0])
            profile_info_change_surname.setText(fullNameList[1])
        }
        profile_info_change_username.setText(USER.username)
        profile_info_change_bio.setText(USER.bio)
    }

}