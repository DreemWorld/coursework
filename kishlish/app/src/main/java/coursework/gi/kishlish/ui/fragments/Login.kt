package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.FragmentLoginBinding

class Login : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.textViewSignUpJump.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_sign)
            }
    }
}