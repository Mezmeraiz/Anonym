package com.mezmeraiz.anonym.viewmodel

import android.content.Context
import android.databinding.Bindable
import android.support.v4.widget.SwipeRefreshLayout
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.mezmeraiz.anonym.BR
import com.mezmeraiz.anonym.R
import com.mezmeraiz.anonym.adapters.RealmAdapter
import com.mezmeraiz.anonym.common.MarginDecoration
import com.mezmeraiz.anonym.common.RootViewModel
import com.mezmeraiz.anonym.model.Post
import com.mezmeraiz.anonym.model.common.ItemClickValue
import com.mezmeraiz.anonym.network.PostService
import com.mezmeraiz.anonym.ui.views.PaginateRecyclerView
import io.reactivex.subjects.PublishSubject
import io.realm.RealmResults

class PostViewModel(val type: Int) : RootViewModel(){

    private val COUNT = 5

    var onClickPost = PublishSubject.create<ItemClickValue<Post>>()

    var onLoadMoreListener = object : PaginateRecyclerView.OnLoadMoreListener{
        override fun onLoadMore(offset: Int) {
            if (offset > 0){
                downloadPosts(offset)
            }
        }
    }

    var refreshListener = SwipeRefreshLayout.OnRefreshListener {
        downloadPosts(0, true)
    }

    @get:Bindable
    var refreshState: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.refreshState)
        }

    @get:Bindable
    var loading = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    @get:Bindable
    var posts: RealmResults<Post>? = null
        get() {
            if (field == null){
                field = realm.where(Post::class.java)
                        .equalTo("postType", type).findAll()
            }
            return field
        }

    @get:Bindable
    var adapter: RealmAdapter<Post>? = null
        get() {
            val adapter = RealmAdapter(this, posts!!, R.layout.item_post)
            adapter.onItemClick.subscribe{
                onClickPost.onNext(it)
            }
            field = adapter
            return field
        }

    var decoration: MarginDecoration? = null
        get() {
            if(field == null){
                field = MarginDecoration(context.resources.getDimension(R.dimen.weather_item_margin).toInt(),
                        context.resources.getDimension(R.dimen.weather_item_margin).toInt())
            }
            return field
        }

    override fun onCreate(context: Context) {
        super.onCreate(context)
        if (posts!!.size == 0){
            downloadPosts(0)
        }
    }

    fun downloadPosts(offset: Int, clear: Boolean = false){
        loading = true
        PostService()
                .getPosts(type, COUNT, offset)
                .subscribe({
                    loading = false
                    it.data?.forEach {
                        it.postType = type
                    }
                    realm.executeTransaction { realm ->
                        if (clear){
                            realm.where(Post::class.java).equalTo("postType", type)
                                    .findAll().deleteAllFromRealm()
                        }
                        realm.insertOrUpdate(it.data)
                    }
                    refreshState = false
                },{
                    if (it is HttpException){
                        error.onNext(it.response().errorBody().string())
                    }
                    refreshState = false
                })
    }

}