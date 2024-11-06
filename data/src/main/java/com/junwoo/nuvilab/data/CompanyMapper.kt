package com.junwoo.nuvilab.data

import com.junwoo.nuvilab.database.ResultEntity
import com.junwoo.nuvilab.domain.Company
import com.junwoo.nuvilab.network.ResultDto
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

internal fun ResultEntity.asDomainModel(): Company = Company(
    rowNum = rowNum,
    appointNum = appointNum,
    isProductGB = isProductGB,
    company = company,
    companyNumber = companyNo,
    ceoName = ceoName,
    worksAddress = workAddress,
    state = state,
    province = province,
    companyKind = companyKind,
    companyKindName = companyKindNum,
    businessType = businessType,
    businessTypeName = businessTypeName,
    businessItem = businessItem,
    businessItemName = businessItemNum,
    certificationDate = LocalDate.parse(certifiedAt),
    certificationExpiredAt = LocalDate.parse(certificateEndOn)
)

internal fun ResultDto.asEntity(): ResultEntity = ResultEntity(
    rowNum = rowNum,
    appointNum = appointNum,
    isProductGB = isProductGB,
    company = company,
    companyNo = companyNo,
    ceoName = ceoName,
    workAddress = workAddress,
    state = state,
    province = province,
    companyKind = companyKind,
    companyKindNum = companyKindNum,
    businessType = businessType,
    businessTypeName = businessTypeName,
    businessItem = businessItem,
    businessItemNum = businessItemNum,
    certifiedAt = certifiedAt,
    certificateEndOn = certificateEndOn
)