package edu.skku.cs.pa1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class WordAdapter (val context: Context, val wordleWords: ArrayList<WordleWord>):
    BaseAdapter() {
    override fun getCount(): Int {
        return wordleWords.size
    }

    override fun getItem(position: Int): Any {
        return wordleWords[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater =
            LayoutInflater.from(context)

        val view: View = inflater.inflate(R.layout.wordle_word, null)

        val letter1 = view.findViewById<TextView>(R.id.textViewLetter1)
        val letter2 = view.findViewById<TextView>(R.id.textViewLetter2)
        val letter3 = view.findViewById<TextView>(R.id.textViewLetter3)
        val letter4 = view.findViewById<TextView>(R.id.textViewLetter4)
        val letter5 = view.findViewById<TextView>(R.id.textViewLetter5)

        letter1.text = wordleWords[i].word[0].letter.toString()
        letter2.text = wordleWords[i].word[1].letter.toString()
        letter3.text = wordleWords[i].word[2].letter.toString()
        letter4.text = wordleWords[i].word[3].letter.toString()
        letter5.text = wordleWords[i].word[4].letter.toString()

        setLetterColor(letter1, i, 0)
        setLetterColor(letter2, i, 1)
        setLetterColor(letter3, i, 2)
        setLetterColor(letter4, i, 3)
        setLetterColor(letter5, i, 4)

        return view
    }

    fun setLetterColor(textView: TextView, i: Int, idx: Int)
    {
        textView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                wordleWords[i].word[idx].backgroundColor
            ))

        textView.setTextColor(
            ContextCompat.getColor(
                context,
                wordleWords[i].word[idx].textColor
            )
        )
    }
}