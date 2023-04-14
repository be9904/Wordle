package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WordAdapter (val context: Context, val wordleWords: ArrayList<WordleWord>):
    BaseAdapter() {
    override fun getCount(): Int {
        return wordleWords.size
    }

    override fun getItem(position: Int): Any {
        return wordleWords.get(position)
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

        letter1.setBackgroundColor(wordleWords[i].word[0].backgroundColor.rgb)
        letter2.setBackgroundColor(wordleWords[i].word[1].backgroundColor.rgb)
        letter3.setBackgroundColor(wordleWords[i].word[2].backgroundColor.rgb)
        letter4.setBackgroundColor(wordleWords[i].word[3].backgroundColor.rgb)
        letter5.setBackgroundColor(wordleWords[i].word[4].backgroundColor.rgb)

        letter1.setTextColor(wordleWords[i].word[0].textColor.rgb)
        letter2.setTextColor(wordleWords[i].word[1].textColor.rgb)
        letter3.setTextColor(wordleWords[i].word[2].textColor.rgb)
        letter4.setTextColor(wordleWords[i].word[3].textColor.rgb)
        letter5.setTextColor(wordleWords[i].word[4].textColor.rgb)

        return view
    }
}