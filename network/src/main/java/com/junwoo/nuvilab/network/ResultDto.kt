package com.junwoo.nuvilab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("rnum")
    val rowNum: String,
    @SerialName("appointno")
    val appointNum: String,
    @SerialName("productGb")
    val isProductGB: String,
    @SerialName("company")
    val company: String,
    @SerialName("companyno")
    val companyNo: String,
    @SerialName("ceoname")
    val ceoName: String,
    @SerialName("worksaddr")
    val workAddress: String,
    @SerialName("area1")
    val state: String,
    @SerialName("area2")
    val province: String,
    @SerialName("companykind")
    val companyKind: String,
    @SerialName("companykindNm")
    val companyKindNum: String,
    @SerialName("businesstype")
    val businessType: String,
    @SerialName("businesstypeNm")
    val businessTypeName: String,
    @SerialName("businessitem")
    val businessItem: String,
    @SerialName("businessitemNm")
    val businessItemNum: String,
    @SerialName("issuedate")
    val certifiedAt: String,
    @SerialName("issueenddate")
    val certificateEndOn: String,
)