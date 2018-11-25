package com.mezmeraiz.anonym.common

import android.content.Context
import android.databinding.BaseObservable
import io.reactivex.subjects.PublishSubject
import io.realm.Realm

open class RootViewModel() : BaseObservable() {

    var realm : Realm private set

    lateinit var context : Context

    var error: PublishSubject<String> = PublishSubject.create()

    init {
        realm = Realm.getDefaultInstance()
    }

    open fun onCreate(context: Context){
        this.context = context
    }

    open fun onStop(){

    }

    open fun onPause(){

    }

    open fun onResume(){

    }

    open fun onStart(){

    }

    open fun onLoad(){

    }
}