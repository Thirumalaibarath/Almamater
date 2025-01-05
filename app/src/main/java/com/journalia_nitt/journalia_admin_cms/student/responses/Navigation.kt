package com.journalia_nitt.journalia_admin_cms.student.responses

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Navigation(
    val route: String,
    val icon: ImageVector,
    val text:String,
    val size:Int,
    val tint: Color,
    val isText:Boolean
    )
