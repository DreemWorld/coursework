package coursework.gi.kishlish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import coursework.gi.kishlish.sign.SignInActivity

class StartingBootActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var toLoginWitchDelay: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting_boot)

        toLoginWitchDelay = Runnable {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        handler = Handler()
        handler.postDelayed(toLoginWitchDelay, SPLASH_TIME)

    }

    private companion object {
        private const val SPLASH_TIME: Long = 4500
    }
}