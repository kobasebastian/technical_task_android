package pl.kobasebastian.slidepracticaltest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kobasebastian.slidepracticaltest.data.RetrofitClient
import pl.kobasebastian.slidepracticaltest.data.model.UsersResponse
import pl.kobasebastian.slidepracticaltest.data.repository.GoRestApiRepository

class MainViewModel : ViewModel() {
    val goRestApiRepository: GoRestApiRepository = GoRestApiRepository(RetrofitClient.goRestApi)
    private val _usersResponse : MutableLiveData<UsersResponse> = MutableLiveData()
    val usersResponse : LiveData<UsersResponse> = _usersResponse

    fun getUsers() {
        goRestApiRepository
            .fetchUsers()
            .subscribe{
                if(it.meta.pagination.pages != 0) {
                    getUsersForPage(it.meta.pagination.pages)
                }
            }
    }

    fun getUsersForPage(pageId: Int) {
        goRestApiRepository
            .fetchUsersForPage(pageId)
            .subscribe{
                _usersResponse.postValue(it)
            }
    }

    fun createUser(name: String, email: String) {
        goRestApiRepository
            .addUser(name, email)
            .subscribe {
                getUsers()
            }
    }
}