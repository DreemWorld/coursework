package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.FragmentSignBinding


class Sign : Fragment(R.layout.fragment_sign) {

    private lateinit var binding: FragmentSignBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.labelToLogin.setOnClickListener {
            navController.navigate(R.id.label_to_login)
        }
        binding.btnRegister.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(
                binding.emailOrMobile.text.toString().trim(),
                binding.password1.text.toString().trim()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    navController.navigate(R.id.label_to_login)
                } else {
                    Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}