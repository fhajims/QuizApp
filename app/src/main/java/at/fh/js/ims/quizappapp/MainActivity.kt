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

class MainActivity : AppCompatActivity() {


    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var submitButton: Button
    private val handler = Handler(Looper.getMainLooper())



    // Schedule a task to hide the image view after the specified time



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        questionText = findViewById(R.id.questionText)
        optionsGroup = findViewById(R.id.optionsGroup)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener { checkAnswer() }
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


        if (selectedOption.text == "Paris") {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            val  delayMillis = 6000
            playRightAnswer(this)
            val mrb = findViewById<ImageView>(R.id.mrb)
            //loadMRBIntoImageView(mrb, "mrb");
            makeImageViewVisible(mrb)
            handler.postDelayed({
                mrb.visibility = View.GONE
            }, delayMillis.toLong())
                handler.postDelayed({
                    // This code will run after the delay
                    val intent = Intent(this, SecondQuestion::class.java)
                    startActivity(intent)
                    finish()
                }, 6000.toLong())
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show()
            val  delayMillis = 3000
            playGoaty(this)
            playJumpScare(this)
            val dagoat = findViewById<ImageView>(R.id.goatygoat)
            //val mrb = findViewById<ImageView>(R.id.mrb)
            //loadGoatyIntoImageView(mrb, "goat");
            makeImageViewVisible(dagoat)
            handler.postDelayed({
                dagoat.visibility = View.GONE
            }, delayMillis.toLong())

        }






    }
}