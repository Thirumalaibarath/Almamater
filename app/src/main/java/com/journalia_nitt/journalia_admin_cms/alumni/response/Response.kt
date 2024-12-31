package com.journalia_nitt.journalia_admin_cms.alumni.response

// Data classes
data class GeneralResponse(val message: String)
data class LoginResponse(val message: String, val logged_account: LoggedInAccount)

data class LoggedInAccount(
    val username: String,
    val designation: String,
    val email: String
)
data class AlumniAccount(
    val username: String,
    val designation: String,
    val email: String,
    val password: String,
    val roll_number: String // camelCase convention
)

data class AlumniUpload(
    val username: String,
    val title: String,
    val description: String,
    val id: String = "",
    val file_url: String, // camelCase convention
    val link1: String = "",
    val link2: String = "",
    val comments: List<Comment> = emptyList(),
    var upvotes: Int = 0,
    val upvoters: List<String> = emptyList()
)

data class Comment(
    val comment: String,
    val username: String,
    val time: String? = null,
    val id: String? = null
)

data class UploadResponse(val message: String, val data: AlumniUpload)
data class FetchUploadsResponse(val message: String, val uploads: List<UploadOutput>)
data class CommentResponse(val message: String, val comment: Comment)

data class LoginBody(val email: String, val password: String)
data class Upvote(val username: String, val id: String)
// File upload response data class
data class FileUploadResponse(val message: String, val url: String)

data class UploadOutput(
    val uploads: List<AlumniUpload>
)
