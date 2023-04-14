package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class LetterAdapter (val context: Context, val letters: ArrayList<WordleLetter>):
    BaseAdapter() {
    override fun getCount(): Int {
        return letters.size
    }

    override fun getItem(position: Int): Any {
        return letters[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater =
            LayoutInflater.from(context)

        val view: View = inflater.inflate(R.layout.wordle_letter, null)

        val textView = view.findViewById<TextView>(R.id.textViewLetter)

        textView.text = letters[i].letter.toString()
        textView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                letters[i].backgroundColor
            )
        )
        textView.setTextColor(
            ContextCompat.getColor(
                context,
                letters[i].textColor
            )
        )

        return view
    }
}