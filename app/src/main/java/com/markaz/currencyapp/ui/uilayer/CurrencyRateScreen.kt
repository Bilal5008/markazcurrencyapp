package com.markaz.currencyapp.ui.uilayer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markaz.currencyapp.dto.apidto.CurrencyRateResponse
import com.markaz.currencyapp.network.base.ApiResponse
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CurrencyRateScreen(viewModel: StateFlow<ApiResponse<CurrencyRateResponse>>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleToolbar("Currency Conversion")
        CurrencyInputEditText()
    }

}

@Preview(showBackground = true)
@Composable
fun CurrencyInputEditText() {
    var textState by remember { mutableStateOf(TextFieldValue()) }

    LabelWithPlaceholder(
        "Enter Value for Conversion") {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun LabelWithPlaceholder(
    label: String,
    content: @Composable (modifier: Modifier) -> Unit
) {
    Column {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
        )
        Box(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            content(Modifier.fillMaxWidth())
        }
    }
}




