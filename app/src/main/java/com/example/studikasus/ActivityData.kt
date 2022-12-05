package com.example.studikasus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studikasus.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ActivityData : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("kredits").get().addOnSuccessListener{
            binding.edtNominal.setText(it.documents.last().data?.get("Nominal").toString())
            binding.edtTenor.setText(it.documents.last().data?.get("Tenor").toString())
            binding.edtAngsuran.setText(it.documents.last().data?.get("Angsuran").toString())
        }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
            }
            binding.btnDelete.setOnClickListener{
            delete()
                val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        fun delete(){
            db.collection("kredits").document("AtY6R0PgVWM7F0vEFUlt")
                .delete()
                .addOnSuccessListener { Log.d(TAG, "documentsSnapshot") }
                .addOnFailureListener{e -> Log.w(TAG, "Error Delete Documents",e)}
        }
    }

}