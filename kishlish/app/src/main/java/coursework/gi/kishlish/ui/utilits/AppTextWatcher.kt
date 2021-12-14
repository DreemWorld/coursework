package coursework.gi.kishlish.ui.utilits

import android.text.Editable
import android.text.TextWatcher

class AppTextWatcher(private val onSuccess: (Editable?) -> Unit):TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }
}