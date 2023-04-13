package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class LetterAdapter (val context: Context, val letters: ArrayList<WordleLetter>):
    BaseAdapter() {
    override fun getCount(): Int {
        return letters.size
    }

    override fun getItem(position: Int): Any {
        return letters.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater =
            LayoutInflater.from(context)

        val view: View = inflater.inflate(R.layout.wordle_letter, null)

        val textView = view.findViewById<TextView>(R.id.textViewLetter)

        textView.text = letters.get(i).letter.toString()
        textView.setBackgroundColor(letters.get(i).backgroundColor.rgb) // check here
        textView.setTextColor(letters.get(i).textColor.rgb)

        return view
    }
}