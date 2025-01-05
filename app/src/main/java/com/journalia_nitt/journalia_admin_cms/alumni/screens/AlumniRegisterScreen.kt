package com.journalia_nitt.journalia_admin_cms.alumni.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniAccount
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniUserDetails
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniAccountViewModel
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.ui.theme.color_2
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AlumniRegisterScreen(
    innerPaddingValues: PaddingValues, navController: NavController
){
    var userDetails by remember { mutableStateOf(AlumniUserDetails())  }
    val emailId by remember{ mutableStateOf("") }
    val viewModel: AlumniAccountViewModel = viewModel()
    val context = LocalContext.current
    val createStatus by viewModel.createStatus.collectAsState()
    val loginStatus by viewModel.loginStatus.collectAsState()
    LaunchedEffect(createStatus) {
        if (createStatus.contains("success", ignoreCase = true)) {
            viewModel.login(emailId, userDetails.password)
        } else if (createStatus.isNotEmpty()) {
            Toast.makeText(context, "Error creating account: $createStatus", Toast.LENGTH_LONG).show()
        }
    }
    LaunchedEffect(loginStatus) {
        if (loginStatus.contains("success", ignoreCase = true)) {
            val user = viewModel.loggedInAccount.value
            if (user != null) {
                Toast.makeText(context, "Welcome, ${user.username}", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens.AlumniHomeScreen.route) {
                    popUpTo(Screens.AlumniRegisterScreen.route) { inclusive = true }
                }
            }
        } else if (loginStatus.isNotEmpty()) {
            Toast.makeText(context, "Error logging in: $loginStatus", Toast.LENGTH_LONG).show()
        }
    }
    val scrollState = rememberScrollState()
    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = color_2)
        .padding(innerPaddingValues)
        .background(color = Color.White)
        .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "alma",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 50.sp,
                    color = Color(150,103,224)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text="mater",
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontSize = 50.sp,
                    color = Color(188, 128, 240)
                )
            }
            Text(
                text="made for NIT Trichy",
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontSize = 15.sp,
                color = Color(150,103,224)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.raw.nitt_logo)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "SVG Logo",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="Register To Alumni Dashboard",
            fontFamily = urbanist,
            fontSize = 18.sp,
        )
        AlumniRegistrationScreenTextFieldComponent(
            text = userDetails.username,
            onValueChange = {
                userDetails = userDetails.copy(username = it)
            },
            label = "Username"
        )

        AlumniRegistrationScreenTextFieldComponent(
            text = userDetails.designation,
            onValueChange = {
                userDetails = userDetails.copy(designation = it)
            },
            label = "Designation"
        )
        AlumniRegistrationScreenTextFieldComponent(
            text = userDetails.rollNumber,
            onValueChange = {
                userDetails = userDetails.copy(rollNumber = it)
            },
            label = "Roll Number"
        )
        AlumniRegistrationScreenTextFieldComponent(
            text = userDetails.password,
            onValueChange = {
                userDetails = userDetails.copy(password = it)
            },
            label = "Password"
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Already have an account?",
                fontFamily = urbanist,
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            )
            Text(
                text = "Login",
                color= Color(14, 17, 186),
                fontFamily= urbanist,
                fontSize=14.sp,
                modifier= Modifier.clickable { navController.navigate(Screens.AlumniLoginScreen.route) }
            )
        }
        Button(onClick = {
            if (userDetails.rollNumber.isNotEmpty() && userDetails.username.isNotEmpty() && emailId.isNotEmpty() && userDetails.password.isNotEmpty() && userDetails.designation.isNotEmpty()) {
                val account = AlumniAccount(
                    roll_number = userDetails.rollNumber,
                    username = userDetails.username,
                    email = emailId,
                    password = userDetails.password,
                    designation = userDetails.designation
                )
                viewModel.createAccount(account)
            } else {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        },
            shape= RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(224, 170, 255))
        ) {
            Text(
                text="Go to Dashboard",
                color= Color.Black,
                fontFamily= urbanist,
                fontSize=20.sp
            )
        }
    }
}

@Composable
fun AlumniRegistrationScreenTextFieldComponent(
    text : String,
    onValueChange: (String) -> Unit,
    label :String
)
{
    Column(){
        Text(
            text = label,
            fontFamily = urbanist,
            fontWeight = FontWeight(400),
            fontSize = 16.sp
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(0.8f)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            maxLines = 1
        )
    }
}
