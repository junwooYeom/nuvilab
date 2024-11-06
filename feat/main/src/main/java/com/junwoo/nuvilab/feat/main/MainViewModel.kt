package com.junwoo.nuvilab.feat.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.junwoo.nuvilab.domain.Company
import com.junwoo.nuvilab.domain.company.GetCompaniesUseCase
import com.junwoo.nuvilab.feat.core.BaseViewModel
import com.junwoo.nuvilab.feat.core.UIAction
import com.junwoo.nuvilab.feat.core.UIEffect
import com.junwoo.nuvilab.feat.core.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed class MainAction : UIAction

data class MainState(
    val itemSource: Flow<PagingData<Company>> = emptyFlow(),
    val loadingState: Boolean = false,
) : UIState {
    override val isLoading: Boolean
        get() = loadingState
}

sealed interface MainEffect : UIEffect

@HiltViewModel
class MainViewModel @Inject constructor(
    getCompaniesUseCase: GetCompaniesUseCase
) : BaseViewModel<MainAction, MainState, MainEffect>(initialState = MainState()) {

    private val companyFlow = getCompaniesUseCase().cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )
        .also {
            setState { copy(itemSource = it) }
        }
        .launchIn(viewModelScope)

}