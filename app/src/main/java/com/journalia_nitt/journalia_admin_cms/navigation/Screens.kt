package com.journalia_nitt.journalia_admin_cms.navigation

sealed class Screens(val route:String) {
    //common
    data object UserRoleSelectionScreen : Screens("UserRoleSelectionScreen")
    data object CommonSplashScreen : Screens("CommonSplashScreen")
    // admin side
    data object AdminHomeScreen : Screens("AdminHomeScreen")
    data object AdminCreatePostScreen : Screens("AdminCreatePostScreen")
    data object AdminDashboardScreen : Screens("AdminDashboardScreen")
    data object AdminLoginScreen : Screens("AdminLoginScreen")
    data object AdminLoginVerificationScreen : Screens("AdminVerificationScreen")
    data object AdminSplashScreen : Screens("AdminSplashScreen")
    data object AdminViewPostScreen : Screens("AdminViewPostScreen")
    //    Alumni Side
    data object AlumniCreateAPostScreen : Screens("AlumniCreateAPostScreen")
    data object AlumniLoginScreen : Screens("AlumniLoginScreen")
    data object AlumniRegisterScreen : Screens("AlumniRegisterScreen")
    data object AlumniSplashScreen : Screens("AlumniSplashScreen")
    data object AlumniPostViewScreen: Screens("AlumniPostViewScreen")
    data object AlumniHomeScreen: Screens("AlumniHomeScreen")
    data object AlumniCommunityScreen: Screens("AlumniCommunityScreen")
    //    Student Side
    data object StudentAdminPostViewScreen : Screens("StudentAdminPostViewScreen")
    data object StudentLoginScreen : Screens("StudentLoginScreen")
    data object StudentClubDirectoryScreen : Screens("StudentClubDirectoryScreen")
    data object StudentFestDirectoryScreen : Screens("StudentClubDirectoryScreen")
    data object StudentSplashScreen : Screens("StudentSplashScreen")
    data object StudentBookMarkScreen : Screens("StudentBookMarkScreen")
    data object StudentCalendarScreen : Screens("StudentCalendarScreen")
    data object StudentCreateAPostScreen : Screens("StudentCreateAPostScreen")
    data object StudentHomeScreen : Screens("StudentSplashScreen")
    data object StudentAdminDashboardScreen : Screens("StudentAdminDashboardScreen")
    data object StudentInboxScreen : Screens("StudentInboxScreen")
    data object StudentProfileScreen : Screens("StudentProfileScreen")
    data object StudentPublicCommunityPostViewScreen : Screens("StudentPublicCommunityPostViewScreen")
    data object StudentPublicCommunityScreen : Screens("StudentPublicCommunityScreen")
    // Web View
    data object WebViewScreen : Screens("WebViewScreen")
    data object WebMailScreen : Screens("WebMailScreen")
}