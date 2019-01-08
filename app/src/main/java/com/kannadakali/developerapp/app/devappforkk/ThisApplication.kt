package com.kannadakali.developerapp.app.devappforkk

import android.app.Application
import android.content.Context

/**
 * Created by varun.am on 08/01/19
 */
class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        private var context: Context? = null

        fun getContext(): Context{
            return context!!
        }
    }
}