package me.robbin.wanandroid.app.utils

/**
 * Snackbar Utils
 * Create by Robbin at 2020/7/20
 */
//fun View.showMsg(msg: String) {
//    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
//}
//
//object SnackbarUtils {
//
//    // Duration
//    const val LENGTH_LONG = Snackbar.LENGTH_LONG
//    const val LENGTH_SHORT = Snackbar.LENGTH_SHORT
//    const val LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE
//
//    // Default Color
//    @ColorInt
//    private const val COLOR_DEFAULT = 0xFF2C2C2C
//    private const val COLOR_SUCCESS = 0xFF52C41A
//    private const val COLOR_WARNING = 0xFFFAAD14
//    private const val COLOR_ERROR = 0xFFE84335
//    private const val COLOR_MSG = 0xFFFFFFFF
//
////    private const val COLOR_DEFAULT = -0x1000001
////    private const val COLOR_SUCCESS = -0xd44a00
////    private const val COLOR_WARNING = -0x3f00
////    private const val COLOR_ERROR   = -0x10000
////    private const val COLOR_MESSAGE = -0x1
//
//    private var msg: CharSequence = ""
//    private var actionMsg: CharSequence = ""
//    private var duration: Int = Snackbar.LENGTH_SHORT
//    private var bgColor: Int = COLOR_DEFAULT.toInt()
//    private var msgColor: Int = COLOR_MSG.toInt()
//    private var actionMsgColor: Int = COLOR_MSG.toInt()
//    private var bgResource: Int = -1
//    private var action: () -> Unit = {}
//    private var parent: View? = null
//
//    private var sReference by Weak<Snackbar>()
//
//    fun with(parent: View): SnackbarUtils {
//        this.parent = parent
//        return this
//    }
//
//    fun setMsg(msg: CharSequence): SnackbarUtils {
//        this.msg = msg
//        return this
//    }
//
//    fun setMsgColor(@ColorInt msgColor: Int): SnackbarUtils {
//        this.msgColor = msgColor
//        return this
//    }
//
//    fun setBgColor(@ColorInt bgColor: Int): SnackbarUtils {
//        this.bgColor = bgColor
//        return this
//    }
//
//    fun setBgResource(@DrawableRes bgResource: Int): SnackbarUtils {
//        this.bgResource = bgResource
//        return this
//    }
//
//    fun setDuration(duration: Int): SnackbarUtils {
//        this.duration = when (duration) {
//            this.LENGTH_LONG -> Snackbar.LENGTH_LONG
//            this.LENGTH_SHORT -> Snackbar.LENGTH_SHORT
//            else -> Snackbar.LENGTH_INDEFINITE
//        }
//        return this
//    }
//
//    fun setAction(
//        @NonNull actionMsg: CharSequence,
//        @ColorInt actionMsgColor: Int = this.actionMsgColor,
//        @NonNull action: () -> Unit
//    ): SnackbarUtils {
//        this.actionMsg = actionMsg
//        this.actionMsgColor = actionMsgColor
//        this.action = action
//        return this
//    }
//
//    fun show(isTop: Boolean = false): Snackbar? {
//        val view = parent ?: return null
////        if (isTop) {
////        }
//        sReference = Snackbar.make(view, msg, duration)
//        val snackbar = sReference
//        if (snackbar != null) {
//            if (msgColor != COLOR_MSG.toInt()) {
//                snackbar.setTextColor(msgColor)
//            }
//            if (bgColor != COLOR_DEFAULT.toInt()) {
//                snackbar.setBackgroundTint(bgColor)
//            }
//            if (actionMsgColor != COLOR_MSG.toInt()) {
//                snackbar.setActionTextColor(actionMsgColor)
//            }
//            if (action != {}) {
//                snackbar.setAction(actionMsg) {
//                    this.action()
//                }
//            }
//        }
//        snackbar?.show()
//        return snackbar
//    }
//
//    fun showSuccess(isTop: Boolean = false): Snackbar? {
//        bgColor = COLOR_SUCCESS.toInt()
//        return show(isTop)
//    }
//
//    fun showWarning(isTop: Boolean = false): Snackbar? {
//        bgColor = COLOR_WARNING.toInt()
//        return show(isTop)
//    }
//
//    fun showError(isTop: Boolean = false): Snackbar? {
//        bgColor = COLOR_ERROR.toInt()
//        return show(isTop)
//    }
//
//}
//
//class Weak<T : Any>(initializer: () -> T?) {
//
//    private var weakReference = WeakReference(initializer())
//
//    constructor() : this({
//        null
//    })
//
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
//        Log.d("Weak Delegate", "-----------getValue")
//        return weakReference.get()
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
//        Log.d("Weak Delegate", "-----------setValue")
//        weakReference = WeakReference(value)
//    }
//
//}