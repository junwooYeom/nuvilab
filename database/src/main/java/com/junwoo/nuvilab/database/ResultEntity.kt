package com.junwoo.nuvilab.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultEntity(
    @ColumnInfo("rnum")
    @PrimaryKey
    val rowNum: String,
    @ColumnInfo("appointNo")
    val appointNum: String,
    @ColumnInfo("productGb")
    val isProductGB: String,
    @ColumnInfo("company")
    val company: String,
    @ColumnInfo("companyNo")
    val companyNo: String,
    @ColumnInfo("ceoname")
    val ceoName: String,
    @ColumnInfo("worksaddr")
    val workAddress: String,
    @ColumnInfo("area1")
    val state: String,
    @ColumnInfo("area2")
    val province: String,
    @ColumnInfo("companyKind")
    val companyKind: String,
    @ColumnInfo("companyKindNm")
    val companyKindNum: String,
    @ColumnInfo("businessType")
    val businessType: String,
    @ColumnInfo("businessTypeName")
    val businessTypeName: String,
    @ColumnInfo("businessItem")
    val businessItem: String,
    @ColumnInfo("businessItemNm")
    val businessItemNum: String,
    @ColumnInfo("issuedate")
    val certifiedAt: String,
    @ColumnInfo("issueenddate")
    val certificateEndOn: String,
)