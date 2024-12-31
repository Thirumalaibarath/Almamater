package com.journalia_nitt.journalia_admin_cms.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.journalia_nitt.journalia_admin_cms.alumni.screens.CommunityScreen
import com.journalia_nitt.journalia_admin_cms.alumni.screens.ContactUs
import com.journalia_nitt.journalia_admin_cms.alumni.screens.LandingPage
import com.journalia_nitt.journalia_admin_cms.alumni.screens.LoginPage
import com.journalia_nitt.journalia_admin_cms.alumni.screens.Page
import com.journalia_nitt.journalia_admin_cms.alumni.screens.PostPage
import com.journalia_nitt.journalia_admin_cms.alumni.screens.RegisterPage

//@Composable
//fun MyApp(innerPaddingValues: PaddingValues){
//    val navController = rememberNavController()
//    NavHost(navController=navController, startDestination = Screens_in_alumni_cms.communityPage.route){
//        composable(route= Screens_in_alumni_cms.loginPage.route){
//            LoginPage(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.landingPage.route){
//            LandingPage(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.postPage.route){
//            PostPage(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.registerPage.route){
//            RegisterPage(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.communityPage.route){
//            CommunityScreen(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.page.route){
//            Page(innerPaddingValues,navController)
//        }
//        composable(Screens_in_alumni_cms.contactUs.route){
//           ContactUs(navController, innerPaddingValues)
//        }
//    }
//}