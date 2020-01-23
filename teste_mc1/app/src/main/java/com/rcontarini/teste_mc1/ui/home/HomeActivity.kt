package com.rcontarini.teste_mc1.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.comtex.meeting.ui.dialog.GenericDialog
import com.comtex.meeting.ui.dialog.GenericDialogImageType
import com.google.android.material.snackbar.Snackbar
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.extensions.setup
import com.rcontarini.teste_mc1.extensions.startActivitySlideTransition
import com.rcontarini.teste_mc1.model.User
import com.rcontarini.teste_mc1.ui.base.BaseActivity
import com.rcontarini.teste_mc1.ui.register.createRegisterIntent
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity(), HomeContract.View {

    private val mPresenter : HomeContract.Presenter by lazy {
        val presenter = HomePresenter(this)
        presenter.attachView(this)
        presenter
    }

    private val mAdapter by lazy {
        val adapter = HomeAdapter(context, object : HomeAdapter.OnItemClickListener {
            override fun onItemClicked(item: User) {
                mPresenter.onClickItem(item)
            }

            override fun onItemDelete(item: User) {
                questionDelete(item)
            }
        })
        rv_home.setup(adapter, layoutManager = LinearLayoutManager(context))
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        setListeners()
    }

    private fun setListeners(){
        floatingActionButton.setOnClickListener { mPresenter.registerUser() }
    }


    private fun setup(){
        setToolbar(getString(R.string.home_title))
    }

    private fun questionDelete(item : User){
        GenericDialog(this, imageType = GenericDialogImageType.QUESTION, title = getString(R.string.dialog_alert), htmlSubtitle = getString(R.string.dialog_title),
            negativeButtonTitle = getString(R.string.btn_no), positiveButtonTitle = getString(R.string.btn_yes), onDialogClickListener = object : GenericDialog.OnDialogClickListener{
                override fun onDialogPositiveClicked() {
                    mPresenter.deleteItem(item)
                }

                override fun onDialogNegativeClicked() {
                }

            }, isCancelable = false).show()
    }

    override fun displayMsg(msg: String?) {
        Snackbar.make(content_main, msg!!, Snackbar.LENGTH_LONG).show()
    }

    override fun setUsers(users: List<User>) {
        mAdapter.setListUser(users)
    }

    override fun startRegisterActivity(item: User?) {
        startActivitySlideTransition(createRegisterIntent(item))
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getUsers()
    }
}
