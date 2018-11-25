package com.mezmeraiz.anonym.model

import com.google.gson.annotations.Expose
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Post : RealmObject(){

    @Expose
    var id: Int? = null

    @Expose
    var postviews: Item? = null

    @Expose
    var text: String? = null

    @Expose
    var lang: String? = null

    @Expose
    var author_watch: Int? = null

    @Expose
    var status: Int? = null

    @Expose
    var adult: Int? = null

    @Expose
    var hidden: Int? = null

    @Expose
    var category: Int? = null

    @Expose
    var filter: Int? = null

    @Expose
    var category_string: String? = null

    @Expose
    var owner_id: Int? = null

    @Expose
    var type: Int? = null

    @Expose
    var open_comments: Int? = null

    @Expose
    var date: Long? = null

    @Expose
    var reposts: Int? = null

    @Expose
    var likes: Item? = null

    @Expose
    var dislikes: Item? = null

    @Expose
    var comments: Item? = null

    @Expose
    var tags: RealmList<String>? = null

    @Expose
    var attachments: RealmList<Attachment>? = null

    var postType: Int? = null

}

@RealmClass
open class Attachment : RealmObject(){

    @Expose
    var link: String? = null

    @Expose
    var photo: Photo? = null

    @Expose
    var type: String? = null

}

@RealmClass
open class Photo : RealmObject(){

    @Expose
    var photo_big: String? = null

    @Expose
    var photo_medium: String? = null

    @Expose
    var photo_small: String? = null

    @Expose
    var size: Size? = null

}

@RealmClass
open class Size : RealmObject(){

    @Expose
    var photo_small: PhotoDimens? = null

    @Expose
    var photo_medium: PhotoDimens? = null
}

@RealmClass
open class PhotoDimens : RealmObject(){

    @Expose
    var width: String? = null

    @Expose
    var height: String? = null

}

@RealmClass
open class Item : RealmObject(){

    @Expose
    var count: Int? = null

    @Expose
    var my: Int? = null

}



