package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    GeoQuizScreen(
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

    fun nextQuestion() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            isAnswered = false
        } else {
            showResultDialog = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Текст вопроса
        Text(
            text = currentQuestion.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(1f)
                .padding(top = 32.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
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
        GeoQuizScreen()
    }
}