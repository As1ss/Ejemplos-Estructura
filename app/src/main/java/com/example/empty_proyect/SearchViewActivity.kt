package com.example.empty_proyect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

class SearchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)



        val users = arrayOf("Alberto","Alvaro","Ana","Amparo","Bartolo","Bernardo","Carla","Carlos","Carolina")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,users)

        val searchView = findViewById<SearchView>(R.id.searchView)
        var listView = findViewById<ListView>(R.id.lVUsers)

        listView.adapter = arrayAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (users.contains(query))
                    arrayAdapter.filter.filter(query)

                return false

            }

            override fun onQueryTextChange(query: String?): Boolean {
                arrayAdapter.filter.filter(query)
                return false
            }

        })
    }


}