package com.junwoo.nuvilab.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorThrowable: Throwable? = null,
    onClickErrorConfirm: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var showErrorDialog by remember {
        mutableStateOf(false)
    }
    val isErrorItemShowed by remember {
        derivedStateOf {
            errorThrowable != null
        }
    }
    LaunchedEffect(isErrorItemShowed) {
        showErrorDialog = true
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .then(modifier)) {
        content()
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if (showErrorDialog) {
        Dialog(
            onDismissRequest = {
                onClickErrorConfirm().also {
                    showErrorDialog = false
                }
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = errorThrowable?.localizedMessage ?: "예상치 못한 에러가 발생하였습니다.")
                    Button(onClick = onClickErrorConfirm.also { showErrorDialog = false }) {
                        Text("뒤로 가기")
                    }
                }
            }
        }
    }
}