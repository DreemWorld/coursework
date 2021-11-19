package coursework.gi.kishlish.internet

import android.content.Context
import android.os.Build

class Connection {
    fun check(context: Context?) {
        if (Build.VERSION_CODES.Q < Build.VERSION.SDK_INT) {
            //TODO add for more version
        } else {
            //TODO add for less version
        }
    }
}