package com.app.moneco.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.moneco.common.navigation.NavGraph

@Composable
fun MainScreen() {
    NavGraph()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}