package coursework.gi.kishlish.ui

import android.content.Context
import android.widget.Toast

class Toasts {

    fun shortToastShow(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun longToastShow(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}