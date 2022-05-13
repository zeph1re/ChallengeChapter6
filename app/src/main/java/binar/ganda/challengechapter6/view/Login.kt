package binar.ganda.challengechapter6.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.ganda.challengechapter6.manager.UserManager
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Login : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    lateinit var userManager: UserManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userManager = UserManager(this)

        login_btn.setOnClickListener {
            val emailInput = input_email.text.toString()
            val passwordInput = input_password.text.toString()

            if (emailInput.isEmpty() && passwordInput.isEmpty()) {
                Toast.makeText(this, "Email dan Password harus di isi", Toast.LENGTH_LONG).show()
            } else {
                getUserUseViewModel()
            }
        }

        belum_punya_akun_btn.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUserUseViewModel() {

        if (input_email.text.isNotEmpty() && input_password.text.isNotEmpty()){
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

            viewModel.getLivedataUser().observe(this, Observer {
                for (i in it.indices) {
                    val emailInput = input_email.text.toString()
                    val passwordInput = input_password.text.toString()

                    if (passwordInput == it[i].password && emailInput == it[i].email) {
                        Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()
                        GlobalScope.launch {
                            userManager.saveData(
                                it[i].address,
                                it[i].completeName,
                                it[i].createdAt,
                                it[i].dateofbirth,
                                it[i].email,
                                it[i].id,
                                it[i].password,
                                it[i].username
                            )
                            startActivity(Intent(this@Login, Home::class.java))
                        }
                    }
                }
            })
            viewModel.callApiUser()
        } else {
            Toast.makeText(this,"Email dan Password harus di isi", Toast.LENGTH_LONG).show()
        }


    }

}
