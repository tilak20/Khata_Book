package com.example.sqlite_db.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite_db.Adapter.InsertData_Adapter
import com.example.sqlite_db.DBhelper
import com.example.sqlite_db.Insert_Data
import com.example.sqlite_db.ModelData.CustomerMD
import com.example.sqlite_db.R
import com.example.sqlite_db.databinding.ActivityInsertDataBinding
import com.example.sqlite_db.databinding.ActivityMainBinding
import com.example.sqlite_db.databinding.CustomerDetailsBinding
import com.example.sqlite_db.databinding.FragmentHomePageBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.collections.ArrayList

class Homepage_Fragment : Fragment() {

    lateinit var CDetails: CustomerDetailsBinding
    lateinit var insertData: ActivityInsertDataBinding
    lateinit var binding: FragmentHomePageBinding
    lateinit var bind: ActivityMainBinding
    lateinit var dBhelper: DBhelper
    lateinit var insertDataAdapter: InsertData_Adapter
    var list = arrayListOf<CustomerMD>()
    lateinit var GetAmount: String
    lateinit var GiveAmount: String
    var l3 = arrayListOf<CustomerMD>()

    var SumOfGiveMoney = 0
    var SumOfGetMoney = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        CDetails = CustomerDetailsBinding.inflate(layoutInflater)
        bind = ActivityMainBinding.inflate(layoutInflater)
        binding = FragmentHomePageBinding.bind(inflater.inflate(R.layout.fragment_home_page, container, false))
        insertData = ActivityInsertDataBinding.inflate(layoutInflater)
        dBhelper = DBhelper(activity)
        initClick()
        bottomDialog()
        dBhelper = DBhelper(this@Homepage_Fragment.activity)

        GiveAmount = CDetails.txtShowGive.text.toString()
        GetAmount = CDetails.txtShowGet.text.toString()

        CustomerRv(list)

        return binding.root
    }

    fun bottomDialog() {
        binding.imgFilter.setOnClickListener {
            var dialog = BottomSheetDialog(requireActivity())
            dialog.setContentView(R.layout.filter_list)

            val CrdView14 = dialog.findViewById<CardView>(R.id.CrdView14)
            val CrdView16 = dialog.findViewById<CardView>(R.id.CrdView16)
            val CrdView17 = dialog.findViewById<CardView>(R.id.CrdView17)

            CrdView14?.setOnClickListener {
                list = dBhelper.readData()
                CustomerRv(list)
                dialog.dismiss()
            }

            CrdView16?.setOnClickListener {
                list = dBhelper.ExpenseData(0)
                CustomerRv(list)
                dialog.dismiss()
            }

            CrdView17?.setOnClickListener {
                list = dBhelper.IncomeData(1)
                CustomerRv(list)
                dialog.dismiss()
            }

            dialog.show()
            }
        }

        fun CustomerRv(list: ArrayList<CustomerMD>) {

            insertDataAdapter = InsertData_Adapter(activity, list, activity, this,l3)
            val lm = LinearLayoutManager(activity)
            binding.RVDetails.layoutManager = lm
            binding.RVDetails.adapter = insertDataAdapter
        }

        fun initClick() {
            binding.btnAddPerson.setOnClickListener {
                val intent = Intent(activity, Insert_Data::class.java)
                startActivity(intent)
            }

            binding.SearchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    l3.clear()

                    for (i in list) {
                        if (i.Customer_Name.lowercase().contains(newText?.lowercase(Locale.getDefault())!!)) {
                            l3.add(i)
                        }
                    }
                    insertDataAdapter.SearchData(l3)
                    return false
                }
            })
        }

        override fun onStart() {
            super.onStart()
            list = dBhelper.readData()
            CustomerRv(list)
            sum()
        }

        fun sum() {
            SumOfGiveMoney = 0
            SumOfGetMoney = 0

            for (i in list) {
                if (i.Status == "0") {
                    SumOfGiveMoney += i.Amount.toInt()
                } else {
                    SumOfGetMoney += i.Amount.toInt()
                }
            }
                binding.txtGiveMoney.setText("$SumOfGiveMoney").toString()
                binding.txtGetMoney.setText("$SumOfGetMoney").toString()
        }
    }
