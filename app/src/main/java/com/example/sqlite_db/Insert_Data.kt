package com.example.sqlite_db

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite_db.Adapter.InsertData_Adapter
import com.example.sqlite_db.ModelData.CustomerMD
import com.example.sqlite_db.databinding.ActivityInsertDataBinding
import com.example.sqlite_db.databinding.CustomerDetailsBinding
import com.example.sqlite_db.databinding.FragmentHomePageBinding
import java.text.SimpleDateFormat
import java.util.*

class Insert_Data : AppCompatActivity() {

    lateinit var details: CustomerDetailsBinding
    lateinit var binding: FragmentHomePageBinding
    lateinit var bind: ActivityInsertDataBinding
    lateinit var dBhelper: DBhelper
    lateinit var list: ArrayList<CustomerMD>
    lateinit var CName: String
    lateinit var PNumber: String
    lateinit var ItemName: String
    lateinit var Quantity: String
    lateinit var Amount: String
    lateinit var DateCalendar: String
    var Status = 0

    @RequiresApi(Build.VERSION_CODES.N)
    var cal = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        details = CustomerDetailsBinding.inflate(layoutInflater)
        bind = ActivityInsertDataBinding.inflate(layoutInflater)
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        initClick()
        DatePick()

        setContentView(bind.root)
        dBhelper = DBhelper(this)

        CName = bind.edtUsername.text.toString()
        PNumber = bind.edtCustomerNumber.text.toString()
        ItemName = bind.edtItemName.text.toString()
        Quantity = bind.edtQua.text.toString()
        Amount = bind.edtAmount.text.toString()
        DateCalendar = bind.txtDateShow.text.toString()
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
        bind.imgCalendar.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@Insert_Data,
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
        bind.txtDateShow.text = sdf.format(cal.time)
    }

    fun initClick() {

        bind.btnCancel.setOnClickListener {
            finish()
        }

        bind.btnDone.setOnClickListener {

            if(bind.RG1.checkedRadioButtonId == R.id.RBTaken1)
            {
                Status = 0
            }
            else{
                Status = 1
            }
            dBhelper.insertData(
                bind.edtUsername.text.toString(),
                bind.edtCustomerNumber.text.toString(),
                bind.edtItemName.text.toString(),
                bind.edtQua.text.toString(),
                bind.edtAmount.text.toString(),
                Status.toString(),
                bind.txtDateShow.text.toString()
            )
            list = dBhelper.readData()

            finish()
        }

        bind.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (1 == requestCode) {
            if (data?.data != null) {
                bind.profileImage.setImageURI(data.data)
            }
        }
    }
}
