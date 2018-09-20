package com.icaboalo.pos

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_completition.*

class CompletitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completition)
        Handler().postDelayed({ checkStatus() }, 5000)
    }

    fun checkStatus() {
        progress.visibility = View.GONE
        result.visibility = View.VISIBLE
        if (intent.getBooleanExtra("success", false)) {
            image_view.setImageResource(R.drawable.success)
            tv_status.text = "Pago Aprovado"
            tv_amount.text = "Monto: ${intent.getStringExtra("amount")}"
        } else {
            tv_amount.text = ""
            tv_status.text = "Pago Declinado"
            image_view.setImageResource(R.drawable.fail)
            tv_authorization.text = "Fondos insuficientes"
        }
    }
}
