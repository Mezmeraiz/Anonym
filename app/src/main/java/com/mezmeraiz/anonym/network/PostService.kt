package com.mezmeraiz.anonym.network

import com.mezmeraiz.rumyancevo.network.common.ServiceImpl
import com.mezmeraiz.anonym.model.Post
import com.mezmeraiz.anonym.model.common.Result
import com.mezmeraiz.anonym.model.request.FeedRequest
import com.mezmeraiz.anonym.network.api.PostApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmList

class PostService : ServiceImpl<PostApi>(PostApi::class.java) {

    fun getPosts(type: Int, count: Int, offset: Int): Single<Result<RealmList<Post>>>{
        return getService().getPosts(FeedRequest(type, count, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}