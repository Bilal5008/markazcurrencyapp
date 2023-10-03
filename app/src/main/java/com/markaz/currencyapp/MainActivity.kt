package com.markaz.currencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.markaz.currencyapp.base.Resource
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    var viewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]

    //    private lateinit var viewModel: CurrencyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MainScreen(this)
        }
    }


}

@Composable
fun MainScreen(mainActivity: MainActivity) {

    val viewModel: CurrencyViewModel = hiltViewModel()
    fetchDataFromApi(viewModel, mainActivity)
    Column {

    }


}

fun fetchDataFromApi(viewModel: CurrencyViewModel, mainActivity: MainActivity) {


    val getCarRegistrationObserver = Observer<Resource<CurrencyResponse>> { response ->
        when (response) {
            is Resource.Loading -> {

            }

            is Resource.Error -> {

            }

            is Resource.Success -> {
                response.data?.let {

                }
            }
        }
    }

    viewModel.getCurrencyPageData()
    viewModel.currencyLiveData.observe(mainActivity, getCarRegistrationObserver)


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
