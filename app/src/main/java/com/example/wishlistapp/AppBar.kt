package com.example.wishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.statusBarsPadding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AppBar(
    title: String,
    navController: NavHostController,
    backRoute: String? = null,
    isNavigationIconVisible: Boolean = true
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .heightIn(max = 24.dp)
            )
        },
        navigationIcon = {
            if (isNavigationIconVisible) {
                IconButton(
                    onClick = {
                        if (backRoute != null) {
                            navController.popBackStack(route = backRoute,inclusive = false)
                        } else {
                            navController.navigateUp()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        modifier = Modifier.statusBarsPadding(),
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.teal_700)
    )
}