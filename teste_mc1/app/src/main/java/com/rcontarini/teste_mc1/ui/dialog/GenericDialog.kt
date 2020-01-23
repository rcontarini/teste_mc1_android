package com.comtex.meeting.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.extensions.getDrawableCompat
import com.rcontarini.teste_mc1.ui.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_generic.*

enum class GenericDialogImageType(@DrawableRes private val drawableRes: Int) {
    QUESTION(R.drawable.ic_question_answer_black_24dp);

    fun getDrawable(context: Context) = context.getDrawableCompat(drawableRes)
}


class GenericDialog(context: Context, private val imageType: GenericDialogImageType, private val title: String? = null, private val htmlSubtitle: String? = null,
                    private val negativeButtonTitle: String? = null, private val positiveButtonTitle: String,
                    private val onDialogClickListener: OnDialogClickListener? = null,
                    private val onCancelListener: DialogInterface.OnCancelListener? = null,
                    private val isCancelable: Boolean = true) : BaseDialog(context) {

    interface OnDialogClickListener {
        fun onDialogPositiveClicked()
        fun onDialogNegativeClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_generic)
        setListeners()
        setViews()
        setCancelable(isCancelable)
    }

    private fun setListeners() {
        genericDialogPositiveBtn.setOnClickListener {
            onDialogClickListener?.onDialogPositiveClicked()
            dismiss()
        }

        genericDialogNegativeBtn.setOnClickListener {
            onDialogClickListener?.onDialogNegativeClicked()
            dismiss()
        }

        genericDialogSuccessBtn.setOnClickListener {
            onDialogClickListener?.onDialogPositiveClicked()
            dismiss()
        }

        onCancelListener?.let { listener ->
            setOnCancelListener(listener)
        }
    }

    private fun setViews() {
        setTextIfNotNull(genericDialogTitleTv, title)

        setTextIfNotNull(genericDialogBodyTv, htmlSubtitle)

        if (!negativeButtonTitle.isNullOrBlank()) {
            genericDialogSuccessBtn.visibility = GONE
            genericDialogNegativeBtn.visibility = VISIBLE
            genericDialogNegativeBtn.text = negativeButtonTitle
            genericDialogPositiveBtn.text = positiveButtonTitle
        } else {
            genericDialogButtonsLl.visibility = GONE
            genericDialogSuccessBtn.visibility = VISIBLE
            genericDialogSuccessBtn.text = positiveButtonTitle
        }
        genericDialogImg.setImageDrawable(imageType.getDrawable(context))
    }

    private fun setTextIfNotNull(textView: TextView, string: String?) {
        if (!string.isNullOrBlank()) {
            textView.visibility = VISIBLE
            textView.text = string
        } else {
            textView.visibility = GONE
        }
    }
}