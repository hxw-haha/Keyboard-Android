package com.hxw.keyboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hxw.keyboard.keyboard.KeyboardDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val defaultMaxLength = 18;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun keyboardNormal(view: View) {
        KeyboardDialog.Builder(view.context)
            .setThenCall(true)
            .setMaxLength(defaultMaxLength)
            .setDefaultValue(getDefaultText())
            .setCall {
                default_text.text = it
            }.build()
    }

    fun keyboardNormalRandom(view: View) {
        KeyboardDialog.Builder(view.context)
            .setThenCall(true)
            .setLetterRandom(true)
            .setNumberRandom(true)
            .setMaxLength(defaultMaxLength)
            .setDefaultValue(getDefaultText())
            .setCall {
                default_text.text = it
            }.build()
    }


    fun keyboardPhone(view: View) {
        KeyboardDialog.Builder(view.context)
            .setThenCall(true)
            .setPhoneKeyboard()
            .setDefaultValue(getDefaultText())
            .setCall {
                default_text.text = it
            }.build()
    }

    fun keyboardMoney(view: View) {
        KeyboardDialog.Builder(view.context)
            .setThenCall(true)
            .setNumberRandom(true)
            .setMoneyKeyboard()
            .setMoneyPointLength(4)
            .setMaxLength(defaultMaxLength)
            .setDefaultValue(getDefaultText())
            .setCall {
                default_text.text = it
            }.build()
    }

    private fun getDefaultText(): String {
        return default_text.text.toString()
    }

}