package binar.ganda.challengechapter6.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRetrofit {
    private const val BASE_URL = "https://apiexample.surelabsid.com/"

    private val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY}
        }

    private val clint = OkHttpClient.Builder().addInterceptor(logging).build()

    val INSTANCE : UserService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clint)
            .build()
        retrofit.create(UserService::class.java)
    }
}