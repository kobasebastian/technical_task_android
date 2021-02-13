package pl.kobasebastian.slidepracticaltest.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.kobasebastian.slidepracticaltest.data.GoRestApi
import pl.kobasebastian.slidepracticaltest.data.model.AddUserRequest
import pl.kobasebastian.slidepracticaltest.data.model.UsersResponse

class GoRestApiRepository(private val goRestApi: GoRestApi) {
    fun fetchUsers() : Observable<UsersResponse> {
        return Observable.create { emitter ->
            goRestApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    emitter.onNext(it)
                },{
                    emitter.onError(it)
                })
        }
    }

    fun fetchUsersForPage(pageId: Int) : Observable<UsersResponse> {
        return Observable.create { emitter ->
            goRestApi.getUsersForPage(pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    emitter.onNext(it)
                },{
                    emitter.onError(it)
                })
        }
    }

    fun addUser(name: String, email: String) : Completable {
        return Completable.create{ emitter ->
            goRestApi.createUser(AddUserRequest(name, email, "Male", "Active"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.code == 201) {
                        emitter.onComplete()
                    }
                },{
                    emitter.onError(it)
                })
        }
    }
}