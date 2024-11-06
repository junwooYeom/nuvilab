package com.junwoo.nuvilab.feat.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.junwoo.nuvilab.design.BaseScreen
import com.junwoo.nuvilab.design.ListComponent

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.uiState.itemSource.collectAsLazyPagingItems()
    BaseScreen(
        modifier = modifier.fillMaxSize(),
        isLoading = pagingItems.loadState.refresh == LoadState.Loading || pagingItems.loadState.append == LoadState.Loading
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pagingItems.itemCount) {
                val currentItem = pagingItems[it]
                currentItem?.let { model ->
                    ListComponent(
                        title = model.company,
                        description = "${model.state} ${model.province}"
                    )
                }
            }
        }
    }

}