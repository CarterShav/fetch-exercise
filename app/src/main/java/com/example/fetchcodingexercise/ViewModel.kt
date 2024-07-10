/*
    View Model class to handle retrieval of information from JSON file and compare items.
    author: Carter Shavitz
    date: 7/9/2024
 */
package com.example.fetchcodingexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import androidx.lifecycle.viewModelScope
import com.example.fetchcodingexercise.Item.Header
import com.example.fetchcodingexercise.Item.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

const val LOGTAG = "VIEWMODEL"

/**
 * View model class to handle things off the main thread and provide information to the main activity.
 */
class ViewModel : ViewModel() {
    private val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    private val _items: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    /**
     * retrieves information from the url provided to get the JSON information,
     * then parse the information and set _items to trigger the observe in main activity.
     */
    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = URL(url).readText()
            Log.d(LOGTAG, response)
            val jsonData = parseItemsFromString(response)
            _items.postValue(jsonData)
            Log.d(LOGTAG, jsonData[0].toString())
        }
    }

    /**
     * Class to compare items easily, first with the list id being compared, then the name.
     */
    class ItemComparator : Comparator<Item> {
        override fun compare(item1: Item, item2: Item): Int {
            if (item1 is Header || item2 is Header) {
                return 0
            } else {
                val content1 = item1 as Content
                val content2 = item2 as Content
                return if (content1.listId != content2.listId) {
                    content1.listId - content2.listId
                } else {
                    content1.name.compareTo(content2.name)
                }
            }
        }
    }

    /**
     * Sort the items with the Item Comparator class.
     */
    fun sortItems(list: List<Item>) : List<Item> {
        return list.sortedWith(ItemComparator())
    }

    /**
     * Method to retrieve information from the JSON file, gets the id, list id, and name.
     * Sorts the items after all are read in.
     */
    fun parseItemsFromString(json: String): List<Item> {
        val itemList = mutableListOf<Item>()
        itemList.add(Header("ID", "List ID", "Name"))
        try {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getInt("id")
                val listId = jsonObject.getInt("listId")
                val name = jsonObject.getString("name")
                if (name.toString() !== "null" && name.toString().isNotEmpty()) {
                    itemList.add(Content(id, listId, name))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val sortedList: List<Item> = sortItems(itemList)
        return sortedList
    }

}
