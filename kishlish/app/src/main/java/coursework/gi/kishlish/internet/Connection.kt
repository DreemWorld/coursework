package coursework.gi.kishlish.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import coursework.gi.kishlish.ui.activity.MainActivity

class Connection {
    fun check(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                    {
                        Toast.makeText(context, "TRANSPORT_CELLULAR", Toast.LENGTH_SHORT).show()
                        return true
                    }

                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                        {
                            Toast.makeText(context, "TRANSPORT_WIFI", Toast.LENGTH_SHORT).show()
                            return true
                        }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->
                    {
                        Toast.makeText(context, "TRANSPORT_ETHERNET", Toast.LENGTH_SHORT).show()
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}