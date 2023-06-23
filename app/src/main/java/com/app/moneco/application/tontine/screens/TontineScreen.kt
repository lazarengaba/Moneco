package com.app.moneco.application.tontine.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.common.screens.BottomBarNavScreen
import com.app.moneco.handlers.outfitRegular
import com.app.moneco.ui.theme.MonecoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TontineScreenView(navController: NavHostController) {
    MonecoTheme {
        Scaffold(
            bottomBar = {
                Column {
                    Divider(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
                    )
                    BottomBarNavScreen(navController = navController)
                }
            },
            content = { paddingValues ->
                Log.d("Padding values", "$paddingValues")
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tontines",
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontFamily = outfitRegular,
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TontineScreenPreview() {
    TontineScreenView(navController = rememberNavController())
}