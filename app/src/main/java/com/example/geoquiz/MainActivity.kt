package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {

    data class Question(
        val text: String,
        val correctAnswer: Boolean
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun GeoQuizScreen(modifier: Modifier = Modifier){

    val questions = listOf(
        MainActivity.Question("Canberra is the capital of Australia.", true),
        MainActivity.Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        MainActivity.Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        MainActivity.Question("The source of the Nile River is in Egypt.", false),
        MainActivity.Question("The Amazon River is the longest river in the Americas.", true),
        MainActivity.Question(
            "Lake Baikal is the world's oldest and deepest freshwater lake.",
            true
        )
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var isAnswered by remember { mutableStateOf(false) }
    var showResultDialog by remember { mutableStateOf(false) }


    val currentQuestion = questions[currentIndex]

    fun checkAnswer(userAnswer: Boolean) {
        if (!isAnswered) {
            if (userAnswer == currentQuestion.correctAnswer) {
                score++
            }
            isAnswered = true
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoQuizTheme {
        Greeting("Android")
    }
}