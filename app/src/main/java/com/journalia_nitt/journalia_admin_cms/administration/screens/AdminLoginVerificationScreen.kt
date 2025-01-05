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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.administration.SecretClient
import com.journalia_nitt.journalia_admin_cms.administration.response.SecretBody
import com.journalia_nitt.journalia_admin_cms.navigation.Screens
import com.journalia_nitt.journalia_admin_cms.student.sharedPreferences.saveUserDetails
import com.journalia_nitt.journalia_admin_cms.ui.theme.poppins
import com.journalia_nitt.journalia_admin_cms.ui.theme.urbanist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AdminLoginVerificationScreen(
    email:String,
    innerPaddingValues: PaddingValues,
    navController: NavController
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var secret by remember{ mutableStateOf("") }
    var isLoading by remember{ mutableStateOf(false) }

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "alma",
                    fontSize = 50.sp,
                    fontFamily = urbanist,
                    color = Color(0XFF9667E0),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "mater",
                    fontFamily = urbanist,
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
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)
                ) {
                    Text(
                        text = "Didn't receive ?",
                        fontFamily = poppins,
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, email, Toast.LENGTH_SHORT).show()
                        }
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
                                        Toast.makeText(context, "Invalid secret code!", Toast.LENGTH_SHORT).show()
                                        navController.navigate(Screens.AdminLoginScreen.route)
                                        isLoading = false
                                        secret = ""
                                    } else {
                                        saveUserDetails(context = context,name = email,email = email,role = "admin")
                                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                        navController.navigate(Screens.AdminHomeScreen.route)
                                        isLoading = false
                                        secret = ""
                                    }
                                }
                                catch (e: Exception) {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
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
                    color= Color.Black,
                    fontFamily= FontFamily(Font(R.font.poppins)),
                    fontSize=16.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }
        }
    }
}