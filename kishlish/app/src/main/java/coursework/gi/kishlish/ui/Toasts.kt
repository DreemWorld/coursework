package coursework.gi.kishlish.ui

import android.content.Context
import android.widget.Toast

class Toasts {
    fun showShort(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT)

    fun showLong(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG)

}