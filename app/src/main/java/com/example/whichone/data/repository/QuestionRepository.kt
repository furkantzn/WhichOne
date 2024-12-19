package com.example.whichone.data.repository

import com.example.whichone.data.model.Option
import com.example.whichone.data.model.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class QuestionRepository {
    fun fetchQuestions(): Flow<List<Question>> {
        return flow {
            emit(
                listOf(
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "What is the capital of France?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Paris",
                                description = "Paris has been the capital of France since the 10th century.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Rome",
                                description = "Rome is the capital of Italy, not France.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "What is the chemical symbol for water?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "H2O",
                                description = "H2O represents two hydrogen atoms and one oxygen atom.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "CO2",
                                description = "CO2 is the chemical symbol for carbon dioxide.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "Who wrote Romeo and Juliet?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "William Shakespeare",
                                description = "Shakespeare is the famous playwright who authored this classic tragedy.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Charles Dickens",
                                description = "Dickens wrote novels like Oliver Twist and A Christmas Carol, not plays.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "Which planet is known as the Red Planet?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Mars",
                                description = "Mars appears red due to the iron oxide (rust) on its surface.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Venus",
                                description = "Venus is known as the 'Morning Star' or 'Evening Star,' not the Red Planet.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "How many continents are there on Earth?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Seven",
                                description = "The seven continents are Asia, Africa, North America, South America, Antarctica, Europe, and Australia.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Five",
                                description = "While the Olympics use five rings to represent unity, there are actually seven continents.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "What is the largest ocean on Earth?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "The Pacific Ocean",
                                description = "The Pacific Ocean covers about 63 million square miles, making it the largest.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "The Atlantic Ocean",
                                description = "While large, the Atlantic is smaller than the Pacific Ocean.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "What is the square root of 64?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "8",
                                description = "The square root of 64 is 8.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "6",
                                description = "6 is the square root of 36, not 64.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "Which organ pumps blood throughout the human body?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Heart",
                                description = "The heart is responsible for pumping blood to all parts of the body.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Liver",
                                description = "The liver performs functions like detoxification, not blood circulation.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "Who is known as the 'Father of Computers'?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Charles Babbage",
                                description = "Babbage is considered the 'Father of Computers' for designing the first mechanical computer.",
                                isCorrect = false
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "Alan Turing",
                                description = "Turing contributed to modern computing but is not referred to as the 'Father of Computers.'",
                                isCorrect = true
                            )
                        ),
                        userAnswer = ""
                    ),
                    Question(
                        id = UUID.randomUUID().toString(),
                        questionText = "What is the boiling point of water at sea level?",
                        options = listOf(
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "100°C",
                                description = "Water boils at 100°C (212°F) under standard atmospheric pressure.",
                                isCorrect = true
                            ),
                            Option(
                                id = UUID.randomUUID().toString(),
                                name = "90°C",
                                description = "Water does not boil at 90°C at sea level.",
                                isCorrect = false
                            )
                        ),
                        userAnswer = ""
                    )
                )
            )
        }
    }
}