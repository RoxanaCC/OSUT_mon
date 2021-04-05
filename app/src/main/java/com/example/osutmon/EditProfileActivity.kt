package com.example.osutmon

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_edit_profile_activiti.*
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_activiti)


        confbuteprf.setOnClickListener {
            if (selectedPhotoUri == null) return@setOnClickListener
            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
            ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    textVieweditprf.setTextColor(Color.GREEN)
                    textVieweditprf.setText("Editare profil cu succes")
                }
                .addOnFailureListener{
                    textVieweditprf.setTextColor(Color.RED)
                    textVieweditprf.setText("Editare profil a eșuat")
                }
            ref.downloadUrl.addOnSuccessListener {

                //FirebaseDatabase.getInstance().getReference("/users/$uid")
            }
        }


        backbadd.setOnClickListener {
            val change: Intent = Intent(this, ProfileActivity::class.java)
            startActivity(change)
            finish()
        }

        profiledirimage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            profiledirimage.setBackgroundDrawable(BitmapDrawable(bitmap))
        }
    }
}
