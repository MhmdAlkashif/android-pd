package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SharedMemory
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var usename : EditText
    lateinit var userMessage : EditText
    lateinit var counter : Button
    lateinit var remember : CheckBox

    var name:String? = null
    var message:String? =null
    var isChecked:Boolean? = null
    
    lateinit var sharedPreferences: SharedPreferences

    var count : Int = 0;

    private val baseURL : String = "https://jsonplaceholder.typicode.com"

    lateinit var mainBinding : ActivityMainBinding

    var postsList = ArrayList<Posts>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        enableEdgeToEdge()
        setContentView(view)


        usename = findViewById(R.id.usernameId)
        userMessage = findViewById(R.id.userMessageId)
        counter = findViewById(R.id.counterId)
        remember = findViewById(R.id.rememberId)

        counter.setOnClickListener{

            count++
            counter.text = "" + count

            showPosts()

        }


        Log.i("Message", "Main Activity onCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showPosts() {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call : Call<List<Posts>> = retrofitAPI.getAllPosts()

        call.enqueue(object : Callback<List<Posts>>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
              if(!response.isSuccessful){
                  mainBinding.userId.text = "error"
                  mainBinding.textViewId.text = "error"
                  mainBinding.textViewTitle.text = "error"
                  mainBinding.textViewBody.text = "error"

              }

                postsList = response.body() as ArrayList<Posts>

                mainBinding.userId.text = postsList[0].userId.toString()
                mainBinding.textViewId.text = postsList[0].id .toString()
                mainBinding.textViewTitle.text = postsList[0].title
                mainBinding.textViewBody.text = postsList[0].subtitle

            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
               Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })

    }


    override fun onPause() {
        super.onPause()
        saveData()

    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }


    private fun saveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = usename.text.toString()
        message = userMessage.text.toString()
        isChecked = remember.isChecked

        val editor  = sharedPreferences.edit()
        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key remember", isChecked!!)

        editor.apply()

        Toast.makeText(applicationContext, "Your data is saved!",Toast.LENGTH_LONG).show()
    }

    @SuppressLint("SetTextI18n")
    private fun retrieveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name",null)
        message = sharedPreferences.getString("key message", null)
        count = sharedPreferences.getInt("key count",0)
        isChecked = sharedPreferences.getBoolean("key remember",false)

        usename.setText(name)
        userMessage.setText(message)
        counter.setText("" + count)
        remember.isChecked = isChecked!!




    }

}