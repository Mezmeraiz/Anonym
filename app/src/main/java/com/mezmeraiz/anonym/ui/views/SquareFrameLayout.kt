package com.mezmeraiz.anonym.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout : FrameLayout {

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, MeasureSpec
//                .makeMeasureSpec((MeasureSpec.getSize(widthMeasureSpec)), MeasureSpec.EXACTLY))
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((MeasureSpec
                .getSize(widthMeasureSpec) / 1.33).toInt(), MeasureSpec.EXACTLY))
    }

}