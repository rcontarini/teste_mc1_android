package com.rcontarini.teste_mc1.ui.register

import android.content.Context
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.UserModel

class RegisterPresenter(private val mContext: Context) : RegisterContract.Presenter {

    private var mView: RegisterContract.View? = null
    private var mUserModel : UserModel = UserModel()

    override fun setError(error: Throwable) {
    }

    override fun saveUser(name: String, address: String, number: String, cpf: String) {
        mUserModel.saveUser(User(name = name, address = address, number = number.toLong(), cpf = cpf))
        mView?.cleanRegister()
        mView?.displayMsg(mContext.getString(R.string.register_ok))
    }

    override fun editUser(name: String, address: String, number: String, cpf: String, user: User) {
        user.address = address
        user.cpf = cpf
        user.name = name
        user.number = number.toLong()

        mUserModel.updateUser(user)
        mView?.cleanRegister()
        mView?.displayMsg(mContext.getString(R.string.register_edit))
    }

    override fun attachView(mvpView: RegisterContract.View?) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }

}