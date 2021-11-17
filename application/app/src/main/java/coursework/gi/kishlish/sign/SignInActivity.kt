package coursework.gi.kishlish.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coursework.gi.kishlish.R
import coursework.gi.kishlish.internet.Internet
import coursework.gi.kishlish.ui.Toasts

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (!Internet().isInternetConnection(this))
            {
                Toasts().shortToastShow(this, "Not connected")
            } else {
                Toasts().shortToastShow(this, "Connected")
            }
        }
    }
}