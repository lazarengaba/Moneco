package com.app.moneco.application.transaction.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.NorthEast
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.common.screens.BottomBarNavScreen
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitRegular
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.ui.theme.Green
import com.app.moneco.ui.theme.GreenLight
import com.app.moneco.ui.theme.LightBlue
import com.app.moneco.ui.theme.MonecoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(navController: NavHostController, modifier: Modifier = Modifier) {

    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            GreenLight,
            Green
        )
    )

    val roundedCornerShape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_card_corner_shape))

    MonecoTheme {

        val systemUiController = rememberSystemUiController()
        val isDarkTheme = isSystemInDarkTheme()
        val surfaceColor = MaterialTheme.colorScheme.surface

        if (navController.currentDestination?.route == NavScreen.Home.route) {

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = surfaceColor,
                    darkIcons = !isDarkTheme
                )
            }

        }

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
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = modifier
                            .verticalScroll(rememberScrollState())
                            .padding(dimensionResource(id = R.dimen.main_padding_size))
                    ) {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_spacer_size)))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Hey, John Doe",
                                fontFamily = outfitSemiBold,
                                fontSize = 26.sp
                            )

                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f),
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                                modifier = Modifier
                                    .width(dimensionResource(id = R.dimen.medium_icon_cover_size))
                                    .height(dimensionResource(id = R.dimen.medium_icon_cover_size))
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Notifications,
                                    contentDescription = stringResource(id = R.string.notifications)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium_card_corner_shape)),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(brush = horizontalGradientBrush)
                                    .fillMaxWidth()
                                    .padding(dimensionResource(id = R.dimen.middle_spacer_size))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.your_balance),
                                        fontFamily = outfitRegular,
                                        color = Color.White
                                    )

                                    FloatingActionButton(
                                        onClick = { /*TODO*/ },
                                        containerColor = Color.White.copy(alpha = 0.2f),
                                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Payments,
                                            contentDescription = stringResource(id = R.string.your_balance),
                                            tint = Color.White,
                                            modifier = Modifier.size(dimensionResource(id = R.dimen.medium_plus_icon_size))
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_size)))

                                Text(
                                    text = "320,000",
                                    fontFamily = outfitSemiBold,
                                    color = Color.White,
                                    fontSize = 24.sp
                                )
                                Text(
                                    text = stringResource(id = R.string.us_dollars),
                                    fontFamily = outfitRegular,
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.main_padding_size))
                        ) {
                            ElevatedCard(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                shape = roundedCornerShape,
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillMaxSize()
                                        .padding(dimensionResource(id = R.dimen.main_padding_size))
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .background(
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.1f
                                                    ),
                                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painterResource(id = R.drawable.empty_wallet_add),
                                                contentDescription = stringResource(id = R.string.top_up_balance),
                                                modifier = Modifier
                                                    .width(dimensionResource(id = R.dimen.medium_plus_icon_size))
                                                    .height(dimensionResource(id = R.dimen.medium_plus_icon_size)),
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                        Text(
                                            text = stringResource(id = R.string.top_up_balance),
                                            fontFamily = outfitSemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 14.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }

                            ElevatedCard(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                shape = roundedCornerShape,
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillMaxSize()
                                        .padding(dimensionResource(id = R.dimen.main_padding_size))
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .background(
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.1f
                                                    ),
                                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {

                                            Image(
                                                painterResource(id = R.drawable.wallet_minus),
                                                contentDescription = stringResource(id = R.string.withdraw_money),
                                                modifier = Modifier
                                                    .width(dimensionResource(id = R.dimen.medium_plus_icon_size))
                                                    .height(dimensionResource(id = R.dimen.medium_plus_icon_size)),
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                        Text(
                                            text = stringResource(id = R.string.withdraw_money),
                                            fontFamily = outfitSemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 14.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.main_padding_size))
                        ) {

                            ElevatedCard(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                shape = roundedCornerShape,
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillMaxSize()
                                        .padding(dimensionResource(id = R.dimen.main_padding_size))
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .background(
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.1f
                                                    ),
                                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painterResource(id = R.drawable.card),
                                                contentDescription = stringResource(id = R.string.get_iban),
                                                modifier = Modifier
                                                    .width(dimensionResource(id = R.dimen.medium_plus_icon_size))
                                                    .height(dimensionResource(id = R.dimen.medium_plus_icon_size)),
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                        Text(
                                            text = stringResource(id = R.string.get_iban),
                                            fontFamily = outfitSemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 14.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }

                            ElevatedCard(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                shape = roundedCornerShape,
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillMaxSize()
                                        .padding(dimensionResource(id = R.dimen.main_padding_size))
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                                                .background(
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.1f
                                                    ),
                                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size))
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painterResource(id = R.drawable.percentage_square),
                                                contentDescription = stringResource(id = R.string.view_analytics),
                                                modifier = Modifier
                                                    .width(dimensionResource(id = R.dimen.medium_plus_icon_size))
                                                    .height(dimensionResource(id = R.dimen.medium_plus_icon_size)),
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                        Text(
                                            text = stringResource(id = R.string.view_analytics),
                                            fontFamily = outfitSemiBold,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 14.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_icon_cover_size)))

                        Text(
                            text = stringResource(id = R.string.transactions),
                            fontFamily = outfitSemiBold,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = roundedCornerShape,
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
                        ) {

                            Column(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(dimensionResource(id = R.dimen.main_padding_size))
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    FloatingActionButton(
                                        onClick = { /*TODO*/ },
                                        containerColor =  LightBlue.copy(alpha = 0.1f),
                                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                                        modifier = Modifier
                                            .width(dimensionResource(id = R.dimen.medium_icon_cover_size))
                                            .height(dimensionResource(id = R.dimen.medium_icon_cover_size))
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.NorthEast,
                                            contentDescription = stringResource(id = R.string.sent_to),
                                            tint = LightBlue,
                                            modifier = Modifier.size(dimensionResource(id = R.dimen.middle_spacer_size))
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = stringResource(id = R.string.sent_to),
                                            fontFamily = outfitRegular,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
                                        )
                                        Text(
                                            text = "Ralph Edouard",
                                            fontFamily = outfitSemiBold,
                                            fontSize = 18.sp
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Euro,
                                            contentDescription = "...",
                                            modifier = Modifier.size(dimensionResource(id = R.dimen.main_padding_size))
                                        )
                                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.extra_small_spacer_size)))
                                        Text(
                                            text = "15",
                                            fontFamily = outfitSemiBold,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_spacer_size)))
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenView(navController = rememberNavController())
}