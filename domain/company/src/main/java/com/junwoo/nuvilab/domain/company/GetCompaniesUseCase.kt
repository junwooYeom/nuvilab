package com.junwoo.nuvilab.domain.company

import com.junwoo.nuvilab.domain.CompanyRepository
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val repository: CompanyRepository
){
    operator fun invoke() = repository.getCompanyList()
}