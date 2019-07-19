package com.beingmomin.hierarchicalview


import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.otaliastudios.zoom.ZoomLayout
import kotlinx.android.synthetic.main.layout_hierarchy_divider.view.*


class HierarchicalView constructor(mContext: Context, attributeSet: AttributeSet) : ZoomLayout(mContext, attributeSet) {

    var constraintLayout: ConstraintLayout

    init {
        constraintLayout = ConstraintLayout(mContext)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        this.addView(constraintLayout, layoutParams)
    }

    val detailMap = mutableMapOf<Int, ExtraDetail>()

    var maxWidthOfSideLine: Float = 0f

    companion object {
        var temp = 1
    }

    inline fun <reified T> setBinderView(binder: BinderView<T>) {
        val binderView = binder
        val hierarchyData = binderView.getHierarchyData()
        maxWidthOfSideLine = (binderView.getLayoutMaximumWidth() + 20) / 2
        val hierarchyFieldName = getChildrenVariable(hierarchyData)

        if (hierarchyData != null && hierarchyFieldName != null) {
            calculateSpace(0, 1, hierarchyData, hierarchyFieldName)
            inflateHierarchy(binderView, null, hierarchyData, hierarchyFieldName, 0)
        }
    }


    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }

    fun <T> inflateHierarchy(binderView: BinderView<T>, root: T?, child: T, hierarchyChildName: String, index: Int) {

        val childView = (context as Activity).layoutInflater.inflate(R.layout.layout_hierarchy_divider, null)
        val mainView = binderView.onCreateViewHolder(child)
        childView.fl_child_view.addView(mainView)

        val field = (child!! as Any).javaClass.getDeclaredField(hierarchyChildName)
        field.isAccessible = true
        val list = field.get(child) as List<T>?

        var rootChildList: List<T>? = null
        if (root != null) {
            val rootField = (root as Any).javaClass.getDeclaredField(hierarchyChildName)
            field.isAccessible = true
            rootChildList = field.get(root) as List<T>?
        }


        val extraDetail = detailMap.get(child.hashCode())

        if (list != null && list.size == 0) {
            childView.v_vertical_bottom_line.visibility = View.INVISIBLE
        } else {
            val lineParams = childView.v_horizontal_top_line_left.layoutParams
            lineParams.width = dpToPx((maxWidthOfSideLine * extraDetail!!.spaceVal))
            childView.v_horizontal_top_line_left.layoutParams = lineParams

            val lineParamsRight = childView.v_horizontal_top_line_right.layoutParams
            lineParamsRight.width = dpToPx((maxWidthOfSideLine * extraDetail.spaceVal))
            childView.v_horizontal_top_line_right.layoutParams = lineParamsRight
        }


        if (root != null) {
            if (index == 0) {
                childView.v_horizontal_top_line_left.visibility = View.INVISIBLE
                if (rootChildList?.size == 1) {
                    childView.v_horizontal_top_line_right.visibility = View.INVISIBLE
                }
            } else if (index == rootChildList!!.size - 1) {
                childView.v_horizontal_top_line_right.visibility = View.INVISIBLE
            }
        } else {
            childView.v_horizontal_top_line_left.visibility = View.INVISIBLE
            childView.v_horizontal_top_line_right.visibility = View.INVISIBLE
        }


        childView.id = extraDetail!!.unId
        constraintLayout.addView(childView)

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        if (extraDetail.fatherId == 0) {
            constraintSet.connect(childView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            constraintSet.connect(childView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            constraintSet.connect(childView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        } else {
            if (index == 0) {
                constraintSet.connect(extraDetail.unId, ConstraintSet.TOP, extraDetail.fatherId, ConstraintSet.BOTTOM)
                constraintSet.connect(extraDetail.unId, ConstraintSet.LEFT, extraDetail.fatherId, ConstraintSet.LEFT)
            } else {
                constraintSet.connect(extraDetail.unId, ConstraintSet.TOP, extraDetail.fatherId, ConstraintSet.BOTTOM)
                constraintSet.connect(
                    extraDetail.unId,
                    ConstraintSet.LEFT,
                    detailMap.get((rootChildList!!.get(index - 1)).hashCode())!!.unId,
                    ConstraintSet.RIGHT
                )
            }
        }
        constraintSet.applyTo(constraintLayout)

        if (list != null) {
            list.forEachIndexed { counter, son ->
                inflateHierarchy(binderView, child, son, hierarchyChildName, counter)
            }
        }
    }


    inline fun <reified T> getChildrenVariable(data: T): String? {
        for (field in (data as Any).javaClass.declaredFields) {
            field.isAccessible = true
            val fieldVal = field.get(data)
            if (fieldVal is List<*>) {
                val result: List<T>? = fieldVal.asListOfType()
                if (result != null) {
                    return field.name
                }
            } else {
                Log.d("az", "not a list")
            }
        }
        return null
    }


    inline fun <reified T> List<*>.asListOfType(): List<T>? =
        if (all { it is T })
            @Suppress("UNCHECKED_CAST")
            this as List<T> else
            null


    fun <T> calculateSpace(fatherId: Int, personalId: Int, root: T, hierarchyChildName: String) {

        val field = (root!! as Any).javaClass.getDeclaredField(hierarchyChildName)
        field.isAccessible = true
        val list = field.get(root) as List<T>?
        val spaceVal :Int
        if (list != null && list.size > 0) {
            for (child in list) {
                temp++
                calculateSpace(personalId, temp, child, hierarchyChildName)
            }
            var sumOfSpace = 0
            for (child in list) {
                sumOfSpace = sumOfSpace + detailMap.get(child.hashCode())!!.spaceVal
            }
            spaceVal = sumOfSpace
        } else {
            spaceVal = 1
        }
        detailMap.put(root.hashCode(), ExtraDetail(personalId, fatherId, spaceVal))
    }

}