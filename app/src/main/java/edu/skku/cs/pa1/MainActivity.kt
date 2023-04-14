package edu.skku.cs.pa1

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {
    // list of attempted words
    var triedWords = arrayListOf<WordleWord>()
    var answer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = applicationContext.assets.open("wordle_words.txt")
        val words: String = inputStream.readBytes().toString(Charsets.UTF_8)
        inputStream.close()

        answer = "there" // set answer from txt file
        tryWord("these", words)

        val wordList = findViewById<ListView>(R.id.listViewWords)

        // List Views
        val letterListWords = findViewById<ListView>(R.id.listViewWords)
        val letterListGray = findViewById<ListView>(R.id.listViewGray)
        val letterListYellow = findViewById<ListView>(R.id.listViewYellow)
        val letterListGreen = findViewById<ListView>(R.id.listViewGreen)

        // create adapters
        val wordsLetterAdapter = WordAdapter(this, triedWords)
        // val grayAdapter = LetterAdapter(this, createWord("there"))

        val editTextWordle = findViewById<EditText>(R.id.editTextWordle)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            letterListWords.adapter = wordsLetterAdapter
            val word = "there"

            //tryWord(editTextWordle.text.toString())
        }
    }

    fun tryWord(word: String, textString: String){
        // bool to check if word is in dictionary
        var wordFound: Boolean = false

        // split textString to words and put in list
        val wordArr = textString.split('\n')

        // check if word is in dictionary
        for(wordleWord in wordArr)
        {
            if(word == wordleWord.trim())
            {
                wordFound = true
                break
            }
        }

        if(wordFound) // word found in dict
        {
            val createdWord = matchWithAnswer(createWord(word))
            triedWords.add(createdWord) // add wordle word to attempts list
            for(i in 0..4)
            {
                println(createdWord.word[i].letter + ", " +
                        createdWord.word[i].backgroundColor + ", " +
                        createdWord.word[i].textColor)
            }
//            val createdWordleWord = createWord(word)
//            for(i in 0..4)
//            {
//                Log.d("letter", createdWordleWord.word[i].letter.toString())
//            }
        }
        else // word not found in dict
        {
            Toast.makeText(
                this,
                "Word '${word}' not in dictionary!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // create a WordleWord out of String
    private fun createWord(word: String) : WordleWord
    {
        var letterArray = arrayListOf<WordleLetter>()

        for(element in word)
            letterArray.add(WordleLetter(element))

        return WordleWord(letterArray)
    }

    private fun matchWithAnswer(wordleWord: WordleWord) : WordleWord
    {
        for(i in 0..4)
        {
            if(checkPresence(i, wordleWord))
            {
                wordleWord.word[i].backgroundColor = WordleLetter.Color.YELLOW
                wordleWord.word[i].textColor = WordleLetter.Color.BLACK
                if(checkLocation(i, wordleWord))
                {
                    wordleWord.word[i].backgroundColor = WordleLetter.Color.GREEN
                }
            }
            else
            {
                wordleWord.word[i].backgroundColor = WordleLetter.Color.GRAY
                wordleWord.word[i].textColor = WordleLetter.Color.WHITE
            }
        }

        return wordleWord
    }

    private fun checkPresence(index: Int, wordleWord: WordleWord):Boolean
    {
        for(i in 0..4)
        {
            if(wordleWord.word[index].letter == answer[i])
                return true
        }

        return false
    }

    private fun checkLocation(index: Int, wordleWord: WordleWord):Boolean
    {
        if(wordleWord.word[index].letter == answer[index])
            return true

        return false
    }
}