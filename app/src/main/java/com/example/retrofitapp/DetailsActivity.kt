package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.retrofitapp.repository.RetrofitInstance

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra("id", 1)
        val fName: TextView = findViewById(R.id.fNameDetails)
        val lName: TextView = findViewById(R.id.lNameDetails)
        val email: TextView = findViewById(R.id.emailDetails)
        val idTV: TextView = findViewById(R.id.idDetails)
        val img: ImageView = findViewById(R.id.imageDetails)

        lifecycleScope.launchWhenCreated {
            val response = RetrofitInstance.retrofit.getUser(id)
            if (response.isSuccessful && response.body() != null){
                fName.text = response.body()!!.data.firstName
                lName.text = response.body()!!.data.lastName
                email.text = response.body()!!.data.email
                idTV.text = response.body()!!.data.id.toString()
                Glide.with(this@DetailsActivity).load(response.body()!!.data.img).into(img)
            }
        }

    }
}