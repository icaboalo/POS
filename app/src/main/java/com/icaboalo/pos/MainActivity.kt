package com.icaboalo.pos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_charge.setOnClickListener {
            if (tv_amount.text.toString() != "$0.00") {
                val intent = Intent(this, ChargeActivity::class.java)
                intent.putExtra("amount", tv_amount.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Completa el campo de monto", Toast.LENGTH_LONG).show()
            }
        }

        numPad.setOnTextChangeListner { text, digits_remaining ->
            val cleanString = text.replace("[$,.]", "")
            var parsed = 0.0
            if (cleanString.isNotEmpty()) {
                parsed = cleanString.toDouble()
            }
            val formatted = NumberFormat.getCurrencyInstance().format((parsed / 100))
            tv_amount.text = formatted
        }
    }


}
