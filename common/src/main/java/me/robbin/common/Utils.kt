package me.robbin.common

import android.app.Application
import android.content.Context
import java.lang.reflect.InvocationTargetException

object Utils {

    private lateinit var sApplication: Application

    fun init(context: Context) {
        return init(context as Application)
    }

    fun init(app: Application) {
        if (!::sApplication.isInitialized)
            this.sApplication = app
    }

    fun getApp(): Application {
        if (::sApplication.isInitialized)
            return sApplication
        val app = getApplicationByReflect()
        init(app)
        return app
    }

    @Suppress("privateApi")
    private fun getApplicationByReflect(): Application {
        try {
            val activityThread = Class.forName("android.app.ActivityThread")
            val thread = activityThread.getMethod("currentActivityThread").invoke(null)
            val app = activityThread.getMethod("getApplication").invoke(thread)
                ?: throw NullPointerException("u should init first")
            return app as Application
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodError) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        throw NullPointerException("u should init first")
    }

}