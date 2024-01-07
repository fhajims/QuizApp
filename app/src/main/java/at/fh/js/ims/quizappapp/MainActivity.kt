package at.fh.js.ims.quizappapp

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import at.fh.js.ims.quizappapp.Question
import at.fh.js.ims.quizappapp.QuestionCounter
import at.fh.js.ims.quizappapp.RandomNumberGenerator

class MainActivity : AppCompatActivity() {


    private val questionCounter = QuestionCounter()

    // Question Text
    private lateinit var questionText: TextView
    // Options Group
    private lateinit var optionsGroup: RadioGroup
    private lateinit var submitButton: Button
    private val handler = Handler(Looper.getMainLooper())
    //QuestionTextView
    private lateinit var correctQuestionTextView : TextView

    // Radio Buttons
    private lateinit var radioButton1 : RadioButton
    private lateinit var radioButton2 : RadioButton
    private lateinit var radioButton3 : RadioButton
    private var randomInt : Int = 0
    // List of Questions
    private val questions = getQuestions()
    private lateinit var currentQuestion : Question



    // Schedule a task to hide the image view after the specified time



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randng = RandomNumberGenerator(1,questions.size-1).nextInt()
        val selectedQuestion = questions[randng]



        correctQuestionTextView = findViewById(R.id.CounterOfQuestions)
        correctQuestionTextView.setText(questionCounter.returnTotalCount().toString())
        questionText = findViewById(R.id.questionText)
        optionsGroup = findViewById(R.id.optionsGroup)
        radioButton1 = findViewById(R.id.option1)
        radioButton2 = findViewById(R.id.option2)
        radioButton3 = findViewById(R.id.option3)

        /*    val min = 1
           val max = questions.size
           val randomInt = (min..max).random()
           val question = questions[2]
          questionText.text = question.questionText
           val radioButton1Text: String = question.options[0]
           radioButton1.text = radioButton1Text
           val radioButton2Text : String = question.options[1]
           radioButton2.text = radioButton2Text
           val radioButton3Text : String = question.options[2]
           radioButton3.text = radioButton3Text  */

        setupQuestion()
        submitButton = findViewById(R.id.submitButton)
        submitButton.setOnClickListener { checkAnswer() }
    }

    private fun setupQuestion() {
        if (questions != null) {
            val min = 0
            val max = questions.size-1
            randomInt = (min..max).random()
            //val question = questions[randomInt]
            currentQuestion = questions[randomInt]
            questionText.text = currentQuestion.questionText
            radioButton1.text = currentQuestion.options[0]
            radioButton2.text = currentQuestion.options[1]
            radioButton3.text = currentQuestion.options[2]
        } else {

        }
    }


    private fun makeImageViewVisible(imageView: ImageView) {
        imageView.visibility = View.VISIBLE
    }

    fun playJumpScare(context: Context) {
        val jumpScareSound = MediaPlayer.create(context, R.raw.dubisteinnoob)

        jumpScareSound.setOnCompletionListener {
            it.release()

        }

        jumpScareSound.start()
    }
    fun playRightAnswer(context: Context) {
        val rightAnswerSound = MediaPlayer.create(context, R.raw.mrbombastic)

        rightAnswerSound.setOnCompletionListener {
            it.release()

        }

        rightAnswerSound.start()
    }
    fun playGoaty(context: Context) {
        val goatySound = MediaPlayer.create(context, R.raw.goaty)

        goatySound.setOnCompletionListener {
            it.release()

        }

        goatySound.start()
    }

    fun loadMRBIntoImageView(imageView: ImageView, imageSource: String) {
        val imageBitmap: Bitmap
            val imageResource = R.drawable.mrb
            imageBitmap = BitmapFactory.decodeResource(imageView.resources, imageResource)
    }
    fun loadGoatyIntoImageView(imageView: ImageView, imageSource: String) {
        val imageBitmap: Bitmap
        val imageResource = R.drawable.goat
        imageBitmap = BitmapFactory.decodeResource(imageView.resources, imageResource)
    }


        private fun checkAnswer() {
        val selectedOptionId = optionsGroup.checkedRadioButtonId

        if (selectedOptionId == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            return
        }







            val selectedOption = findViewById<RadioButton>(selectedOptionId)


            val intent = Intent(this, SecondQuestion::class.java)


        if (selectedOption.text == currentQuestion.options[currentQuestion.correctAnswer]) {
            questionCounter.incrementTotalCount()
            correctQuestionTextView.setText(" ${questionCounter.returnTotalCount()} / 16 Fragen beantwortet")
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            val  delayMillis = 6000
            playRightAnswer(this)
            val mrb = findViewById<ImageView>(R.id.mrb)
            //loadMRBIntoImageView(mrb, "mrb");
            makeImageViewVisible(mrb)
            handler.postDelayed({
                mrb.visibility = View.GONE
                updateUIElements(questions,randomInt)
            }, delayMillis.toLong())
              /*  handler.postDelayed({
                    // This code will run after the delay
                    //val intent = Intent(this, MainActivity::class.java)
                    //startActivity(intent)
                    //finish()

                }, 6000.toLong()) */
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show()
            val  delayMillis = 3000
            playGoaty(this)
            //playJumpScare(this)
            val dagoat = findViewById<ImageView>(R.id.goatygoat)
            //val mrb = findViewById<ImageView>(R.id.mrb)
            //loadGoatyIntoImageView(mrb, "goat");
            makeImageViewVisible(dagoat)
            handler.postDelayed({
                dagoat.visibility = View.GONE
            }, delayMillis.toLong())


        }






    }

    fun updateUIElements(questionList: MutableList<Question>, justanint: Int) {
        val indexToRemove = justanint
        if (indexToRemove >= 0 && indexToRemove < questionList.size) {
            questionList.removeAt(indexToRemove)
            setupQuestion()




    }



    }


}