package com.example.sqlite_db

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.sqlite_db.Adapter.InsertData_Adapter
import com.example.sqlite_db.ModelData.CustomerMD
import com.example.sqlite_db.databinding.ActivityEditDataBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Edit_Data : AppCompatActivity() {

    lateinit var binding: ActivityEditDataBinding
    lateinit var list: ArrayList<CustomerMD>
    lateinit var dBhelper: DBhelper
    lateinit var Name: String
    lateinit var Number: String
    lateinit var ItemName: String
    lateinit var Quantity: String
    lateinit var Amount: String
    lateinit var Date: String
    lateinit var Id: String
    var Status = 0


    @RequiresApi(Build.VERSION_CODES.N)
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dBhelper = DBhelper(this)

        DatePick()
        initClick()
        getIntentData()

        binding.edtEditUsername.setText(Name)
        binding.edtEditCustomerNumber.setText(Number)
        binding.edtEditItemName.setText(ItemName)
        binding.edtEditQua.setText(Quantity)
        binding.edtEditAmount.setText(Amount)
        binding.txtEditDateShow.setText(Date)
    }

    fun initClick() {

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnUpdate.setOnClickListener {

            if(binding.RG1.checkedRadioButtonId == R.id.RBTaken)
            {
                Status = 0
            }
            else{
                Status = 1
            }

            dBhelper.UpdateData(
                Id,
                binding.edtEditUsername.text.toString(),
                binding.edtEditCustomerNumber.text.toString(),
                binding.edtEditItemName.text.toString(),
                binding.edtEditQua.text.toString(),
                binding.edtEditAmount.text.toString(),
                Status.toString(),
                binding.txtEditDateShow.text.toString()
            )
            list = dBhelper.readData()
            finish()
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (1 == requestCode) {
            if (data?.data != null) {
                binding.profileImage.setImageURI(data.data)
            }
        }
    }

    fun getIntentData() {
        Id = intent.getStringExtra("Id").toString()
        Name = intent.getStringExtra("Name").toString()
        Number = intent.getStringExtra("Number").toString()
        ItemName = intent.getStringExtra("ItemName").toString()
        Quantity = intent.getStringExtra("Quantity").toString()
        Amount = intent.getStringExtra("Amount").toString()
        Date = intent.getStringExtra("Date").toString()
    }

    fun DatePick() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        binding.imgEditCalendar.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@Edit_Data,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateInView() {
        val myFormat = "dd/MM/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.txtEditDateShow.text = sdf.format(cal.time)
    }
}