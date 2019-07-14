package com.beingmomin.hierarchicalview
import android.view.View

interface BinderView<T> {

    fun onCreateViewHolder(obj: T): View

    fun getHierarchyData() : T

    fun getLayoutMaximumWidth(): Float
}