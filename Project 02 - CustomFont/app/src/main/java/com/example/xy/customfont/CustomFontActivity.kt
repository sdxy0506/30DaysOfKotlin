package com.example.xy.customfont

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_font.*

class CustomFontActivity : AppCompatActivity() {

    val fontList = mutableListOf<Typeface>()
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_font)

        fontList.addAll(getFonts())

        changeFont(fontList[index])

        btn_change_font.setOnClickListener { view -> changeFont(fontList[index]) }

    }

    fun changeFont(type: Typeface?) {
        custom_font_txt.typeface = type
        btn_change_font.typeface = type

        index = if (index > 1) 0 else ++index

    }

    fun getFonts(): MutableList<Typeface> {
        val fonts = arrayListOf("miaomiao.ttf", "qingshu.ttf", "zhihei.ttf")
        val list: MutableList<Typeface> = mutableListOf()
        for (font in fonts) {
            val typeface = Typeface.createFromAsset(assets, "fonts/" + font)
            list.add(typeface)
        }

        return list
    }

}
