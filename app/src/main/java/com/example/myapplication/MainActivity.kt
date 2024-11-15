package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResults = findViewById(R.id.textViewResults)
        val buttonGetUsers: Button = findViewById(R.id.buttonGetUsers)
        val buttonCreateUser: Button = findViewById(R.id.buttonCreateUser)

        buttonGetUsers.setOnClickListener {
            getUsers()
        }

        buttonCreateUser.setOnClickListener {
            createUser()
        }
    }

    private fun getUsers() {
        ApiClient.apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()?.joinToString("\n") { "${it.name}, ${it.age}" }
                    textViewResults.text = users ?: "Нет пользователей"
                } else {
                    textViewResults.text = "Ошибка: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                textViewResults.text = "Ошибка: ${t.message}"
            }
        })
    }

    private fun createUser() {
        val newUser = User(id = 0, name = "Niga", age = 89) 
        ApiClient.apiService.createUser(newUser).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    textViewResults.text = "Пользователь создан: ${response.body()?.name}"
                } else {
                    textViewResults.text = "Ошибка: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                textViewResults.text = "Ошибка: ${t.message}"
            }
        })
    }
}
