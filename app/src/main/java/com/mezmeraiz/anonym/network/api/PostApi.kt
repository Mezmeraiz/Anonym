package com.mezmeraiz.anonym.network.api

import com.mezmeraiz.anonym.model.Post
import com.mezmeraiz.anonym.model.common.Result
import com.mezmeraiz.anonym.model.request.FeedRequest
import io.reactivex.Single
import io.realm.RealmList
import retrofit2.http.*

interface PostApi {

    @POST("posts/get")
    fun getPosts(@Body body: FeedRequest): Single<Result<RealmList<Post>>>

}