package com.proway.crud

import android.app.Application
import android.content.Context

class CRUDApplication: Application() {

    companion object {
        var contextCRUDApp : Context? = null
    }


    override fun onCreate() {
        super.onCreate()

        contextCRUDApp = applicationContext


    }

}