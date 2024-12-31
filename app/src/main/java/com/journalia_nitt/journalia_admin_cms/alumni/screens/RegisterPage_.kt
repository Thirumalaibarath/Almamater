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
import com.journalia_nitt.journalia_admin_cms.alumni.response.AlumniAccount
import com.journalia_nitt.journalia_admin_cms.alumni.viewModels.AlumniAccountViewModel
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_alumni_cms
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist

@Composable
fun RegisterPage(innerPaddingValues: PaddingValues,navController: NavController){
    var rollNumber by remember{mutableStateOf("")}
    var designation by remember { mutableStateOf("") }
    var userName by remember{mutableStateOf("")}
    var emailId by remember{mutableStateOf("")}
    var passWord by remember{mutableStateOf("")}
    var viewModel: AlumniAccountViewModel = viewModel()
    val context = LocalContext.current
    val createStatus by viewModel.createStatus.collectAsState()
    val loginStatus by viewModel.loginStatus.collectAsState()

    LaunchedEffect(createStatus) {
        if (createStatus.contains("success", ignoreCase = true)) {
            // Auto-login after successful account creation
            viewModel.login(emailId, passWord)
        } else if (createStatus.isNotEmpty()) {
            Toast.makeText(context, "Error creating account: $createStatus", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(loginStatus) {
        if (loginStatus.contains("success", ignoreCase = true)) {
            val user = viewModel.loggedInAccount.value
            if (user != null) {
                Toast.makeText(context, "Welcome, ${user.username}", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens_in_alumni_cms.landingPage.route) {
                    popUpTo(Screens_in_alumni_cms.registerPage.route) { inclusive = true }
                }
            }
        } else if (loginStatus.isNotEmpty()) {
            Toast.makeText(context, "Error logging in: $loginStatus", Toast.LENGTH_LONG).show()
        }
    }
    val scrollState = rememberScrollState()
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)
        .background(Color(163, 127, 219))
        .verticalScroll(scrollState)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
            contentAlignment = Alignment.Center
        ){
            Column(modifier=Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "alma mater",
                    color = Color.Black,
                    fontFamily = urbanist,
                    fontWeight = FontWeight(600),
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
                    text="Register To Alumni Dashboard",
                    color=Color.White,
                    fontFamily= urbanist,
                    fontWeight=FontWeight(400),
                    fontSize=21.sp,
                    textAlign= TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 35.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier= Modifier
                .fillMaxWidth()
                .height(115.dp)){
                Column()
                {
                    Text(
                        text = "Username",
                        color = Color.White,
                        fontFamily = urbanist,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = userName,
                        onValueChange = {userName=it},
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
                Column()
                {
                    Text(
                        text = "Designaiton",
                        color = Color.White,
                        fontFamily = urbanist,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = designation,
                        onValueChange = {designation=it},
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
                .height(115.dp)){
                Column()
                {
                    Text(
                        text = "Roll Number",
                        color = Color.White,
                        fontFamily = urbanist,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = rollNumber,
                        onValueChange = {rollNumber=it},
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
                .height(115.dp)){
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
                .height(115.dp)){
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
        Box(modifier= Modifier.fillMaxWidth()
            .height(30.dp),
            contentAlignment = Alignment.Center
        ){
            Row {
                Text(
                    text = "Already have an account?",
                    modifier = Modifier.padding(end = 20.dp),
                    color = Color.White,
                    fontFamily = urbanist,
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp
                )
                Text(
                    text = "Login",
                    color=Color(14, 17, 186),
                    fontFamily= urbanist,
                    fontWeight=FontWeight(600),
                    fontSize=18.sp,
                    modifier=Modifier.clickable{
                        navController.popBackStack()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(135.dp),
            contentAlignment = Alignment.Center
        ){
            Button(onClick = {
                    if (rollNumber.isNotEmpty() && userName.isNotEmpty() && emailId.isNotEmpty() && passWord.isNotEmpty() && designation.isNotEmpty()) {
                        val account = AlumniAccount(
                            roll_number = rollNumber,
                            username = userName,
                            email = emailId,
                            password = passWord,
                            designation = designation
                        )
                        viewModel.createAccount(account)
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier=Modifier.size(height=57.dp, width=240.dp),
                shape= RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(224, 170, 255))
            ) {
                Text(
                    text="Register",
                    color=Color.Black,
                    fontFamily= urbanist,
                    fontWeight=FontWeight(600),
                    fontSize=20.sp
                )
            }
        }
    }
}

