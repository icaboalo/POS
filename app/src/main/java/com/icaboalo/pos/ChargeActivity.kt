package com.icaboalo.pos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_charge.*

class ChargeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charge)

        et_scan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if (p0.toString().isNotEmpty()) {
                    Toast.makeText(this@ChargeActivity, p0.toString(), Toast.LENGTH_LONG).show()
                    checkFirebase(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    fun checkFirebase(text: String) {
        val database = FirebaseDatabase.getInstance()
        database.getReference("$text/server").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val success = snapshot.getValue(Boolean::class.java)!!
                updateFirebase(text, success)
            }
        })
    }

    fun updateFirebase(text: String, completed: Boolean) {
        val database = FirebaseDatabase.getInstance()
        database.getReference("$text/client").setValue(completed)

        val intent = Intent(this, CompletitionActivity::class.java)
        intent.putExtra("success", completed)
        intent.putExtra("amount", this.intent.getStringExtra("amount"))
        startActivity(intent)
        finish()
    }
}
