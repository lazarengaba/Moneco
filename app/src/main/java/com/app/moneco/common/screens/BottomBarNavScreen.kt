package com.app.moneco.common.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.moneco.R
import com.app.moneco.handlers.outfitRegular
import com.app.moneco.ui.theme.GreenLight
import com.app.moneco.ui.theme.Secondary

@Composable
fun BottomBarNavScreen(navController: NavHostController) {
    val screens = listOf(
        NavScreen.Home,
        NavScreen.Cards,
        NavScreen.Transfer,
        NavScreen.Tontines,
        NavScreen.Settings,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val color =
        if (selected) GreenLight else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .weight(1f)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (screen.route != NavScreen.Transfer.route) {
                IconButton(onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }) {
                    Icon(
                        imageVector = screen.icon!!,
                        contentDescription = "${screen.title} icon",
                        tint = color
                    )
                }
                Text(
                    text = screen.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = outfitRegular,
                    color = color,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.small_plus_spacer_size),
                            end = dimensionResource(id = R.dimen.small_plus_spacer_size)
                        )
                )
            } else {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                ElevatedCard(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.transfer_button_size))
                        .height(dimensionResource(id = R.dimen.transfer_button_size)),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.transfer_button_corner_radius)),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Secondary)
                            .clickable(
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painterResource(id = R.drawable.send),
                            contentDescription = "${screen.title} icon",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        }
    }
}