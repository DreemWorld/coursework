package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.ActivityMainBinding
import coursework.gi.kishlish.databinding.FragmentSignBinding
import coursework.gi.kishlish.ui.activity.MainActivity


class Sign : Fragment() {

    lateinit var binding: FragmentSignBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewLoginJump.setOnClickListener {
            findNavController().navigate(R.id.action_sign_to_login)
        }
    }

}