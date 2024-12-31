package com.journalia_nitt.journalia_admin_cms.navigation

sealed class Screens_in_alumni_cms(val route: String) {
    object loginPage: Screens_in_alumni_cms("login_page")
    object landingPage: Screens_in_alumni_cms("landing_page")
    object postPage: Screens_in_alumni_cms("post_page")
    object registerPage: Screens_in_alumni_cms("register_page")
    object communityPage: Screens_in_alumni_cms("community_page")
    object commentsPage: Screens_in_alumni_cms("comments_page")
    object page: Screens_in_alumni_cms("page")
    object contactUs: Screens_in_alumni_cms("contact_us")
}