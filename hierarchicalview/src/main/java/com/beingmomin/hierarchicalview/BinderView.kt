package com.beingmomin.hierarchicalview
import android.view.View

interface BinderView<T> {

    fun onCreateViewHolder(obj: T): View

    // to get hierarchical data
    fun getHierarchyData() : T

    fun getLayoutMaximumWidth(): Float
}