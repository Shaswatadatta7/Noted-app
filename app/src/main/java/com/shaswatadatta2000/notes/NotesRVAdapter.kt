package com.shaswatadatta2000.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: MainActivity): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
     private val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView:TextView =itemView.findViewById<TextView>(R.id.text)
        val deleteButton:ImageView=itemView.findViewById<ImageView>(R.id.DeleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
         val ViewHolder=NoteViewHolder(  LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
            ViewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[ViewHolder.adapterPosition])

        }
        return ViewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text=currentNote.text
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface  INotesRVAdapter{


    fun onItemClicked(note:Note){

    }
}