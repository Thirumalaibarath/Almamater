package com.journalia_nitt.journalia_admin_cms.navigation

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.journalia_nitt.journalia_admin_cms.common.screens.LoginPage


//@Composable
//fun MyApp(innerPaddingValues: PaddingValues) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screens_in_Admin_cms.SplashPage.route) {
//        composable(
//            route = Screens_in_Admin_cms.SplashPage.route,
//            exitTransition = {
//                fadeOut(animationSpec = tween(durationMillis = 200 , easing = EaseOut))
//            }
//        ) {
//            SplashPage(innerPaddingValues , navController)
//        }
//        composable(
//            route = Screens_in_Admin_cms.LoginPage.route,
//            enterTransition = {
//                fadeIn(animationSpec = tween(durationMillis = 200 , easing = EaseIn))
//            },
//        ) {
//            LoginPage(innerPaddingValues , navController)
//        }
//        composable(
//            route = Screens_in_Admin_cms.SecretPage.route,
//            arguments = listOf(
//                navArgument("email") {type = NavType.StringType}
//            )
//        ) { backStackEntry ->
//            val email = backStackEntry.arguments?.getString("email") ?: ""
//            SecretChecking(email , innerPaddingValues , navController)
//        }
//        composable(
//            route = Screens_in_Admin_cms.LandingPage.route,
//            arguments = listOf(
//                navArgument("token") {type = NavType.StringType}
//            )
//        ) {backStackEntry ->
//            val temp = backStackEntry.arguments?.getString("token") ?: ""
//            val token = remember { mutableStateOf(temp) }
//            LandingPage(token ,innerPaddingValues , navController)
//        }
//        composable(
//            route = Screens_in_Admin_cms.AdminPage.route,
//            arguments = listOf(
//                navArgument("token") {type = NavType.StringType}
//            )
//        ){ backStackEntry ->
//            val token=backStackEntry.arguments?.getString("token") ?: ""
//            AdminDashBoard(token , innerPaddingValues , navController)
//        }
//        composable(
//            route = Screens_in_Admin_cms.DeadlinePage.route,
//            arguments = listOf(
//                navArgument("token") {type = NavType.StringType}
//            )
//        )
//        { backStackEntry ->
//            val token=backStackEntry.arguments?.getString("token") ?: ""
//            PostPage(token,innerPaddingValues , navController)
//        }
//        composable(route = Screens_in_Admin_cms.AnnouncementPage.route,
//            arguments = listOf(
//                navArgument("token") {type = NavType.StringType}
//            )
//            ) {
//                backStackEntry ->
//            val token=backStackEntry.arguments?.getString("token") ?: ""
//            PostPage(token,innerPaddingValues , navController)
//        }
//        composable(route = Screens_in_Admin_cms.InfoPage.route) {
//            AdminDetailsPage(navController = navController, innerPaddingValues = innerPaddingValues)
//        }
//        composable(route = Screens_in_Admin_cms.PdfView.route) {
//                imageHelper(navController)
//        }
//    }
//}