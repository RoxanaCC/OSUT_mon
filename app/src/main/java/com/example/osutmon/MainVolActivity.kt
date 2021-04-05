package com.example.osutmon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainVolActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vol_page)

        val logout: Button = findViewById(R.id.logoutvol)
        logout.setOnClickListener {
            val change: Intent = Intent(this, LoginActivity::class.java)
            startActivity(change)
            finish()
        }

        val profil: Button = findViewById(R.id.profilvol)
        profil.setOnClickListener {
            val change: Intent = Intent(this, ProfileActivity::class.java)
            startActivity(change)
            finish()
        }

        /*val meet: Button = findViewById(R.id.sedintevol)
        meet.setOnClickListener {
            val change: Intent = Intent(this, MeetCoPage::class.java)
            startActivity(change)
            finish()
        }
        val task: Button = findViewById(R.id.taskvol)
        task.setOnClickListener {
            val change: Intent = Intent(this, TaskVolPage::class.java)
            startActivity(change)
            finish()
        }*/
    }
}
