package binar.ganda.challengechapter6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.model.DefaultResponse
import binar.ganda.challengechapter6.network.UserRetrofit
import binar.ganda.challengechapter6.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn.setOnClickListener {
            val username = input_username.text.toString()
            val password = input_password.text.toString()
            val email = input_email.text.toString()
            val konfirmasiPassword = input_confirm_password.text.toString()

            if (username.isEmpty() && password.isEmpty() && email.isEmpty() &&
                konfirmasiPassword.isEmpty()
            ) {
                Toast.makeText(this, "Harus Diisi", Toast.LENGTH_LONG).show()
            } else {
                    if (password == konfirmasiPassword) {
                            registerUserViewModel()
                            Toast.makeText(this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Password dan konfirmasi password harus sama", Toast.LENGTH_SHORT).show()
                    }


            }
        }
    }

    private fun registerUserViewModel() {

        val username = input_username.text.toString()
        val password = input_password.text.toString()
        val email = input_email.text.toString()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.getLivedataUser().observe(this, Observer {
            postDataUser(email,username,password)
        })

        viewModel.callApiUser()
    }

    private fun postDataUser(
        email : String,
        username : String,
        password : String
    ) {
        UserRetrofit.INSTANCE.registerUser(email, username, password)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@Register, response.message(), Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@Register, Login::class.java))
                    } else {
                        Toast.makeText(this@Register, response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(this@Register, t.message, Toast.LENGTH_LONG).show()
                }


            })
    }
}