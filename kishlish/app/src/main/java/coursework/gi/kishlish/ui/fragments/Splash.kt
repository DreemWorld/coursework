package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coursework.gi.kishlish.R

class Splash : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed (
            {
                findNavController().navigate(R.id.action_splash_to_login)
            }, 3000)
    }
}