package com.markaz.currencyapp.ui.uilayer


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.markaz.currencyapp.network.erros.ApiError


@Composable
fun AlertDialogSample(errorMessage: ApiError) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },

                    text = {
                        Text(
                            text = errorMessage.message + " " + errorMessage.actualCode,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary // Set the text color
                        )
                    },
                    confirmButton = {

                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }

    }
}

