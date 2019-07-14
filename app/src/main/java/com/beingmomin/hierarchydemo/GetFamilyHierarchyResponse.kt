package com.beingmomin.hierarchydemo

import com.google.gson.annotations.SerializedName


data class GetFamilyHierarchyResponse(
        @SerializedName("data")
        val `data`: Children,
        @SerializedName("status")
        val status: Int
)

data class Children(
        @SerializedName("children")
        val children: List<Children>?,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("wifeName")
        val wifeName: String?,
        @SerializedName("wifeId")
        val wifeId: Int,
        @SerializedName("spaceVal")
        var spaceVal: Int = 0,
        @SerializedName("personId")
        var personId: Int,
        @SerializedName("fatherId")
        var fatherId: Int
        )