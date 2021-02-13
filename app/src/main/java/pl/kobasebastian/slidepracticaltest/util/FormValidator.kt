package pl.kobasebastian.slidepracticaltest.util

import android.widget.EditText

object FormValidator {
    fun validate(vararg editTexts: EditText) : Boolean {
        var validated = true
        editTexts.forEach {
            it.let {
                if (it.text.isEmpty()) {
                    it.error = "Is empty"
                    validated = true
                }
            }
        }
        return validated
    }
}