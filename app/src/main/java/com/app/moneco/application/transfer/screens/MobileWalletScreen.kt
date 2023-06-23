package com.app.moneco.application.transfer.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.models.MultipleResourceWallet
import com.app.moneco.models.Wallet
import com.app.moneco.ui.theme.MonecoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileWalletScreenView(
    navController: NavHostController,
    selectedCountryCurrencyCode: String?,
    selectedCountryCode: String?,
    phoneNumber: String?,
    fullName: String?
) {

    var selectedWallet by remember { mutableIntStateOf(0) }
    var selectedWalletName by remember { mutableStateOf("") }

    val wallet1 = Wallet(1, "Cash plus")
    val wallet2 = Wallet(2, "Orange money")
    val wallet3 = Wallet(3, "Wave")
    val wallet4 = Wallet(4, "Moov Togo")

    val wallets: List<Wallet> = listOf(
        wallet1,
        wallet2,
        wallet3,
        wallet4
    )

    val mobileWallets = MultipleResourceWallet(wallets)

    val walletImages = listOf(
        R.drawable.wave,
        R.drawable.mtn,
        R.drawable.orange_money,
        R.drawable.moov
    )

    MonecoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {

                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size)
                        )
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.main_spacer_size)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        FloatingActionButton(
                            onClick = {
                                navController.navigateUp()
                            },
                            containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f),
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.small_icon_cover_size))
                                .height(dimensionResource(id = R.dimen.small_icon_cover_size)),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_icon_cover_rounded_size))
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = stringResource(id = R.string.close)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            start = dimensionResource(id = R.dimen.middle_spacer_size),
                            end = dimensionResource(id = R.dimen.middle_spacer_size)
                        )
                        .weight(1f)
                ) {

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                    Text(
                        text = "Choose a mobile wallet",
                        fontFamily = outfitSemiBold,
                        fontSize = 26.sp,
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                    mobileWallets.wallets.forEach { wallet ->
                        OutlinedCard(
                            onClick = {
                                selectedWallet = wallet.id
                                selectedWalletName = wallet.name
                            },
                            border = BorderStroke(
                                width = if (wallet.id == selectedWallet) 2.dp else 1.dp,
                                color = if (wallet.id == selectedWallet)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.mobile_wallet_button_height))
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            start = dimensionResource(id = R.dimen.main_padding_size),
                                            end = dimensionResource(id = R.dimen.main_padding_size)
                                        )
                                ) {
                                    ElevatedCard(
                                        modifier = Modifier
                                            .width(dimensionResource(id = R.dimen.medium_icon_cover_size))
                                            .height(dimensionResource(id = R.dimen.medium_icon_cover_size)),
                                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
                                    ) {
                                        Image(
                                            painterResource(id = walletImages[wallet.id - 1]),
                                            contentDescription = wallet.name,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                                    Text(
                                        text = wallet.name,
                                        fontFamily = outfitSemiBold,
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .weight(1f)
                                    )

                                    AnimatedVisibility(visible = wallet.id == selectedWallet) {
                                        Icon(
                                            imageVector = Icons.Rounded.CheckCircle,
                                            contentDescription = "Check",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                    }
                }

                Divider(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                )

                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size)
                        )
                ) {

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                    ElevatedButton(
                        onClick = {
                            navController.navigate(NavScreen.SendMoney.route + "/$selectedCountryCurrencyCode/$selectedCountryCode/$phoneNumber/$fullName/${selectedWalletName}")
                        },
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.country_card_height))
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size)),
                        enabled = selectedWallet != 0
                    ) {
                        Text(
                            text = "Continue",
                            fontFamily = outfitSemiBold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MobileWalletScreenViewPreview() {
    MobileWalletScreenView(
        navController = rememberNavController(),
        selectedCountryCurrencyCode = "",
        selectedCountryCode = "",
        phoneNumber = "",
        fullName = ""
    )
}