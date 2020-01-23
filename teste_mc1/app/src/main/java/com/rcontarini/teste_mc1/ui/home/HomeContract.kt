package com.rcontarini.teste_mc1.ui.home

import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.base.BasePresenterApp
import com.rcontarini.teste_mc1.ui.base.BaseViewApp


interface HomeContract {

    interface View : BaseViewApp<Presenter> {
        fun displayMsg(msg: String?)
        fun setUsers(users : List<User>)
        fun startRegisterActivity(item : User?)
    }

    interface Presenter : BasePresenterApp<View> {
        fun getUsers()
        fun onClickItem(item : User)
        fun registerUser()
        fun deleteItem(user: User)
    }


}