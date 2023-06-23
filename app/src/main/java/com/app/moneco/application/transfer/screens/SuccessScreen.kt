package com.app.moneco.application.transfer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.ui.theme.MonecoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SuccessScreenView(navController: NavHostController) {
    MonecoTheme {

        val systemUiController = rememberSystemUiController()
        val surfaceColor = MaterialTheme.colorScheme.primary

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = surfaceColor,
                darkIcons = false
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.medium_icon_cover_size)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painterResource(id = R.drawable.on_way),
                        contentDescription = "Money on way !",
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.success_icon_size))
                            .height(dimensionResource(id = R.dimen.success_icon_size)),
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.medium_plus_icon_size))
                    )

                    Text(
                        text = "Your money is on the way. Get excited! ",
                        fontFamily = outfitSemiBold,
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(value = 40f, type = TextUnitType.Sp)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.country_card_height))
                    )

                    ElevatedButton(
                        onClick = {
                            navController.navigate(NavScreen.Home.route) {
                                navController.popBackStack()
                            }
                        },
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.country_card_height))
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                    ) {
                        Text(
                            text = "Got it !", fontFamily = outfitSemiBold, fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessScreenPreview() {
    SuccessScreenView(
        navController = rememberNavController()
    )
}