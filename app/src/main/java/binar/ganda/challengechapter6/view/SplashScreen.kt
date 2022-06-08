package binar.ganda.challengechapter6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.asLiveData
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.manager.UserManager

class SplashScreen : AppCompatActivity() {

    private lateinit var userManager: UserManager
    private lateinit var emailLogin : String
    private lateinit var passwordLogin :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        userManager = UserManager(this)

        userManager.userEmail.asLiveData().observe( this) {
            emailLogin = it.toString()
        }

        userManager.userPassword.asLiveData().observe( this) {
            passwordLogin = it.toString()
        }

        Handler().postDelayed({
            if (emailLogin.isNotEmpty() && passwordLogin.isNotEmpty()){
                startActivity(Intent(this@SplashScreen, Home::class.java))
            } else {
                val intent = Intent(this@SplashScreen, Login::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)
    }
}