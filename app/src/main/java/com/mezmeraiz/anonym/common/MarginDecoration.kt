package com.mezmeraiz.anonym.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class MarginDecoration (val verticalSpace: Int, val sideSpace: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpace
        }
        outRect.bottom = verticalSpace
        if (sideSpace != 0){
            outRect.right = sideSpace
            outRect.left = sideSpace
        }
    }
}