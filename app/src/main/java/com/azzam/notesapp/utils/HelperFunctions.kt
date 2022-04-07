package com.azzam.notesapp.utils

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.azzam.notesapp.R
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.Priority

object HelperFunctions {
    fun setPriorityColor(context: Context, cardView: CardView) : AdapterView.OnItemSelectedListener {

        val listener = object : AdapterView.OnItemSelectedListener {

//            val arrPriority = context.resources.getIntArray(R.array.priorities)

            val pink = ContextCompat.getColor(context, R.color.pink)
            val yellow = ContextCompat.getColor(context, R.color.yellow)
            val green = ContextCompat.getColor(context, R.color.green)

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> cardView.setCardBackgroundColor(pink)
                    1 -> cardView.setCardBackgroundColor(yellow)
                    2 -> cardView.setCardBackgroundColor(green)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return listener
    }

    fun parseToPriority(context: Context? ,priority: String): Priority {
        val arrPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            arrPriority?.get(0) -> Priority.HIGH
            arrPriority?.get(1) -> Priority.MEDIUM
            arrPriority?.get(2) -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    val emptyDataBase = MutableLiveData<Boolean>()

    fun checkDataIsEmpty(data: List<Notes>) {
        emptyDataBase.value = data.isEmpty()
    }

}