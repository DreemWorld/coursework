package coursework.gi.kishlish.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import coursework.gi.kishlish.ui.Toasts

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
                        Toasts().showShort(context, "TRANSPORT_CELLULAR")
                        return true
                    }

                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                        {
                            Toasts().showShort(context, "TRANSPORT_WIFI")
                            return true
                        }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->
                    {
                        Toasts().showShort(context, "TRANSPORT_ETHERNET")
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