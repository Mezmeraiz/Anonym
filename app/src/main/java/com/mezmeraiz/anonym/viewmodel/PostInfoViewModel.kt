package com.mezmeraiz.anonym.viewmodel

import com.mezmeraiz.anonym.common.RootViewModel
import com.mezmeraiz.anonym.model.Post
import com.squareup.picasso.Callback
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

class PostInfoViewModel(postId: Int): RootViewModel(){

    var onImageReady = PublishSubject.create<Unit>()

    var post = realm.where(Post::class.java)
            .equalTo("id", postId).findFirst()


    var picassoCallback = object : Callback{

        override fun onSuccess() {
            onImageReady.onNext(Unit)
        }

        override fun onError(e: Exception?) {
            onImageReady.onNext(Unit)
        }

    }

}