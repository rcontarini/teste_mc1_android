package com.rcontarini.teste_mc1.ui.home

import android.content.Context
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.UserModel

class HomePresenter(private val mContext: Context) : HomeContract.Presenter {

    private var mView: HomeContract.View? = null
    private var mModel : UserModel = UserModel()

    override fun getUsers() {
        mView?.setUsers(mModel.getAllUsers())
    }

    override fun onClickItem(item: User) {
        mView?.startRegisterActivity(item)
    }

    override fun registerUser() {
        mView?.startRegisterActivity(null)
    }

    override fun deleteItem(user: User) {
        mModel.removeItem(user)
        getUsers()
        mView?.displayMsg(mContext.getString(R.string.register_delete))
    }

    override fun attachView(mvpView: HomeContract.View?) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }

}