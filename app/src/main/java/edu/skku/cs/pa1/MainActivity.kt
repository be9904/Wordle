package edu.skku.cs.pa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val letter : WordleLetter = WordleLetter('A', Color.GREEN, Color.BLACK)
//        letter.changeColor(Color.YELLOW)

        val wordList = findViewById<ListView>(R.id.listViewWords)

        // List Views
        val letterListWords = findViewById<ListView>(R.id.listViewWords)
        val letterListGray = findViewById<ListView>(R.id.listViewGray)
        val letterListYellow = findViewById<ListView>(R.id.listViewYellow)
        val letterListGreen = findViewById<ListView>(R.id.listViewGreen)

        // create adapters
        val wordsAdapter = LetterAdapter(this, createWord("there"))
        // val grayAdapter = LetterAdapter(this, createWord("there"))

        letterListWords.adapter = wordsAdapter

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val newWordleLetter = WordleLetter('A', Color.GREEN, Color.BLACK)
            val newTextView = TextView(this)

        }
    }

    private fun createWord(word: String) : ArrayList<WordleLetter>
    {
        var wordArray = arrayListOf<WordleLetter>()

        for(element in word)
            wordArray.add(WordleLetter(element))

        return wordArray
    }
}