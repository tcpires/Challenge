package com.desafio.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.desafio.presentation.R
import com.desafio.presentation.ui.extensions.observeNotNull
import com.google.accompanist.appcompattheme.AppCompatTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val progressBar = findViewById<ComposeView>(R.id.progress_bar)
        progressBar.setContent {
            AppCompatTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    homeViewModel.loading.value?.let { CircularIndeterminateProgressBar(it) }
                }
            }
        }

        observeData()

    }

    private fun observeData() {
        homeViewModel.postalCodes.observeNotNull(this) {
            it.resources
            Toast.makeText(this, it.resources[0].name, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}