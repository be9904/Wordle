package edu.skku.cs.pa1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var answer: String = ""

    // list of wordle words
    var triedWords = arrayListOf<WordleWord>()
    var outLetters = arrayListOf<WordleLetter>()
    var ballLetters = arrayListOf<WordleLetter>()
    var strikeLetters = arrayListOf<WordleLetter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // read words from file
        val inputStream = applicationContext.assets.open("wordle_words.txt")
        val words: String = inputStream.readBytes().toString(Charsets.UTF_8)
        inputStream.close()

        // split textString to words and put in list
        val wordArr = words.split('\n')

        // set answer
        answer = wordArr[(0..wordArr.size).random()] // set answer from txt file

        // log answer (erase later)
        println("answer: $answer")

        // Wordle word list view
        val wordList = findViewById<ListView>(R.id.listViewWords)

        // Wordle letter list Views
        val letterListGray = findViewById<ListView>(R.id.listViewGray)
        val letterListYellow = findViewById<ListView>(R.id.listViewYellow)
        val letterListGreen = findViewById<ListView>(R.id.listViewGreen)

        // create adapters
        val wordAdapter = WordAdapter(this, triedWords)
        val grayAdapter = LetterAdapter(this, outLetters)
        val yellowAdapter = LetterAdapter(this, ballLetters)
        val greenAdapter = LetterAdapter(this, strikeLetters)

        // input field and button
        val inputField = findViewById<EditText>(R.id.editTextWordle)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            // set adapters
            wordList.adapter = wordAdapter
            letterListGray.adapter = grayAdapter
            letterListYellow.adapter = yellowAdapter
            letterListGreen.adapter = greenAdapter

            if(inputField.text.toString().length == 5)
            {
                // word list view
                val result = tryWord(inputField.text.toString(), wordArr) // try word on btn click
                inputField.text.clear() // clear input field

                // letter list view
                addLetters(result)
            }
            else
            {
                Toast.makeText(
                    this,
                    "Word length not 5!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    //region WORDLE_WORD
    fun tryWord(word: String, wordArr: List<String>): WordleWord{
        // bool to check if word is in dictionary
        var wordFound: Boolean = false


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
            val createdWord = checkWord(createWord(word))
            triedWords.add(createdWord) // add wordle word to attempts list
            return createdWord
        }
        else // word not found in dict
        {
            Toast.makeText(
                this,
                "Word '${word}' not in dictionary!",
                Toast.LENGTH_LONG
            ).show()
            return createWord("     ")
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

    private fun checkWord(wordleWord: WordleWord) : WordleWord
    {
        for(i in 0..4)
        {
            if(checkPresence(i, wordleWord))
            {
                wordleWord.word[i].backgroundColor = R.color.background_ball
                wordleWord.word[i].textColor = R.color.text_ball
                if(checkLocation(i, wordleWord))
                {
                    wordleWord.word[i].backgroundColor = R.color.background_strike
                }
            }
            else
            {
                wordleWord.word[i].backgroundColor = R.color.background_out
                wordleWord.word[i].textColor = R.color.text_out
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
    //endregion

    //region WORDLE_LETTER
    fun addLetters(wordleWord: WordleWord)
    {
        for(i in 0..4)
        {
            // change to uppercase
            wordleWord.word[i].letter = wordleWord.word[i].letter.uppercaseChar()

            // check if alphabet
            if(wordleWord.word[i].letter < 'A' || wordleWord.word[i].letter > 'Z')
                continue
            when(wordleWord.word[i].backgroundColor)
            {
                R.color.background_strike -> {
                    for(l in ballLetters)
                        if(l.letter == wordleWord.word[i].letter){
                            ballLetters.remove(l)
                            break
                        }
                    if(!isDuplicate(wordleWord.word[i], strikeLetters))
                        strikeLetters.add(wordleWord.word[i])
                }
                R.color.background_ball   -> {
                    if(!isDuplicate(wordleWord.word[i], ballLetters))
                        ballLetters.add(wordleWord.word[i])
                }
                R.color.background_out    -> {
                    if(!isDuplicate(wordleWord.word[i], outLetters))
                        outLetters.add(wordleWord.word[i])
                }
                else -> println("Error when adding wordle letters to list")
            }
        }
    }

    fun isDuplicate(wordleLetter: WordleLetter, letterList: ArrayList<WordleLetter>) : Boolean
    {
        for(l in letterList)
        {
            if(wordleLetter.letter == l.letter)
                return true
        }

        return false
    }
    //endregion
}