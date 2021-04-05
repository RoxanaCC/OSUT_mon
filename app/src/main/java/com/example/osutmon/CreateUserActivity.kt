package com.example.osutmon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.graphics.Color
import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*


class CreateUserActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val back: Button = findViewById(R.id.backbadd)
        back.setOnClickListener {
            val change: Intent = Intent(this, MainAdmActivity::class.java)
            startActivity(change)
            finish()
        }

        imageadd.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivityForResult(intent, 0)

        }

        confbutadn.setOnClickListener {
            runRegister()
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){

            selectedPhotoUri = data.data
            val selPhotoUri: Uri = data.data!!

            val source = ImageDecoder.createSource(contentResolver, selPhotoUri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            circleimagevew.setImageBitmap(bitmap)

            imageadd.alpha = 0f

            /*if(data.data != null){
                val selPhotoUri: Uri = data.data!!
                val source = ImageDecoder.createSource(contentResolver, selPhotoUri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                val drawable: Drawable = BitmapDrawable(resources, bitmap)
                imageadd.setBackground(drawable)*/
            }

    }


    private fun runRegister(){

        val email = editemailadm.text.toString()
        val pass = editpassadm.text.toString()

        if(email.isEmpty() || pass.isEmpty()){
            textViewaddadm.setTextColor(Color.RED)
            textViewaddadm.setText("Introduceți text in Email și Password")
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if(!it.isSuccessful) {
                    textViewaddadm.setTextColor(Color.GREEN)
                    textViewaddadm.setText("Cont înregistrat cu succes")
                    uploadImage()

                }
                else return@addOnCompleteListener
            }
            .addOnFailureListener {
                textViewaddadm.setTextColor(Color.RED)
                textViewaddadm.setText("Eroare înregistrare cont")
            }
    }


    private fun uploadImage(){
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    saveNewUser(it.toString())
                }
            }
            .addOnFailureListener{
                textViewaddadm.setTextColor(Color.RED)
                textViewaddadm.setText("Eroare înregistrare cont")
            }

    }

    private fun saveNewUser(imageUrl: String){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid.toString(), editnameadm.toString(), editfacadm.toString(),
            editanadm.toString().toInt(), "Recrut", 0, "Admin", imageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                textViewaddadm.setTextColor(Color.GREEN)
                textViewaddadm.setText("Cont înregistrat cu succes")
            }
            .addOnFailureListener{
                textViewaddadm.setTextColor(Color.RED)
                textViewaddadm.setText("Eroare înregistrare cont")
            }
    }

}

class User(val uid:String, val nume: String, val facultate: String,
           val an: Int, val statut: String, val points: Int,
           val functie: String, val imageUrl: String)

