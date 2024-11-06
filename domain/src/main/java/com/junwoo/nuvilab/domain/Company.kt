package com.junwoo.nuvilab.domain

import kotlinx.datetime.LocalDate

data class Company(
    val rowNum: String,
    val appointNum: String,
    val isProductGB: String,
    val company: String,
    val companyNumber: String,
    val ceoName: String,
    val worksAddress: String,
    val state: String,
    val province: String,
    val companyKind: String,
    val companyKindName: String,
    val businessType: String,
    val businessTypeName: String,
    val businessItem: String,
    val businessItemName: String,
    val certificationDate: LocalDate,
    val certificationExpiredAt: LocalDate
)