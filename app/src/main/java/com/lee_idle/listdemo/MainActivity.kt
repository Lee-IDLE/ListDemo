package com.lee_idle.listdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lee_idle.listdemo.ui.theme.ListDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScree()
                }
            }
        }
    }
}

@Composable
fun MainScree() {
    //ColumnList()
    RowList()
}

@Composable
fun ColumnList() {
    val scrollstate = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollstate.animateScrollTo(0)
                    }
            },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)){
                Text("Top")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollstate.animateScrollTo(scrollstate.maxValue)
                    }
            },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)){
                Text("End")
            }
        }

        Column(Modifier.verticalScroll(scrollstate)) {
            repeat(500) {
                Text(
                    "List Item $it",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@Composable
fun RowList() {
    val scrollstate = rememberScrollState()

    Row(Modifier.horizontalScroll(scrollstate)){
        repeat(50) {
            Text(
                " $it",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(5.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ListDemoTheme {
        MainScree()
    }
}