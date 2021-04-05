package com.example.osutmon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainAdmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_adm)

        val logout: Button = findViewById(R.id.logoutadm)
        logout.setOnClickListener {
            val change: Intent = Intent(this, LoginActivity::class.java)
            startActivity(change)
            finish()
        }

        val profil: Button = findViewById(R.id.profiladm)
        profil.setOnClickListener {
            val change: Intent = Intent(this, ProfileActivity::class.java)
            startActivity(change)
            finish()
        }

        val addv: Button = findViewById(R.id.addadm)
        addv.setOnClickListener {
            val change: Intent = Intent(this, CreateUserActivity::class.java)
            startActivity(change)
            finish()
        }
        /*val listv: Button = findViewById(R.id.usersadm)
        listv.setOnClickListener {
            val change: Intent = Intent(this, VolListPage::class.java)
            startActivity(change)
            finish()
        }*/

    }
}
