/*
    Main activity to handle creation of app with correct elements.
    author: Carter Shavitz
    date: 7/9/2024
 */
package com.example.fetchcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager

import kotlinx.coroutines.launch

private lateinit var viewModel: ViewModel

/**
 * Main activity class to contain creation of the app as well as the recycler view and view model.
 */
class MainActivity : ComponentActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ItemAdapter

    /**
     * On create method provides handling of view model instantiation and usage of recycler view.
     * The adapter is created with the list of items to provide recycler view with items.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Use View model provider to instantiate view model,
        //use the scope to get items off main thread.
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.viewModelScope.launch {
            viewModel.getItems()
        }
        //Retrieve recycler review, listen for the items to change in the view model,
        //then update the adapter to contain the new items.
        recyclerview = findViewById(R.id.recycler_view)
        recyclerview.layoutManager = GridLayoutManager(this, 1)
        viewModel.items.observe(this) { items ->
            adapter = ItemAdapter(items)
            recyclerview.adapter = adapter
        }
    }
}