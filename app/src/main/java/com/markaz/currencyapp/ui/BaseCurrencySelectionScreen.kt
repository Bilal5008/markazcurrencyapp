package com.markaz.currencyapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markaz.currencyapp.dto.Currency
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.remote.erros.ApiError
import com.markaz.currencyapp.viewmodel.CurrencyViewModel

@Composable
fun BaseCurrencySelectionScreen(
    responseLiveData: CurrencyViewModel,
    selectedItem: (index: Currency) -> Unit
) {
    val state = responseLiveData.responseCurrencyStateFlow.collectAsState()
    val value = state.value

    var currencyList: List<Currency>

    when (value) {
        is ApiResponse.Success -> {
            currencyList = convertToCurrencyList(value.data.currencies) as MutableList<Currency>
            Column {
                SimpleToolbar("Currency application")
                HeadingWidget("Please Select your Base Currency")
                CurrencyListScreen(currencyList, selectedItem)
            }
        }

        is ApiResponse.Error -> {
            ErrorScreen(value.error)

        }

        is ApiResponse.Loading -> {
            LoadingScreen()
        }

    }
    responseLiveData.responseCurrencyRatesStateFlow.collectAsState()

}


fun convertToCurrencyList(currenciesMap: Map<String, String>?): List<Currency> {
    return currenciesMap?.map { (code, name) ->
        Currency(currencyCode = code, currencyName = name)
    } ?: emptyList()
}

@Composable
fun ErrorScreen(error: ApiError) {

}

@Composable
fun CurrencyListScreen(currencyList: List<Currency>, selectedItem: (index: Currency) -> Unit) {
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(currencyList) { index, item ->
            itemCurrency(item, selectedItem)
        }
    }


}

@Composable
fun LoadingScreen() {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary

        )

    }


}


@Composable
fun HeadingWidget(title: String) {

    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary // Set the text color
    )


}

@Composable
fun SimpleToolbar(title: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), // Set the desired height of the top bar
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White // Set the text color
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleToolbarPreview() {
    SimpleToolbar("Currency application")
}

@Preview(showBackground = true)
@Composable
fun HeadingWidgetpreview() {
    HeadingWidget("Select your Base Currency")
}

@Composable
fun itemCurrency(
    currencyItem: Currency,
    selectedItem: (index: Currency) -> Unit,
) {

    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(16.dp), // Background color of the card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp) // Optional: Rounded corners for the card
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable(
                    onClick = {
                        selectedItem(currencyItem)
                    },
                ),
                text = currencyItem.currencyName,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


