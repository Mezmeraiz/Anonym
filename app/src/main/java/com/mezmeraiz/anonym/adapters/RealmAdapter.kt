package com.mezmeraiz.anonym.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mezmeraiz.anonym.BR
import com.mezmeraiz.anonym.common.RootViewModel
import com.mezmeraiz.anonym.model.common.ItemClickValue
import io.reactivex.subjects.PublishSubject
import io.realm.RealmObject
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults


class RealmAdapter<M: RealmObject>(val viewModel: RootViewModel, val data: RealmResults<M>, val layout: Int)
    : RealmRecyclerViewAdapter<M, RealmAdapter.ViewHolder>(data, true) {

    val onItemClick: PublishSubject<ItemClickValue<M>> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewModel.context).inflate(layout, parent, false)
        return RealmAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = getItem(position) as M
        holder?.binding?.setVariable(BR.viewModel, viewModel)
        holder?.binding?.setVariable(BR.item, item)
        holder?.binding?.setVariable(BR.position, position)
        holder?.binding?.executePendingBindings()
        holder?.itemView?.setOnClickListener {
            onItemClick.onNext(ItemClickValue<M>(position, item))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewDataBinding = DataBindingUtil.bind(itemView)
    }

}