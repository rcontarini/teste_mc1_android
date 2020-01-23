package com.rcontarini.teste_mc1.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rcontarini.teste_mc1.R
import com.rcontarini.teste_mc1.model.User
import kotlinx.android.synthetic.main.item_usuario.view.*

class HomeAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(list[position], onItemClickListener)
        }
    }

    fun setListUser(listUser: List<User>){
        list = listUser
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClicked( item : User )
        fun onItemDelete( item : User )
    }

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(item : User, onItemClickListerner: OnItemClickListener){

            itemView.apply {
                setOnClickListener {
                    onItemClickListerner.onItemClicked( item )
                }

                im_delete.setOnClickListener {
                    onItemClickListerner.onItemDelete( item )
                }

                tv_name.text = item.name
                tv_address.text = item.address
                tv_cpf.text = item.cpf
                tv_number.text = item.number.toString()

            }
        }
    }
}