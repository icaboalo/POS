package com.icaboalo.pos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_charge.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_charge.setOnClickListener {
            if (et_amount.text.toString().isNotEmpty()) {
                val intent = Intent(this, ChargeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Completa el campo de monto", Toast.LENGTH_LONG).show()
            }
        }
    }


}
