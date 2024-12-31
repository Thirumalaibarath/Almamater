package com.journalia_nitt.journalia_admin_cms.administration.response

data class AdminDashBoardInfo(
    val token: String = "",
    val author: String = "SW Office",
    val title: String = "Hostel Fee Payment",
    val description: String = "",
    val deadline: String = "7 Aug 2025",
    val file_url: String? = "",
    val mode: Int = 0,
    val link1: String = "",
    val link2: String = "",
    val id: String = ""
)
data class UploadResponse(
    val message: String,
    val url: String
)

data class infoPageDetails(
    var title: String = "",
    var deadline: String = "",
    var author: String = "",
    var content: String = "",
    var pdfUrl: String = "",
    var link1: String = "",
    var link2: String = ""
)
data class Deadline(
    val author: String,
    val deadline: String,
    val description: String?,
    val file_url: String?,
    val link1: String?,
    val link2: String?,
    val mode: Int,
    val title: String?
)

data class fetchResponse(
    val message: String,
    val data: List<forEachUser>
)

data class forEachUser(
    val id: String,
    val details: List<announcement>
)

data class announcement(
    val author: String = "SW Office",
    val title: String = "Hostel Fee Payment",
    val description: String = "",
    val deadline: String = "7 Aug 2025",
    val file_url: String? = "",
    val mode: Int = 0,
    val link1: String = "",
    val link2: String = "",
    val id: String = "" // Add id field
)

data class DeleteResponse(
    val message: String,
    val post_id: String
)

