package com.rcontarini.teste_mc1.extensions

import com.google.android.material.textfield.TextInputLayout
import com.rcontarini.teste_mc1.util.CpfValidade

fun TextInputLayout.clear() {
    this.editText!!.text.clear()
}

fun TextInputLayout.validade() : Boolean {
    var isValidate = true

    if(this.editText!!.text.isNullOrEmpty()){
        this.error = "Campo Obrigatório"
        isValidate = false
    } else {
        this.error = ""
    }

    return isValidate
}

fun TextInputLayout.validadeCPF() : Boolean {
    var isCpfValid = true

    if(!CpfValidade.myValidateCPF(this.editText!!.text.toString())){
        this.error = "CPF Inválido!"
        isCpfValid = false
    } else {
        this.error = ""
    }
    return isCpfValid
}