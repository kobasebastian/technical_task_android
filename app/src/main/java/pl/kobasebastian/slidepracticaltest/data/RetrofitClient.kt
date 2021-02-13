package pl.kobasebastian.slidepracticaltest.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL = "https://gorest.co.in/public-api/"
    private const val ACCESS_TOKEN = "1863e0c1a5e3bc4a90ed9d16aadff2b73b9a6fe84ad3dfc8e1ea5aeace1509e2"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                .build()
            chain.proceed(request)
        }

    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    val goRestApi: GoRestApi by lazy {
        retrofitClient
            .build()
            .create(GoRestApi::class.java)
    }
}