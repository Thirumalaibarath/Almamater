package com.journalia_nitt.journalia_admin_cms.alumni.services

//interface AlumniUploadApi {
//    @POST("/alumni/upload/")
//    suspend fun uploadContent(@Body upload: AlumniUpload): Response<UploadResponse>
//
//    @GET("/alumni/uploads/")
//    suspend fun fetchAllUploads(): Response<FetchUploadsResponse>
//
//    @GET("/alumni/uploads/{username}")
//    suspend fun fetchUploadsByUsername(@Path("username") username: String): Response<FetchUploadsResponse>
//
//    @POST("/alumni/upload/{id}/comment/")
//    suspend fun addComment(@Path("id") id: String, @Body comment: Comment): Response<CommentResponse>
//
//    @POST("/alumni/upload/{id}/upvote/")
//    suspend fun upvotePost(@Path("id") id: String, @Body upvote: Upvote): Response<GeneralResponse>
//
//    @DELETE("/alumni/upload/{id}/delete/")
//    suspend fun deletePost(@Path("id") id: String): Response<GeneralResponse>
//
//    @POST("/alumni/upload/{id}/recreate/")
//    suspend fun recreatePost(@Path("id") id: String, @Body upload: AlumniUpload): Response<UploadResponse>
//}
// Data classes
//data class GeneralResponse(val message: String)
//data class UploadResponse(val message: String, val data: AlumniUpload)
//data class FetchUploadsResponse(val message: String, val uploads: List<UploadOutput>)
//data class CommentResponse(val message: String, val comment: Comment)
//data class Upvote(val username: String, val id: String)
// File upload response data class
// Example of initializing the file upload API
//val fileUploadApi: FileUploadApi = retrofit.create(FileUploadApi::class.java)



