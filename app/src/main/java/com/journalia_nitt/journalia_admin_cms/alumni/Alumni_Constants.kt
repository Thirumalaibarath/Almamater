package com.journalia_nitt.journalia_admin_cms.alumni

import android.content.ContentResolver
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUpload
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoggedInAccount
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_alumni_cms

var clickedPost = mutableStateOf(
    AlumniUpload(
        username = "",
        title = "",
        description = "",
        file_url = "",
        link1 = "",
        link2 = "",
        comments = emptyList(),
        upvotes = 0,
        upvoters = emptyList()
    )
)
val landingPageButtonTexts = listOf(
    Pair("ALUMNI COMMUNITY" , Screens_in_alumni_cms.communityPage),
    Pair("CREATE A POST", Screens_in_alumni_cms.postPage)
)
val EditState = mutableStateOf(false)
var theUser = LoggedInAccount(
    username = "",
    email = "",
    designation = ""
)
val font = FontFamily(Font(R.font.urbanist))
var Uri = mutableStateOf<Uri?>(null)
var ContentResolver1 = mutableStateOf<ContentResolver?>(null)
