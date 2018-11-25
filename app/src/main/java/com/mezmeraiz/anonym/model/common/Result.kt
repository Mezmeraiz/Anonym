package com.mezmeraiz.anonym.model.common

import com.google.gson.annotations.Expose

class Result<T> {

    @Expose
    var error: Boolean? = null

    @Expose
    var code: Int? = null

    @Expose
    var data: T? = null

}