package com.rcontarini.teste_mc1.ui.register

import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.base.BasePresenterApp
import com.rcontarini.teste_mc1.ui.base.BaseViewApp

interface RegisterContract {

    interface View : BaseViewApp<Presenter> {
        fun displayMsg(msg: String?)
        fun cleanRegister()
    }

    interface Presenter : BasePresenterApp<View> {
        fun setError(error : Throwable)
        fun saveUser(name : String, address : String, number : String, cpf : String)
        fun editUser(name: String, address: String, number: String, cpf: String, user: User)
    }

}