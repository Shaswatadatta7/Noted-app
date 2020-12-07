package com.shaswatadatta2000.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() ,INotesRVAdapter{

    lateinit var viewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter =adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer{list->
            list?.let{
                adapter.updateList(it)

            }


        })


    }
    override fun onItemClicked(note: Note){
        viewModel.deleteNode(note)
    }

    fun submitData(view: View) {
        val noteText =inputData.text.toString()

        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(Note(noteText)))
        }
    }
}