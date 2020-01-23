package com.rcontarini.teste_mc1.ui.register

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.extensions.clear
import com.rcontarini.teste_mc1.extensions.validade
import com.rcontarini.teste_mc1.extensions.validadeCPF
import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.base.BaseActivity
import com.rcontarini.teste_mc1.util.Mask
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*
import com.rcontarini.teste_mc1.ConstantsApp
import org.jetbrains.anko.intentFor

class RegisterActivity : BaseActivity(), RegisterContract.View {

    private var mUser : User? = null

    private val mPresenter : RegisterContract.Presenter by lazy {
        val presenter = RegisterPresenter(this)
        presenter.attachView(this)
        presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
        setListener()
    }

    private fun setup(){
        setToolbar(getString(R.string.register_title), true)
        et_cpf.addTextChangedListener(Mask.mask("###.###.###-##", et_cpf))

        if(intent.getSerializableExtra(ConstantsApp.USER) != null){
            mUser = intent.getSerializableExtra(ConstantsApp.USER) as User
            setUser()
        }
    }

    private fun setUser(){
        et_name.setText(mUser!!.name)
        et_address.setText(mUser!!.address)
        et_number.setText(mUser!!.number.toString())
        et_cpf.setText(mUser!!.cpf)
    }

    private fun setListener(){
        btn_save.setOnClickListener {
           if(validade()){
               if(mUser == null) {
                   mPresenter.saveUser(et_name.text.toString(),
                           et_address.text.toString(),
                           et_number.text.toString(),
                           et_cpf.text.toString())
               } else {
                   mPresenter.editUser(et_name.text.toString(),
                           et_address.text.toString(),
                           et_number.text.toString(),
                           et_cpf.text.toString(), mUser!!)
               }
           }
        }
    }

    private fun validade() : Boolean {
        var isValid = true
        listOf(ti_cpf,
            ti_name,
            ti_address,
            ti_number).forEach {
            if(!it.validade()){
                isValid = false
            }
        }

        if(!ti_cpf.validadeCPF()){
            isValid = false
        }
        return isValid
    }

    override fun displayMsg(msg: String?) {
        Snackbar.make(content_register, msg!!, Snackbar.LENGTH_LONG).show()
    }


    override fun cleanRegister() {
        listOf(ti_cpf,
            ti_name,
            ti_address,
            ti_number).forEach {
                it.clear()
        }

        ti_name.requestFocus()
    }

}

fun Context.createRegisterIntent(item : User?) = intentFor<RegisterActivity>(
   ConstantsApp.USER to item
)