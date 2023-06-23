package com.app.moneco.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import com.app.moneco.application.card.screens.CardScreenView
import com.app.moneco.application.setting.screens.SettingScreenView
import com.app.moneco.application.tontine.screens.TontineScreenView
import com.app.moneco.application.transaction.screens.HomeScreenView
import com.app.moneco.application.transfer.screens.MobileWalletScreenView
import com.app.moneco.application.transfer.screens.RecipientScreenView
import com.app.moneco.application.transfer.screens.SendMoneyScreenView
import com.app.moneco.application.transfer.screens.SendToAfricaScreenView
import com.app.moneco.application.transfer.screens.SuccessScreenView
import com.app.moneco.application.transfer.screens.TransferScreenView
import com.app.moneco.common.screens.NavScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController,
        startDestination = NavScreen.Home.route
    ) {

        composable(route = NavScreen.Home.route) {
            HomeScreenView(navController)
        }

        composable(route = NavScreen.Cards.route) {
            CardScreenView(navController)
        }

        composable(
            route = NavScreen.Transfer.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            TransferScreenView(navController)
        }

        composable(
            route = NavScreen.SendToAfrica.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            SendToAfricaScreenView(navController)
        }

        composable(
            route = NavScreen.MobileWallet.route + "/{selectedCountryCurrencyCode}/{selectedCountryCode}/{phoneNumber}/{fullName}",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }
        ) { navBackStack ->

            val selectedCountryCurrencyCode = navBackStack.arguments?.getString("selectedCountryCurrencyCode")
            val selectedCountryCode = navBackStack.arguments?.getString("selectedCountryCode")
            val phoneNumber = navBackStack.arguments?.getString("phoneNumber")
            val fullName = navBackStack.arguments?.getString("fullName")

            MobileWalletScreenView(
                navController = navController,
                selectedCountryCurrencyCode = selectedCountryCurrencyCode,
                selectedCountryCode = selectedCountryCode,
                phoneNumber = phoneNumber,
                fullName = fullName
            )
        }

        composable(
            route = NavScreen.TransferRecipient.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            RecipientScreenView(navController)
        }

        composable(
            route = NavScreen.SendMoney.route + "/{selectedCountryCurrencyCode}/{selectedCountryCode}/{phoneNumber}/{fullName}/{selectedWalletName}",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }

        ) { navBackStack ->

            val selectedCountryCurrencyCode = navBackStack.arguments?.getString("selectedCountryCurrencyCode")
            val selectedCountryCode = navBackStack.arguments?.getString("selectedCountryCode")
            val phoneNumber = navBackStack.arguments?.getString("phoneNumber")
            val fullName = navBackStack.arguments?.getString("fullName")
            val selectedWalletName = navBackStack.arguments?.getString("selectedWalletName")

            SendMoneyScreenView(
                navController = navController,
                selectedCountryCurrencyCode = selectedCountryCurrencyCode,
                selectedCountryCode = selectedCountryCode,
                phoneNumber = phoneNumber,
                fullName = fullName,
                selectedWalletName = selectedWalletName
            )
        }

        composable(
            route = NavScreen.TransferSuccess.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            SuccessScreenView(navController)
        }

        composable(route = NavScreen.Tontines.route) {
            TontineScreenView(navController)
        }

        composable(route = NavScreen.Settings.route) {
            SettingScreenView(navController)
        }
    }
}