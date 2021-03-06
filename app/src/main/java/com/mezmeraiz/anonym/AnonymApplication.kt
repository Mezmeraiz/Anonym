package com.mezmeraiz.anonym

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class AnonymApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm(){
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }
}