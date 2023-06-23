package com.app.moneco.common.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {
    object Home: NavScreen(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )

    object Cards: NavScreen(
        route = "cards",
        title = "Cards",
        icon = Icons.Rounded.CreditCard
    )

    object Transfer: NavScreen(
        route = "transfer",
        title = "Transfer",
        icon = Icons.Outlined.Send
    )

    object SendToAfrica: NavScreen(
        route = "send-to-africa",
        title = "Send to Africa",
        icon = null
    )

    object MobileWallet: NavScreen(
        route = "mobile wallet",
        title = "Mobile wallet",
        icon = null
    )

    object TransferRecipient: NavScreen(
        route = "transfer-recipient",
        title = "Transfer recipient",
        icon = null
    )

    object SendMoney: NavScreen(
        route = "send-money",
        title = "Send money",
        icon = null
    )

    object TransferSuccess: NavScreen(
        route = "transfer-success",
        title = "Transfer success",
        icon = null
    )

    object Tontines: NavScreen(
        route = "tontines",
        title = "Tontines",
        icon = Icons.Rounded.MonetizationOn
    )

    object Settings: NavScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Rounded.Settings
    )
}