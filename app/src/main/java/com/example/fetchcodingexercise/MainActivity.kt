package com.example.fetchcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

import com.example.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme
import java.io.File
import java.net.URL
import java.util.logging.Logger

data class Item(val id: Int, val listId: Int, val name: String)

private lateinit var viewModel: ViewModel


class MainActivity : ComponentActivity() {

    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        Log.d("Main", "initial")
        recyclerview = findViewById(R.id.recyclerview)
        Log.d("Main", viewModel.getItems())
        recyclerview.
    }

}