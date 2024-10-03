package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

const val MESSAGE_KEY = "message"
const val RESULT_KEY = "reply_success"

class DisplayActivity : AppCompatActivity() {

    // TODO Step 1: Launch TextSizeActivity when button clicked to allow selection of text size value

    // A protocol system, this explicit piece is needed to launch an Activity along with an Intent. This is the launcher.
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.apply {

                // Step 3: Set the return value, the text size, and set it to lyricsDisplayTextView
                // lyricsDisplayTextView.textSize = intent.getIntExtra(RESULT_KEY, 22).toFloat() WRONG
                lyricsDisplayTextView.textSize = it?.data.run {
                    getIntExtra(RESULT_KEY, 22).toFloat()
                }
            }
        }
    }


    // TODO Step 3: Use returned value for lyricsDisplayTextView text size

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        // Kind of doing to do, step 1 here with launching the second activity
        // Step 1: Launch the textSizeActivity because the textSizeSelectorButton has been clicked
        textSizeSelectorButton.setOnClickListener {
            val launchIntent = Intent(this@DisplayActivity, TextSizeActivity::class.java)
            launchIntent.putExtra(MESSAGE_KEY, "Hello! This is a message to TextSizeActivity")
            launcher.launch(launchIntent)
        }

    }
}