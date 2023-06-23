package com.app.moneco.application.transfer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.ui.theme.MonecoTheme

@Composable
fun SendToAfricaScreenView(navController: NavHostController) {

    MonecoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.main_spacer_size))
                        .padding(
                            start = dimensionResource(id = R.dimen.middle_spacer_size),
                            end = dimensionResource(id = R.dimen.middle_spacer_size),
                        ),

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

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                Text(
                    text = "Send to Africa",
                    fontFamily = outfitSemiBold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.middle_spacer_size),
                            end = dimensionResource(id = R.dimen.middle_spacer_size)
                        )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                Box(
                    Modifier.clickable(
                        onClick = {
                            navController.navigate(NavScreen.TransferRecipient.route)
                        }
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.medium_list_item_height))
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.middle_spacer_size),
                                end = dimensionResource(id = R.dimen.middle_spacer_size),
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_icon_cover_rounded_size))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowCircleRight,
                                contentDescription = "Mobile wallets",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                        Text(
                            text = "Mobile wallets",
                            fontFamily = outfitSemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Rounded.ArrowForwardIos,
                            contentDescription = stringResource(id = R.string.close),
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.main_padding_size))
                        )
                    }
                }

                Box(
                    Modifier.clickable(
                        onClick = {

                        }
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.medium_list_item_height))
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.middle_spacer_size),
                                end = dimensionResource(id = R.dimen.middle_spacer_size),
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_icon_cover_rounded_size))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowCircleRight,
                                contentDescription = "Mobile wallets",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                        Text(
                            text = "Bank transfer",
                            fontFamily = outfitSemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Rounded.ArrowForwardIos,
                            contentDescription = stringResource(id = R.string.close),
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.main_padding_size))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SendToAfricaScreenPreview() {
    SendToAfricaScreenView(navController = rememberNavController())
}