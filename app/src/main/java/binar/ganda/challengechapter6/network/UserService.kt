package binar.ganda.challengechapter6.network

import binar.ganda.challengechapter6.model.DefaultResponse
import binar.ganda.challengechapter6.model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("register.php")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<DefaultResponse>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id")id : String,
        @Field("username")username : String,
        @Field("complete_name")completename : String,
        @Field("dateofbirth")dateofbirth : String,
        @Field("address")address : String
    ): Call<DefaultResponse>


    @GET("apiuser.php")
    fun getAllUser() : Call<List<ResponseDataUserItem>>
}