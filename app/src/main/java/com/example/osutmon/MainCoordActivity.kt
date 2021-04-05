package com.example.osutmon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainCoordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coord)

        val logout: Button = findViewById(R.id.logoutco)
        logout.setOnClickListener {
            val change: Intent = Intent(this, LoginActivity::class.java)
            startActivity(change)
            finish()
        }

        val profil: Button = findViewById(R.id.profilco)
        profil.setOnClickListener {
            val change: Intent = Intent(this, ProfileActivity::class.java)
            startActivity(change)
            finish()
        }
        /*val meet: Button = findViewById(R.id.sedinteco)
        meet.setOnClickListener {
            val change: Intent = Intent(this, MeetCoPage::class.java)
            startActivity(change)
            finish()
        }
        val task: Button = findViewById(R.id.tasksco)
        task.setOnClickListener {
            val change: Intent = Intent(this, TaskCoPage::class.java)
            startActivity(change)
            finish()
        }
        val point: Button = findViewById(R.id.pointsco)
        point.setOnClickListener {
            val change: Intent = Intent(this, PointsPage::class.java)
            startActivity(change)
            finish()
        }*/
    }
}
