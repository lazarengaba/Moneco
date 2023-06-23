package com.app.moneco.application.transfer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitRegular
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.ui.theme.BlueText
import com.app.moneco.ui.theme.MonecoTheme

@Composable
fun SendMoneyScreenView(
    navController: NavHostController,
    selectedCountryCurrencyCode: String?,
    selectedCountryCode: String?,
    phoneNumber: String?,
    fullName: String?,
    selectedWalletName: String?
) {
    var canSendMoney by remember { mutableStateOf(false) }
    var amountToSend by remember { mutableDoubleStateOf(0.0) }
    val showConfirmDialog = remember { mutableStateOf(false) }

    val conversionRate = when (selectedCountryCurrencyCode) {
        "XOF" -> 655.94
        "MAD" -> 10.9
        else -> {
            655.94
        }
    }

    val currentBalance = 230

    if (showConfirmDialog.value)
        Dialog(
            onDismissRequest = { showConfirmDialog.value = false }
        ) {
            MonecoTheme {
                ConfirmationRequest(
                    navController = navController,
                    showConfirmDialog = showConfirmDialog,
                    selectedCountryCurrencyCode = selectedCountryCurrencyCode,
                    selectedCountryCode = selectedCountryCode,
                    phoneNumber = phoneNumber,
                    fullName = fullName,
                    selectedWalletName = selectedWalletName,
                    totalToSend = (amountToSend * conversionRate).toFloat()
                )
            }
        }


    MonecoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column {

                Column(
                    modifier = Modifier.padding(
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
                        text = "Send money",
                        fontFamily = outfitSemiBold,
                        fontSize = 26.sp,
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                    Text(
                        text = "How much are you sending ?",
                        fontFamily = outfitSemiBold,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                    OutlinedCard(
                        border = BorderStroke(
                            width = 1.dp, color = if (amountToSend == 0.0) {
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                            } else if (amountToSend >= currentBalance) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        ),
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.send_money_panel_height))
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen.transfer_button_size))
                                        .padding(
                                            start = dimensionResource(id = R.dimen.middle_spacer_size),
                                            end = dimensionResource(id = R.dimen.middle_spacer_size)
                                        ), verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BasicTextField(
                                        value = amountToSend.toString(),
                                        onValueChange = { newValue ->
                                            if (amountToSend.toString().isEmpty()) {
                                                amountToSend = 0.0
                                                canSendMoney = false
                                            } else {
                                                try {
                                                    amountToSend = newValue.toDouble()
                                                    canSendMoney =
                                                        amountToSend > 0 && amountToSend < currentBalance
                                                } catch (e: Exception) {
                                                    canSendMoney = false
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(
                                                top = dimensionResource(id = R.dimen.small_plus_spacer_size),
                                                bottom = dimensionResource(id = R.dimen.small_plus_spacer_size)
                                            ),
                                        textStyle = TextStyle(
                                            fontFamily = outfitSemiBold, fontSize = 18.sp,
                                            color = MaterialTheme.colorScheme.onBackground
                                        ),
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )

                                    Text(
                                        text = "EUR",
                                        fontFamily = outfitSemiBold,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .background(
                                            if (amountToSend == 0.0) {
                                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
                                            } else if (amountToSend >= currentBalance) {
                                                MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                                            } else {
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                            }
                                        ), verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Your current balance is ",
                                        fontFamily = outfitRegular,
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(
                                            start = dimensionResource(id = R.dimen.small_plus_spacer_size)
                                        )
                                    )
                                    Text(
                                        text = "$currentBalance EUR",
                                        fontFamily = outfitSemiBold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_icon_cover_size)))

                    Text(
                        text = "Yearly free remittances",
                        fontFamily = outfitSemiBold,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_small_spacer_size)))

                    Text(
                        text = "Remittances are free with Moneco until you reach your limit, which resets every year.",
                        fontFamily = outfitRegular,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_small_spacer_size)))

                    Text(
                        text = "Check number of free remittance remaining",
                        fontFamily = outfitSemiBold,
                        fontSize = 14.sp,
                        color = BlueText
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_icon_cover_size)))

                    Text(
                        text = "Fees breakdown",
                        fontFamily = outfitSemiBold,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                    Column {
                        Row {
                            Text(
                                text = "Moneco fees",
                                fontFamily = outfitRegular,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "0.0 EUR", fontFamily = outfitSemiBold, fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        Row {
                            Text(
                                text = "Transfer fees",
                                fontFamily = outfitRegular,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "0.0 EUR", fontFamily = outfitSemiBold, fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        Row {
                            Text(
                                text = "Conversion rate",
                                fontFamily = outfitRegular,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "$conversionRate $selectedCountryCurrencyCode",
                                fontFamily = outfitSemiBold,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        Row {
                            Text(
                                text = "You’ll spend in total",
                                fontFamily = outfitRegular,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "$amountToSend EUR",
                                fontFamily = outfitSemiBold,
                                fontSize = 14.sp,
                                color = if (amountToSend > 0.0 && !canSendMoney) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(50f, 20f), 0f)
                        val dashedColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 1f)

                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                        ) {
                            drawLine(
                                color = dashedColor,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                pathEffect = pathEffect
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Recipients gets",
                                fontFamily = outfitRegular,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "${(amountToSend * conversionRate).toFloat()} $selectedCountryCurrencyCode",
                                fontFamily = outfitSemiBold,
                                fontSize = 18.sp,
                                color = if (amountToSend > 0.0 && !canSendMoney) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                }
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mobile_wallet_button_height)))
                }

                Divider(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                )

                Column(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.main_padding_size),
                        end = dimensionResource(id = R.dimen.main_padding_size)
                    )
                ) {

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                    ElevatedButton(
                        onClick = {
                            showConfirmDialog.value = true
                        },
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.country_card_height))
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size)),
                        enabled = canSendMoney
                    ) {
                        Text(
                            text = "Continue", fontFamily = outfitSemiBold, fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                }
            }
        }
    }
}

@Composable
fun ConfirmationRequest(
    navController: NavHostController,
    showConfirmDialog: MutableState<Boolean>,
    selectedCountryCurrencyCode: String?,
    selectedCountryCode: String?,
    phoneNumber: String?,
    fullName: String?,
    selectedWalletName: String?,
    totalToSend: Float
) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = dimensionResource(id = R.dimen.main_padding_size)),
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dialog_size)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_plus_icon_size))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(dimensionResource(id = R.dimen.main_padding_size))
        ) {
            Column {
                Text(
                    text = "Confirm transfer",
                    fontFamily = outfitSemiBold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                Text(
                    text = "You're sending",
                    fontFamily = outfitSemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                Text(
                    text = "$totalToSend $selectedCountryCurrencyCode",
                    fontFamily = outfitSemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                Text(
                    text = "To",
                    fontFamily = outfitSemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                Text(
                    text = "$fullName",
                    fontFamily = outfitSemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                Text(
                    text = "Via",
                    fontFamily = outfitSemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                Text(
                    text = "$selectedWalletName : $selectedCountryCode $phoneNumber",
                    fontFamily = outfitSemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                ElevatedButton(
                    onClick = {
                        showConfirmDialog.value = false
                        navController.navigate(NavScreen.TransferSuccess.route)
                    },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.country_card_height))
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                ) {
                    Text(
                        text = "Confirm", fontFamily = outfitSemiBold, fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SendMoneyScreenViewPreview() {
    SendMoneyScreenView(
        navController = rememberNavController(),
        selectedCountryCurrencyCode = "",
        selectedCountryCode = "",
        phoneNumber = "",
        fullName = "",
        selectedWalletName = ""
    )
}