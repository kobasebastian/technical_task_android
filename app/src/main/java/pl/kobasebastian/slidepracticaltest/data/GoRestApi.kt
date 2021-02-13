package pl.kobasebastian.slidepracticaltest.data

import io.reactivex.Flowable
import io.reactivex.Single
import pl.kobasebastian.slidepracticaltest.data.model.AddUserRequest
import pl.kobasebastian.slidepracticaltest.data.model.AddUserResponse
import pl.kobasebastian.slidepracticaltest.data.model.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GoRestApi {
    @GET("users/")
    fun getUsers(): Flowable<UsersResponse>

    @GET("users")
    fun getUsersForPage(@Query("page") page: Int): Flowable<UsersResponse>

    @POST("users/")
    fun createUser(@Body addUserRequest: AddUserRequest) : Single<AddUserResponse>
}