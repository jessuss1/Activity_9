package com.example.act9

import android.os.Bundle

class ActFiveB : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_five_b)

        val selectedImages = intent.getIntegerArrayListExtra("SELECTED_IMAGES") ?: arrayListOf()

        val paketaxoAmarillo = listOf(R.drawable.img_12, R.drawable.img_21, R.drawable.img_30, R.drawable.img_38)
//        val paketaxoVerde = listOf(R.drawable.)
    }
}