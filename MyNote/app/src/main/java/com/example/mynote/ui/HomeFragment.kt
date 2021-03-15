package com.example.mynote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynote.MVVM.MyViewModel
import com.example.mynote.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)


      val viewModel= ViewModelProviders.of(this).get(MyViewModel::class.java)


        launch {
            context?.let {
               viewModel.getTask.observe(viewLifecycleOwner, Observer {
                   recyclerView.adapter=NoteAdapter(it)

               })
//
////
//
//                val notes =NoteDatabase.getDatabase(it).getNoteDao().getAllNotes()
//
//                recyclerView.adapter=NoteAdapter(notes)
            }
        }


        btnAdd.setOnClickListener {
            val action=HomeFragmentDirections.ActionAddNotes()
            Navigation.findNavController(it).navigate(action)
        }
    }

}