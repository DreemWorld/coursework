package coursework.gi.kishlish.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import coursework.gi.kishlish.R
import coursework.gi.kishlish.databinding.ActivityMainBinding
import coursework.gi.kishlish.databinding.FragmentSignBinding
import coursework.gi.kishlish.ui.activity.MainActivity


class Sign : Fragment(R.layout.fragment_sign) {

    private lateinit var binding: FragmentSignBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var daatabaseReference: DatabaseReference
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
            navController.navigate(R.id.action_sign_to_login)
        }
        binding.btnRegister.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(
                binding.emailOrMobile.text.toString().trim(),
                binding.password.text.toString().trim()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = firebaseAuth.currentUser?.uid.toString()
                    var dateMap = mutableMapOf<String, Any>()
                    dateMap["id"] = uid
                    dateMap["name"] = binding.editTextUsernameSign.text
                    navController.navigate(R.id.action_sign_to_login)
                } else {
                    Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}