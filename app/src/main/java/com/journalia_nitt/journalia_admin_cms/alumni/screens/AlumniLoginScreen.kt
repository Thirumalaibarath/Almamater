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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoggedInAccount
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniAccountViewModel
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.saveUserDetails
import com.journalia_nitt.journalia_admin_cms.ui.theme.color_2
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun AlumniLoginScreen(
    innerPaddingValues: PaddingValues, navController: NavController
){
    var emailId by remember{ mutableStateOf("") }
    var passWord by remember{ mutableStateOf("") }
    val scrollState = rememberScrollState()
    val viewModel: AlumniAccountViewModel = viewModel()
    val loginStatus by viewModel.loginStatus.collectAsState()
    val loggedInAccount by viewModel.loggedInAccount.collectAsState()
    val context = LocalContext.current
    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = color_2)
        .padding(innerPaddingValues)
        .background(Color.White)
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
            text="Login To Alumni Dashboard",
            fontFamily = urbanist,
            fontSize = 18.sp,
        )
        AlumniRegistrationScreenTextFieldComponent(
            text = emailId,
            onValueChange = {
                emailId = it
            },
            label = "Email"
        )
        AlumniRegistrationScreenTextFieldComponent(
            text = passWord,
            onValueChange = {
                passWord = it
            },
            label = "Password"
        )
        Box(
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        {
            Text(
                text="Forgot Password?",
                fontFamily = urbanist,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.align(Alignment.CenterEnd)
                )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Text(
                text = "Don’t have an account?",
                fontFamily = urbanist,
                fontSize = 16.sp
            )
            Text(
                text = "Register",
                color= Color(14, 17, 186),
                fontFamily= urbanist,
                fontSize= 16.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.AlumniRegisterScreen.route)
                }
            )
        }
        Button(
            onClick = {
                if (emailId.isNotEmpty() && passWord.isNotEmpty()) {
                    viewModel.login(emailId, passWord)
                } else {
                    Toast.makeText(
                        context,
                        "Email and Password cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(224, 170, 255))
        ) {
            Text(
                text = "Go to Dashboard",
                color = Color.Black,
                fontFamily = urbanist,
                fontSize = 18.sp
            )
        }
        LaunchedEffect(loginStatus) {
            if (loginStatus.isNotEmpty()) {
                Toast.makeText(context, loginStatus, Toast.LENGTH_SHORT).show()
                if (loggedInAccount != null) {
                    theUser = loggedInAccount as LoggedInAccount
                    saveUserDetails(context = context,name = theUser.username, email = theUser.email, role = "alumni")
                    navController.navigate(Screens.AlumniHomeScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}