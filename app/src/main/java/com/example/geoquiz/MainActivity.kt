package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun GeoQuizScreen(modifier: Modifier = Modifier) {

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

    var isQuizFinished by remember { mutableStateOf(false) }

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
            isQuizFinished = true
        }
    }

    fun restartQuiz() {
        currentIndex = 0
        score = 0
        isAnswered = false
        isQuizFinished = false
    }

    if (isQuizFinished) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Квест выполнен",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Правильных ответов: ",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )

            Text(
                text = "$score / ${questions.size}",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { restartQuiz() },
                modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Ответить заново", fontSize = 18.sp)
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = currentQuestion.text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 32.dp),
                textAlign = TextAlign.Center
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!isAnswered) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { checkAnswer(true) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                        ) {
                            Text("Правда")
                        }

                        Button(
                            onClick = { checkAnswer(false) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                        ) {
                            Text("Ложь")
                        }
                    }
                }

                if (isAnswered && currentIndex < questions.size - 1) {
                    Button(
                        onClick = { nextQuestion() },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text("Дальше >")
                    }
                }

                if (isAnswered && currentIndex == questions.size - 1) {
                    Button(
                        onClick = { nextQuestion() },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text("Закончить")
                    }
                }
            }
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
        GeoQuizScreen()
    }
}