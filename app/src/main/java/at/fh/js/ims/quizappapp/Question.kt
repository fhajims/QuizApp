package at.fh.js.ims.quizappapp

data class Question (val questionText: String, val options: List<String>, val correctAnswer: Int)

   fun getQuestions() : MutableList<Question> {
       return mutableListOf(
           Question("What is the capital of Austria", listOf("Styria", "Armenia", "Vienna"), 2),
           Question("What is an integer?", listOf("A number", "A String", "A dog"), 0),
           Question("What is the capital of Japan?", listOf("Tokyo", "Beijing", "Seoul"), 0),
           Question("What is 2 + 2", listOf("3", "4", "5"), 1),
           Question("What is a mutable list of Strings", listOf("it is a seaweed","it is a mutable data structure that takes strings", "it is a can of soda"), 1)
       )
   }






