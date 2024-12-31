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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniAccountViewModel
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.alumni.response.LoggedInAccount
import com.journalia_nitt.journalia_admin_cms.alumni.theUser
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_alumni_cms
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun LoginPage(innerPaddingValues: PaddingValues,navController: NavController){
    var rollNumber by remember{mutableStateOf("")}
    var emailId by remember{mutableStateOf("")}
    var passWord by remember{mutableStateOf("")}
    var scrollState = rememberScrollState()
    var viewModel: AlumniAccountViewModel = viewModel()
    val loginStatus by viewModel.loginStatus.collectAsState()
    val loggedInAccount by viewModel.loggedInAccount.collectAsState()
    val context = LocalContext.current
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)
        .background(Color(163, 127, 219))
        .verticalScroll(scrollState)
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
            contentAlignment = Alignment.Center
        ){
            Column(modifier=Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "alma mater",
                    color = Color.Black,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Image(
                    painter = painterResource(R.drawable.nitt),
                    contentDescription = "Logo",
                    modifier=Modifier.size(
                        height=92.dp,
                        width=94.dp
                    )
                )
                Text(
                    text="Login To Alumni DashBoard",
                    color=Color.White,
                    fontFamily= urbanist,
                    fontWeight=FontWeight(400),
                    fontSize=21.sp,
                    textAlign= TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(340.dp),
            contentAlignment = Alignment.Center){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .height(95.dp)){
                    Column(){
                        Text(
                            text = "Email Id",
                            color = Color.White,
                            fontFamily = urbanist,
                            fontWeight = FontWeight(400),
                            fontSize = 16.sp
                        )
                        OutlinedTextField(
                            value = emailId,
                            onValueChange = {emailId=it},
                            modifier = Modifier
                                .width(340.dp)
                                .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = false
                        )
                    }
                }
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .height(95.dp)){
                    Column(){
                        Text(
                            text = "Password",
                            color = Color.White,
                            fontFamily = urbanist,
                            fontWeight = FontWeight(400),
                            fontSize = 16.sp
                        )
                        OutlinedTextField(
                            value = passWord,
                            onValueChange = {passWord=it},
                            modifier = Modifier
                                .width(340.dp)
                                .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = false
                        )
                    }
                }
            }
        }
        Box(modifier= Modifier.fillMaxWidth()
            .height(30.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(
                text="Forgot Password?",
                modifier=Modifier.
                    padding(end=20.dp).
                    clickable{},
                color = Color.White,
                fontFamily = urbanist,
                fontWeight = FontWeight(400),
                fontSize = 18.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(135.dp),
            contentAlignment = Alignment.Center
        ) {
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
                modifier = Modifier.size(height = 57.dp, width = 240.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(224, 170, 255))
            ) {
                Text(
                    text = "Login",
                    color = Color.Black,
                    fontFamily = urbanist,
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp
                )
            }
        }
        LaunchedEffect(loginStatus) {
            if (loginStatus.isNotEmpty()) {
                Toast.makeText(context, loginStatus, Toast.LENGTH_SHORT).show()
                if (loggedInAccount != null) {
                    theUser = loggedInAccount as LoggedInAccount
                    navController.navigate(Screens_in_alumni_cms.landingPage.route) {
                        popUpTo(Screens_in_alumni_cms.loginPage.route) { inclusive = true }
                    }
                }
            }
        }
        Box(modifier= Modifier.fillMaxWidth()
            .height(30.dp)
            .clickable{
                navController.navigate(Screens_in_alumni_cms.registerPage.route)
            },
            contentAlignment = Alignment.Center
        ){
            Row {
                Text(
                    text = "Don’t have an account?",
                    modifier = Modifier.padding(end = 20.dp),
                    color = Color.White,
                    fontFamily = urbanist,
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp
                )
                Text(
                    text = "Register",
                    color=Color(14, 17, 186),
                    fontFamily= urbanist,
                    fontWeight=FontWeight(600),
                    fontSize=18.sp,
                )
            }
        }
    }
}