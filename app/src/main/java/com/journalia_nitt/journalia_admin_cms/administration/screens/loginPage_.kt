package com.journalia_nitt.journalia_admin_cms.administration.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.LoginClient
import com.journalia_nitt.journalia_admin_cms.administration.SecretClient
import com.journalia_nitt.journalia_admin_cms.administration.response.LoginBody
import com.journalia_nitt.journalia_admin_cms.administration.response.SecretBody
import com.journalia_nitt.journalia_admin_cms.navigation.Screens_in_Admin_cms
import com.journalia_nitt.journalia_admin_cms.ui.theme.poppins

@Composable
fun LoginPage(
    innerPaddingValues: PaddingValues ,
    navController: NavController
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var emailId by remember{mutableStateOf("")}
    var passWord by remember{mutableStateOf("")}
    var isLoading by remember{mutableStateOf(false)}
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(innerPaddingValues)
        .background(color = Color.White)
        .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(100.dp,Alignment.CenterVertically)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "alma",
                    fontFamily = poppins,
                    fontSize = 50.sp,
                    color = Color(0XFF9667E0),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "mater",
                    fontFamily = poppins,
                    fontSize = 50.sp,
                    color = Color(0XFFBC80F0),
                    fontWeight = FontWeight.ExtraBold
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.raw.nitt_logo)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "SVG Logo",
                modifier = Modifier.size(100.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            var passwordVisible by remember {
                mutableStateOf(false)
            }
            Text(
                text = "Login to Admin Dashboard",
                fontFamily = poppins,
                fontSize = 18.sp,
                color = Color.Black
            )
            Column(
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            {
                Text(
                    text = "Email ID",
                    fontFamily = poppins,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = emailId,
                    onValueChange = {
                        emailId = it
                    },
                    modifier = Modifier.semantics { contentDescription = "mail-text-field" }
                        .border(
                            width = 1.5.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = false,
                    colors= OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource( R.drawable.mail),
                            contentDescription = "Password_Visibility",
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            }.size(25.dp),
                            tint = Color.Black
                        )
                    }
                )
            }
            Column( modifier = Modifier.fillMaxWidth(0.9f)){
                Text(
                    text = "Password",
                    fontFamily = poppins,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = passWord,
                    onValueChange = {
                        passWord = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .semantics { contentDescription = "Password input field" }
                        .border(
                            width = 1.5.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = false,
                    colors= OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(if(!passwordVisible) R.drawable.password_not_visible else R.drawable.password_visible),
                            contentDescription = "Password_Visibility",
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            }.size(25.dp),
                            tint = Color.Black
                        )
                    }
                )
            }
            if(isLoading) {
                CircularProgressIndicator()
            }
            Card(
                modifier = Modifier.padding(top = 10.dp)
                    .clickable {
                        coroutineScope.launch(Dispatchers.Main) {
                            if(emailId.isNotBlank()&& passWord.isNotBlank())
                            {
                                if(emailId == "google" || passWord == "nothing"){
                                    try {
                                        isLoading = true
                                        val response = LoginClient.login(LoginBody(emailId, passWord))
                                        if (response.isSuccessful) {
                                            isLoading = false
                                            passWord = ""
                                            Log.d("messageLogin", "success")
                                            navController.navigate(Screens_in_Admin_cms.SecretPage.createRoute(emailId))
                                        } else {
                                            //handle displaying that the process failed
                                            isLoading = false
                                            Log.d("messageLogin", response.message)
                                            passWord = ""
                                            navController.navigate(Screens_in_Admin_cms.LoginPage.route)
                                        }
                                    }
                                    catch (e: Exception) {
                                        isLoading = false
                                        passWord = ""
                                        Log.d("message", e.message.toString())
                                    }
                                    navController.navigate(Screens_in_Admin_cms.SecretPage.route)
                                }

                            }
                            else
                            {
                                Toast.makeText(context, "Please,enter both password and email!", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            )
            {
                Text(
                    text="Submit",
                    color=Color.White,
                    fontFamily= FontFamily(Font(R.font.poppins)),
                    fontSize=16.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
fun SecretChecking(
    email: String,
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var secret by remember{mutableStateOf("")}
    var isLoading by remember{mutableStateOf(false)}

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp,Alignment.CenterVertically)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "alma",
                    fontFamily = poppins,
                    fontSize = 50.sp,
                    color = Color(0XFF9667E0),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "mater",
                    fontFamily = poppins,
                    fontSize = 50.sp,
                    color = Color(0XFFBC80F0),
                    fontWeight = FontWeight.ExtraBold
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.raw.nitt_logo)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "SVG Logo",
                modifier = Modifier.size(100.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp))
        {
            Text(
                text = "Enter the Verification code sent to your Email ID",
                fontFamily = poppins,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            {
                OutlinedTextField(
                    value = secret,
                    onValueChange = {
                        secret = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = false,
                    colors= OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.End)
                ) {
                    Text(
                        text = "Didn't receive ?",
                        fontFamily = poppins,
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Send Again",
                        fontFamily = poppins,
                        color = Color(0XFF9667E0),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            if(isLoading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(top = 30.dp))
            }
            Spacer(modifier = Modifier.height(50.dp))
            Card(
                modifier = Modifier.padding(top = 10.dp)
                    .clickable {
                        if(secret.isNotBlank())
                        {
                            coroutineScope.launch(Dispatchers.Main) {
                                try {
                                    isLoading = true
                                    val response = SecretClient.secret(SecretBody(email, secret))
                                    if (response.token == null) {
                                        isLoading = false
                                        secret = ""
                                        Log.d("message", response.message)
                                        navController.navigate(Screens_in_Admin_cms.LoginPage.route)
                                    } else {
                                        isLoading = false
                                        secret = ""
                                        Log.d("token",response.token)
                                        navController.navigate(Screens_in_Admin_cms.LandingPage.createRoute(response.token))
                                    }
                                }
                                catch (e: Exception) {
                                    isLoading = false
                                    secret = ""
                                    Log.d("message", e.message.toString())
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(context, "Please,enter the secret code!", Toast.LENGTH_SHORT).show()
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0XFFE0AAFF)
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ){
                Text(
                    text="Go to Dashboard",
                    color=Color.Black,
                    fontFamily= FontFamily(Font(R.font.poppins)),
                    fontSize=16.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }
        }
    }
}


