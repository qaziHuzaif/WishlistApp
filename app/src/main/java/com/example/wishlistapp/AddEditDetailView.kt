package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavHostController
) {
    val title: String =
        if (id != 0L) stringResource(R.string.update_wish) else stringResource(R.string.add_wish)

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                navController = navController
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.wishTitleState,
                onValueChange = { viewModel.onWishTitleChange(it) },
                label = { Text(text = "Title", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                textStyle = TextStyle.Default.copy(color = Color.Black)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.wishDescriptionState,
                onValueChange = { viewModel.onWishDescriptionChange(it) },
                label = { Text(text = "Description", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle.Default.copy(color = Color.Black)
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAddEditDetailView() {
    AddEditDetailView(
        id = 0,
        viewModel = WishViewModel(),
        navController = rememberNavController()
    )
}