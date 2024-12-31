package com.journalia_nitt.journalia_admin_cms.administration.screens

import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.app.DatePickerDialog
import androidx.compose.material3.*
import com.journalia_nitt.journalia_admin_cms.administration.viewModels.FileUploadViewModel
import java.util.Calendar
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.mode
import com.journalia_nitt.journalia_admin_cms.administration.response.AdminDashBoardInfo
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun PostPage(
    token:String,
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    val Uri by remember{mutableStateOf<Uri?>(null)}
    val ContentResolver1 by remember{mutableStateOf<ContentResolver?>(null)}

    Log.d("tokenFromBackStack", token)
    val scrollState = rememberScrollState()
    val descriptionScrollState = rememberScrollState()
    val viewModel: FileUploadViewModel = viewModel()
    val context = LocalContext.current
    var isLoaded = viewModel.isLoaded.value
    val uploadVal = viewModel.uploadStatus.value
    val coroutineScope = rememberCoroutineScope()
    var showDatePicker by remember { mutableStateOf(false) }
    val isFieldBlank  = remember { mutableListOf(true,true,true) }
    var title by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    val theFileName = remember { mutableStateOf("Attach circular") }
    val fileUploadMode = remember { mutableIntStateOf(0) }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var description by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .background(color = Color.Transparent)
                .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back ),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                tint = Color.Black
            )
            Text(
                text = "CREATE",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(10.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = if (mode.value == 1) "Title of your Announcement" else "Title of your Deadline",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it
                                isFieldBlank[0] = title.isBlank()},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
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
            Text(
                text = "Description",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                    .verticalScroll(descriptionScrollState)
            )
            {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it
                        isFieldBlank[1] = description.isBlank()},
                    modifier = Modifier
                        .fillMaxSize(),
                    singleLine = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            var expanded by remember { mutableStateOf(false) }
            val items = listOf("Option 1", "Option 2", "Option 3","Option 1", "Option 2", "Option 3","Option 1", "Option 2", "Option 3","Option 1", "Option 2", "Option 3")
            var selectedItem by remember { mutableStateOf("") }
            Text(
                text = "Applicability",
                fontFamily = urbanist,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Column()
            {
                OutlinedTextField(
                    value = selectedItem,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Choose an option") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown icon",
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    }
                )
                DropdownMenu(
                    modifier = Modifier.height(200.dp).fillMaxWidth(0.9f),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedItem = option
                                isFieldBlank[2] = selectedItem.isBlank()
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomFileUploadButton(theFileName, fileUploadMode)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Deadline Date",
                    fontFamily = urbanist,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Box(modifier = Modifier.fillMaxWidth())
                {
                    Icon(
                        painter = painterResource(id = R.drawable.calender),
                        contentDescription = "calendar_icon",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 20.dp)
                            .size(25.dp)
                            .clickable {
                                showDatePicker = true
                            }
                        ,
                        tint = Color.Black
                    )
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = { selectedDate = it },
                        label = {
                            Text(modifier = Modifier.clickable { showDatePicker=true }, text = if(mode.value == 1) "Deadline (optional)" else "Deadline (mandatory)", fontSize = 12.sp, color = Color.LightGray)
                        },
                        readOnly = true,
                        enabled = false,
                        placeholder = { Text(modifier = Modifier.clickable { showDatePicker=true }, text = "dd/mm/yyyy", fontSize = 16.sp) },
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.Center),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledBorderColor =  Color.Black,
                            disabledLabelColor = Color.Black,
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (showDatePicker) {
                DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        showDatePicker = false
                    },
                    year,
                    month,
                    day
                ).apply {
                    setOnDismissListener { showDatePicker = false }
                }.show()
            }

            Text(
                text = "Important Links - 1",
                fontFamily = urbanist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            val link1 = remember { mutableStateOf("") }
            OutlinedTextField(
                value = link1.value,
                onValueChange = { link1.value = it },
                modifier = Modifier
                    .height(53.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Important Links - 2",
                fontFamily = urbanist,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            val link2 = remember { mutableStateOf("") }
            OutlinedTextField(
                value = link2.value,
                onValueChange = { link2.value = it },
                modifier = Modifier
                    .height(53.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LaunchedEffect(viewModel.uploadStatus.value) {
                when (uploadVal) {
                    "success" -> {
                        isLoaded = false
                        Toast.makeText(context, "File Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()
                        navController.popBackStack()
                    }
                    "failure" -> {
                        isLoaded = false
                        Toast.makeText(context, "File Upload Failed", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    "success1" -> {
                        isLoaded = false
                        Toast.makeText(context, "No File Selected", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoaded)
                    CircularProgressIndicator()
            }
            Card(
                modifier = Modifier.padding(bottom = 10.dp).align(Alignment.CenterHorizontally).fillMaxWidth(0.5f).clickable{
                    if(!isFieldBlank.contains(true) && fileUploadMode.value != 0)
                    {
                        isLoaded = true
                        val uri = Uri
                        val contentResolver = ContentResolver1
                        coroutineScope.launch(Dispatchers.IO) {
                            viewModel.uploadFile(uri, contentResolver)
                            withContext(Dispatchers.Main) {
                                viewModel.uploadDetailsDeadline(
                                    AdminDashBoardInfo(
                                        token = token,
                                        author = "adminOffice",
                                        title = title,
                                        description = description,
                                        deadline = selectedDate,
                                        file_url = viewModel.fileUrl.value,
                                        mode = mode.value,
                                        link1 = link1.value,
                                        link2 = link2.value
                                    )
                                )
                                fileUploadMode.value = 0
                                theFileName.value = "Attach circular"
                            }
                        }
                        isFieldBlank.replaceAll(
                            { false }
                        )
                    }
                    else
                    {
                        if (selectedDate == "" && mode.value == 0){
                            Toast.makeText(context, "Please select a deadline", Toast.LENGTH_LONG).show()
                            return@clickable
                        }
                        if(isFieldBlank[0])
                        {
                            Toast.makeText(context, "Please fill title", Toast.LENGTH_LONG).show()
                            return@clickable
                        }
                        else if(isFieldBlank[1])
                        {
                            Toast.makeText(context, "Please fill description", Toast.LENGTH_LONG).show()
                            return@clickable
                        }
                        else if(isFieldBlank[2])
                        {
                            Toast.makeText(context, "Please fill applicability", Toast.LENGTH_LONG).show()
                            return@clickable
                        }
                        else
                        {
                            Toast.makeText(context, "Please select a file", Toast.LENGTH_LONG).show()
                            return@clickable
                        }
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = Color((0xffa37fdb))
                ),
                elevation = CardDefaults.cardElevation(20.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(vertical = 10.dp), contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Upload",
                        fontFamily = urbanist,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
@Composable
fun CustomFileUploadButton(theFileName:MutableState<String>,fileUploadMode:MutableState<Int>) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri)
            fileUploadMode.value = 1

            if (mimeType in listOf("image/png", "image/jpeg", "image/jpg", "application/pdf")) {
                val fileName = getFileName(contentResolver, uri)
                val fileContent = readFileContent(contentResolver, uri)
                theFileName.value = fileName ?: "Unknown File"

                Log.d("FileName", "File Name: $fileName")
            } else {
                Toast.makeText(context, "Invalid file type selected", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .clickable {
                try {
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
                    }
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "No app available to select files", Toast.LENGTH_SHORT).show()
                }
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(vertical = 5.dp)
        )
        {
            Text(
                modifier = Modifier.align(Alignment.Center).padding(vertical = 10.dp),
                text = theFileName.value,
                fontFamily = urbanist,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                painter = if (fileUploadMode.value == 0) painterResource(id = R.drawable.attach_file) else rememberVectorPainter(Icons.Default.Clear),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
                    .size(25.dp)
                    .clickable {
                        theFileName.value = "yoo"
                    },
                tint = Color.Black
            )
        }
    }
}

fun getFileName(contentResolver: ContentResolver?, uri: Uri?): String {
    var fileName = "unknown_file"
    val cursor = uri?.let { contentResolver?.query(it, null, null, null, null) }
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