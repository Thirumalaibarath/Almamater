package com.journalia_nitt.journalia_admin_cms.student.responses

data class DeadlineResponse(
    val deadlines: List<DeadlineContainer>, // Updated to reflect the array
    val status: String // To match the "status" field
)

data class DeadlineContainer(
    val details: List<Deadline>, // Each object contains a "details" list
    val id: String // Each object contains an "id"
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

data class UserUploadClass(
    val token :String="",
    val title:String="",
    val description:String="",
    val comments: Boolean=false,
    val anonymity: Boolean=false,
    val tag:String="",
    val fileUrl:String?=null,
    val userName:String=""
)

data class userUploadResponse(
    val message:String="",
    val file_url:String="",
    val data:String=""
)

data class UserFetch(
    val message: String = "",
    val data: Data = Data()
)
data class UserFetch1(
    val message: String = "",
    val data: List<UserFetchClass> = emptyList()
)

data class UserFetchClass(
    val token :String="",
    val title:String="",
    val description:String="",
    val comments: Boolean=false,
    val anonymity: Boolean=false,
    val tag:String="",
    val fileUrl:String?=null,
    val userName:String="",
    val id:String="",
    val upvotes:Int=0,
    val commentsPosted:List<userComments> =emptyList()
)
data class Data(
    val details: List<UserFetchClass> = emptyList()
)

data class UploadResponse(
    val message: String,
    val url: String
)
data class BookMarkFetch(
    val message: String = "",
    val data: bookMarkClass = bookMarkClass()
)

data class bookMarkClass(
    val details: List<BookMark> = emptyList()
)

//ALUMNI FETCH
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

data class FetchUploadsResponse(val message: String, val uploads: List<UploadOutput>)
data class UploadOutput(
    val uploads: List<AlumniUpload>
)
data class CommentResponse(val message: String, val comment: Comment)
data class Upvote(val username: String, val id: String)
data class GeneralResponse(val message: String)

data class userComments(
    val text: String="",
    val commentedBy: String=""
)
