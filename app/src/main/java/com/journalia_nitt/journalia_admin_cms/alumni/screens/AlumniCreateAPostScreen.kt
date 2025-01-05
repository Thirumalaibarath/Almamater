package com.journalia_nitt.journalia_admin_cms.alumni.screens

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.ContentResolver1
import com.journalia_nitt.journalia_admin_cms.alumni.EditState
import com.journalia_nitt.journalia_admin_cms.alumni.Uri
import com.journalia_nitt.journalia_admin_cms.alumni.clickedPost
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUpload
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoggedInAccount
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniUploadViewModel
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.FileUploadViewModel
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AlumniCreateAPostScreen(
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    val fileUploadModel: FileUploadViewModel = viewModel()
    val alumniUploadModel: AlumniUploadViewModel = viewModel()
    val scrollState = rememberScrollState()
    val uploadStatus by fileUploadModel.uploadFileStatus.collectAsState()
    val uploadedUrl by fileUploadModel.uploadedFileUrl.collectAsState()

    var isUploading by remember { mutableStateOf(false) }
    val context= LocalContext.current
    var commentState = remember { mutableStateOf(true) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Enable vertical scrolling
                .padding(start = 24.dp, end = 24.dp) // Add padding to the whole column
        ) {

            Text(
                text = "Title of your post",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )

            // Title Input Field
            val title = remember { mutableStateOf("") }
            OutlinedTextField(
                value = title.value,
                onValueChange = { title.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(99.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Description Label
            Text(
                text = "Description",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )

            // Description Input Field
            val description = remember { mutableStateOf("") }
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(298.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Attach Circular Button
            Box(
                modifier = Modifier
            ) {
                CustomFileUploadButton { fileData ->
                    if (fileData != null) {
                        theFileName.value = fileData.name
                        println("MIME Type: ${fileData.mimeType}")
                        println("File Content Size: ${fileData.content.size} bytes")
                    } else {
                        println("No file selected")
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
            // Important Links Label
            Text(
                text = "Important Links",
                fontFamily = urbanist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            // Important Link Input Field
            val link1= remember { mutableStateOf("") }
            OutlinedTextField(
                value = link1.value,
                onValueChange = { link1.value = it },
                modifier = Modifier
                    .height(53.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))
            // Important Link Input Field
            val link2 = remember { mutableStateOf("") }
            Row(modifier = Modifier.fillMaxWidth()
                .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text="Comments",
                        fontFamily = urbanist,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Button(
                        onClick = {
                            commentState.value = !commentState.value
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (commentState.value) Color(0xffa37fdb) else Color.Black,
                            contentColor = Color.White,
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if(commentState.value) "Enabled" else "Disabled",
                            fontFamily = urbanist,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                    }
                }
                val coroutineScope = rememberCoroutineScope()

                if(EditState.value){
                    UpdateButton(
                        fileUploadModel = fileUploadModel,
                        alumniUploadModel = alumniUploadModel,
                        context = context,
                        uri = Uri.value,
                        title = title.value,
                        description = description.value,
                        link1 = link1.value,
                        link2 = link2.value,
                        theUser = theUser,
                        navController = navController,
                        id = clickedPost.value.id
                    )
                }
                else{
                    UploadButton(
                        fileUploadModel = fileUploadModel,
                        alumniUploadModel = alumniUploadModel,
                        context = context,
                        uri = Uri.value,
                        title = title.value,
                        description = description.value,
                        link1 = link1.value,
                        link2 = link2.value,
                        theUser = theUser,
                        navController = navController
                    )
                }
            }
        }
    }
}

val theFileName = mutableStateOf("Attach Image (max 1 MB)")
val fileUploadMode = mutableStateOf(0)
@Composable
fun CustomFileUploadButton(onFileSelected: (FileData?) -> Unit) {
    val context = LocalContext.current

    // Launch the file picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri)

            Uri.value = uri
            ContentResolver1.value = contentResolver
            fileUploadMode.value = 1

            if (mimeType in listOf("image/png", "image/jpeg", "image/jpg", "application/pdf")) {
                val fileName = getFileName(contentResolver, uri)
                val fileContent = readFileContent(contentResolver, uri)

                onFileSelected(
                    FileData(
                        name = fileName,
                        mimeType = mimeType ?: "application/octet-stream",
                        content = fileContent
                    )
                )
                theFileName.value = fileName
            } else {
                Toast.makeText(context, "Invalid file type selected", Toast.LENGTH_SHORT).show()
                onFileSelected(null)
            }
        } else {
            onFileSelected(null)
        }
    }

    Box(
        modifier = Modifier
    ) {
        Button(
            onClick = {
                if (fileUploadMode.value == 0) {
                    launcher.launch(
                        arrayOf(
                            "image/png",
                            "image/jpeg",
                            "image/jpg",
                            "application/pdf"
                        )
                    )
                } else {
                    fileUploadMode.value = 0
                    theFileName.value = "Attach circular"
                    onFileSelected(null)
                }
            },
            modifier = Modifier
                .height(57.dp)
                .width(360.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 15.dp,
                pressedElevation = 10.dp
            )
        ) {}

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 30.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
        ) {
            Text(
                modifier = Modifier,
                text = theFileName.value,
                fontFamily = urbanist,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(300),
                maxLines = 1
            )
        }
        Image(
            painter = if (fileUploadMode.value == 0) painterResource(id = R.drawable.upload) else rememberVectorPainter(
                Icons.Default.Clear),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-30).dp)
        )
    }
}


data class FileData(
    val name: String,
    val mimeType: String,
    val content: ByteArray
)

fun getFileName(contentResolver: ContentResolver, uri: Uri): String {
    var fileName = "unknown_file"
    val cursor = contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (nameIndex >= 0 && it.moveToFirst()) {
            fileName = it.getString(nameIndex)
        }
    }
    return fileName
}

fun readFileContent(contentResolver: ContentResolver, uri: Uri): ByteArray {
    return contentResolver.openInputStream(uri)?.use { inputStream ->
        inputStream.readBytes()
    } ?: ByteArray(0)
}


@Composable
fun UploadButton(
    fileUploadModel: FileUploadViewModel,
    alumniUploadModel: AlumniUploadViewModel,
    context: Context,
    uri: Uri?,
    title: String,
    description: String,
    link1: String,
    link2: String,
    theUser: LoggedInAccount,
    navController: NavController
) {
    val uploadFileStatus by fileUploadModel.uploadFileStatus.collectAsState()
    val uploadedFileUrl by fileUploadModel.uploadedFileUrl.collectAsState()

    var isUploading by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (uri != null) {
                isUploading = true // Start showing the progress bar
                fileUploadModel.uploadFile(uri, context.contentResolver)
            } else {
                Toast.makeText(context, "Please select a file to upload.", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier.offset(y = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffa37fdb),
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isUploading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp) // Adjust the size if needed
            )
        } else {
            Text(
                text = "Upload",
                fontFamily = urbanist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    // React to changes in the upload status and URL
    LaunchedEffect(uploadFileStatus, uploadedFileUrl) {
        if (uploadedFileUrl.isNotEmpty()) {
            // File uploaded successfully, proceed with content upload
            alumniUploadModel.uploadContent(
                AlumniUpload(
                    title = title,
                    description = description,
                    link1 = link1,
                    link2 = link2,
                    file_url = uploadedFileUrl,
                    username = theUser.username
                )
            )

            if (alumniUploadModel.uploadStatus.value.isNotEmpty()) {
                Toast.makeText(context, alumniUploadModel.uploadStatus.value, Toast.LENGTH_SHORT).show()
            }

            isUploading = false // Stop showing the progress bar
            navController.popBackStack()
            Toast.makeText(context, "post uploaded successfully!", Toast.LENGTH_SHORT).show()
        } else if (uploadFileStatus.contains("Error")) {
            // Handle file upload error
            Toast.makeText(context, uploadFileStatus, Toast.LENGTH_SHORT).show()
            isUploading = false // Stop showing the progress bar
        }
    }
}

@Composable
fun UpdateButton(
    fileUploadModel: FileUploadViewModel,
    alumniUploadModel: AlumniUploadViewModel,
    context: Context,
    uri: Uri?,
    title: String,
    description: String,
    link1: String,
    link2: String,
    theUser: LoggedInAccount,
    navController: NavController,
    id: String
) {
    val uploadFileStatus by fileUploadModel.uploadFileStatus.collectAsState()
    val uploadedFileUrl by fileUploadModel.uploadedFileUrl.collectAsState()

    var isUploading by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (uri != null) {
                isUploading = true // Start showing the progress bar
                fileUploadModel.uploadFile(uri, context.contentResolver)
            } else {
                Toast.makeText(context, "Please select a file to upload.", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier.offset(y = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffa37fdb),
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isUploading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp) // Adjust the size if needed
            )
        } else {
            Text(
                text = "Update",
                fontFamily = urbanist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    // React to changes in the upload status and URL
    LaunchedEffect(uploadFileStatus, uploadedFileUrl) {
        if (uploadedFileUrl.isNotEmpty()) {
            // File uploaded successfully, proceed with content upload
            alumniUploadModel.uploadContent(
                AlumniUpload(
                    title = title,
                    description = description,
                    link1 = link1,
                    link2 = link2,
                    file_url = uploadedFileUrl,
                    username = theUser.username,
                    comments = clickedPost.value.comments,
                    upvoters = clickedPost.value.upvoters,
                    upvotes = clickedPost.value.upvotes
                )
            )

            if (alumniUploadModel.uploadStatus.value.isNotEmpty()) {
                Toast.makeText(context, alumniUploadModel.uploadStatus.value, Toast.LENGTH_SHORT).show()
            }

            isUploading = false // Stop showing the progress bar
            EditState.value = false
            repeat(2) {
                navController.popBackStack()
            }

            alumniUploadModel.deletePost(id)
            Toast.makeText(context, "post uploaded successfully!", Toast.LENGTH_SHORT).show()
        } else if (uploadFileStatus.contains("Error")) {
            // Handle file upload error
            Toast.makeText(context, uploadFileStatus, Toast.LENGTH_SHORT).show()
            isUploading = false // Stop showing the progress bar
        }
    }
}