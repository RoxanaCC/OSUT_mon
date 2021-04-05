package com.example.osutmon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val back: Button = findViewById(R.id.backbprof)
        back.setOnClickListener {
            val change: Intent = Intent(this, MainAdmActivity::class.java)
            startActivity(change)
            finish()
        }
        val bedit: Button = findViewById(R.id.editprf)
        back.setOnClickListener {
            val change: Intent = Intent(this, EditProfileActivity::class.java)
            startActivity(change)
            finish()
        }
    }
}
