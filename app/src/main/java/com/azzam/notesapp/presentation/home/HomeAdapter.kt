package com.azzam.notesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azzam.notesapp.R
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.Priority
import com.azzam.notesapp.databinding.RowItemNotesBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes?.get(position)
        holder.binding.apply {
            tvTitle.text = data?.title
            tvDescription.text = data?.desc
            tvDate.text = data.date

            when (data?.priority) {
                Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(priorityIndicator.context, R.color.pink)
                )
                Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(priorityIndicator.context, R.color.yellow)
                )
                Priority.LOW -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(priorityIndicator.context, R.color.green)
                )
                else -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(priorityIndicator.context, R.color.pink)
                )
            }
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>?) {
        if (data == null) return
        val diffCallback = DiffCallback(listNotes, data)
        val diffUtil = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffUtil.dispatchUpdatesTo(this)
    }
}