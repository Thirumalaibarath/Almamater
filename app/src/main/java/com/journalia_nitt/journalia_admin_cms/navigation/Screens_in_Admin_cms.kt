package com.journalia_nitt.journalia_admin_cms.navigation

sealed class Screens_in_Admin_cms(val route : String) {
    object LoginPage : Screens_in_Admin_cms("loginPage")
    object SecretPage : Screens_in_Admin_cms("secretPage/{email}") {
        fun createRoute(email : String) = "secretPage/$email"
    }
    object SplashPage : Screens_in_Admin_cms("splashPage")
    object LandingPage : Screens_in_Admin_cms("landingPage/{token}"){
        fun createRoute(token : String) = "landingPage/$token"
    }
    object AdminPage : Screens_in_Admin_cms("adminPage/{token}") {
        fun createRoute(token : String) = "adminPage/$token"
    }
    object DeadlinePage : Screens_in_Admin_cms("deadlinePage/{token}") {
        fun createRoute(token : String) = "deadlinePage/$token"
    }
    object AnnouncementPage : Screens_in_Admin_cms("announcementPage/{token}"){
        fun createRoute(token : String) = "announcementPage/$token"
    }
    object InfoPage : Screens_in_Admin_cms("infoPage")
    object PdfView: Screens_in_Admin_cms("pdfView")
}