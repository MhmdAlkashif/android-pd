package com.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var result : TextView
//    lateinit var recyclerView : RecyclerView
//
//    var countryNameList = ArrayList<String>()
//    var countryDetailsList = ArrayList<String>()
//    var countryImageViewList = ArrayList<Int>()


//    lateinit var adapter: CountriesAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        result = findViewById(R.id.textView)

        var userName: String = intent.getStringExtra("userName").toString()
        var age : Int = intent.getIntExtra("age",0).toString().toInt()

        result.text = " Your name is $userName and your age is $age"


//        recyclerView = findViewById(R.id.recyclerView)

//        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//
//        fillCountryList()
//
//        adapter = CountriesAdapter(countryNameList,countryDetailsList, countryImageViewList, this@MainActivity)
//
//        recyclerView.adapter = adapter





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    fun fillCountryList(){
//        countryNameList.add("USA")
//        countryNameList.add("Canada")
//        countryNameList.add("Germany")
//        countryNameList.add("Germany")
//
//        countryDetailsList.add("This is the USA Flag")
//        countryDetailsList.add("This is the Canada Flag")
//        countryDetailsList.add("This is the Germany Flag")
//        countryDetailsList.add("This is the Germany Flag")
//
//        countryImageViewList.add(R.drawable.usa)
//        countryImageViewList.add(R.drawable.canada)
//        countryImageViewList.add(R.drawable.germany)
//        countryImageViewList.add(R.drawable.germany)
//    }
}