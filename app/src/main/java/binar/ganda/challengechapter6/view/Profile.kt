package binar.ganda.challengechapter6.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.manager.UserManager
import binar.ganda.challengechapter6.model.DefaultResponse
import binar.ganda.challengechapter6.model.ResponseDataUserItem
import binar.ganda.challengechapter6.network.UserRetrofit
import binar.ganda.challengechapter6.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class Profile : AppCompatActivity() {

    private lateinit var userManager : UserManager
    private lateinit var viewModelUser : UserViewModel
    private lateinit var listUser: List<ResponseDataUserItem>
    private lateinit var idLogin : String
    private lateinit var emailLogin : String
    private lateinit var passwordLogin : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setDataUser()

        update_btn.setOnClickListener {
            val usernameNew = edit_username.text.toString()
            val namaLengkapNew = edit_nama_lengkap.text.toString()
            val tanggalLahirNew = edit_tanggal_lahir.text.toString()
            val alamatNew = edit_alamat.text.toString()
            val id = idLogin
            updateUserData(id, usernameNew, namaLengkapNew, tanggalLahirNew, alamatNew)
        }


        logout_btn.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        AlertDialog.Builder(this)
            .setTitle("LOGOUT")
            .setMessage("Yakin ingin logout?")
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }.setPositiveButton("Ya"){ _: DialogInterface, _: Int ->

                GlobalScope.launch {
                    userManager.clearData()
                    startActivity(Intent(this@Profile, Login::class.java))
                }
            }.show()
    }

    private fun updateUserData(
        id : String,
        username : String,
        completeName : String,
        dateofbirth : String,
        address : String) {
        UserRetrofit.INSTANCE.updateUser(id ,username, completeName,dateofbirth,address)
            .enqueue(object : retrofit2.Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@Profile, response.message(), Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@Profile, response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(this@Profile, t.message, Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun setDataUser() {
            userManager = UserManager(this)

            userManager.userEmail.asLiveData().observe(this) {
                emailLogin = it.toString()
            }

            userManager.userPassword.asLiveData().observe(this) {
                passwordLogin = it.toString()
            }

            viewModelUser = ViewModelProvider(this)[UserViewModel::class.java]

            viewModelUser.getLivedataUser().observe(this) {
                listUser = it
                for (i in listUser.indices)
                    if (passwordLogin == listUser[i].password && emailLogin == listUser[i].email) {
                        val username = listUser[i].username
                        val namaLengkap = listUser[i].completeName
                        val tanggalLahir = listUser[i].dateofbirth
                        val alamat = listUser[i].address
                        idLogin = listUser[i].id

                        edit_username.setText(username)
                        edit_nama_lengkap.setText(namaLengkap)
                        edit_tanggal_lahir.setText(tanggalLahir)
                        edit_alamat.setText(alamat)
                    }

            }
        viewModelUser.callApiUser()
            }


}








