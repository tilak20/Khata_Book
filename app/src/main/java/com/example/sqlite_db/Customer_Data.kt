package com.example.sqlite_db

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sqlite_db.databinding.ActivityCustomerDataBinding

class Customer_Data : AppCompatActivity() {

    lateinit var Name: String
    lateinit var Number: String
    lateinit var ItemName: String
    lateinit var Quantity: String
    lateinit var Amount: String
    lateinit var Date: String
    lateinit var Id: String

    lateinit var binding : ActivityCustomerDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomerDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        initClick()
        IntentData()
        SetData()
        setContentView(binding.root)
    }

    private fun initClick() {

        binding.imgMessage.setOnClickListener {
            binding.CrdView13.visibility = View.VISIBLE
        }

        binding.imgSMSCancel.setOnClickListener {
            binding.CrdView13.visibility = View.GONE
        }

        binding.imgSMSSend.setOnClickListener {
            val Message = binding.edtMessage.text.toString()

            val smsManager: SmsManager = SmsManager.getDefault()

            smsManager.sendTextMessage(Number, null, Message, null, null)

            binding.CrdView13.visibility = View.GONE
        }

        binding.imgPhone.setOnClickListener {
            val uri = Uri.parse("tel:$Number")
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = uri
            startActivity(intent)
        }

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    fun SetData() {
        binding.txtUsername.setText(Name)
        binding.txtCustomerNumber.setText(Number)
        binding.txtItemName.setText(ItemName)
        binding.txtQuantity.setText(Quantity)
        binding.txtGiveAmount.setText(Amount)
        binding.txtDateShow.setText(Date)
    }

    fun IntentData() {

        Name = intent.getStringExtra("Name").toString()
        Number = intent.getStringExtra("Number").toString()
        ItemName = intent.getStringExtra("ItemName").toString()
        Quantity = intent.getStringExtra("Quantity").toString()
        Amount = intent.getStringExtra("GiveAmount").toString()
        Date = intent.getStringExtra("GetAmount").toString()
        Date = intent.getStringExtra("Date").toString()
        Id = intent.getStringExtra("Id").toString()
    }
}