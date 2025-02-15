package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Checkbox
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CheckboxDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavHostController
) {
    if (id != 0L) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
        viewModel.wishImportantState = wish.value.important
    } else {
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
        viewModel.wishImportantState = false
    }

    val title: String =
        if (id != 0L) stringResource(R.string.update_wish) else stringResource(R.string.add_wish)

    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                navController = navController
            )
        },
        backgroundColor = Color.White,
        scaffoldState = scaffoldState
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
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
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

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mark as Important",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Checkbox(
                    checked = viewModel.wishImportantState,
                    onCheckedChange = { checked ->
                        viewModel.onWishImportantChange(checked)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(R.color.teal_700)
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {
                    if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
                        if (id != 0L) {
                            // Update wish
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim(),
                                    important = viewModel.wishImportantState
                                )
                            )
                            //snackMessage.value = "Wish has been updated"
                        } else {
                            // Add wish
                            viewModel.addWish(
                                Wish(
                                    title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim(),
                                    important = viewModel.wishImportantState
                                )
                            )
                            //snackMessage.value = "Wish has been created"
                        }
                        navController.navigateUp()
                    } else {
                        snackMessage.value = "Enter fields to create a wish"
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    }
                }
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.teal_700)
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