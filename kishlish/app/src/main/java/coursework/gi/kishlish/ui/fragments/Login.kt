package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.FragmentLoginBinding

class Login : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController by lazy { findNavController() }

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
        binding.labelToRegister.setOnClickListener {
            navController.navigate(R.id.action_login_to_sign)
        }
        binding.btnLogin.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signInWithEmailAndPassword(
                binding.email.text.toString().trim(),
                binding.loginPassword.text.toString().trim()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    navController.navigate(R.id.action_login_to_mainMenu)
                } else {
                    Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}