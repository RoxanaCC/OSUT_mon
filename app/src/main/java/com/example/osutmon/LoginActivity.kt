package com.example.osutmon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val myOsut: ImageView = findViewById(R.id.imageView6)
        myOsut.setImageResource(R.drawable.osutalb)

        val email: EditText = findViewById(R.id.userlog)
        val pass: EditText = findViewById(R.id.passlog)
        val text: TextView = findViewById(R.id.outlog)


        login.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(), pass.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val intent = Intent(this, MainAdmActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        text.setText("Email sau parolă greșite")
                    }
                }
        }
        regfirst.setOnClickListener{
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

    }
}
