package com.example.sqlite_db.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_db.Customer_Data
import com.example.sqlite_db.DBhelper
import com.example.sqlite_db.Edit_Data
import com.example.sqlite_db.Fragment.Homepage_Fragment
import com.example.sqlite_db.ModelData.CustomerMD
import com.example.sqlite_db.R

class InsertData_Adapter(
    val insertData: FragmentActivity?,
    var list: ArrayList<CustomerMD>,
    val activity: Context?,
    val hf: Homepage_Fragment,
    l3: ArrayList<CustomerMD>,
) : RecyclerView.Adapter<InsertData_Adapter.ViewData>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val view = LayoutInflater.from(insertData).inflate(R.layout.customer_details, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        val dBhelper = DBhelper(insertData)

        holder.txtShowName.setText(list[position].Customer_Name).toString()
        holder.txtShowItmName.setText(list[position].Item_Name).toString()
        holder.txtShowQua.setText(list[position].Quantity).toString()
        holder.txtShowDate.setText(list[position].Date).toString()

        if (list[position].Status == "0") {
            holder.txtShowGet.text = ""
            holder.txtShowGive.text = list[position].Amount
        } else if (list[position].Status == "1") {
            holder.txtShowGive.text = ""
            holder.txtShowGet.text = list[position].Amount
        }



        holder.txtViewDetails.setOnClickListener {
            val intent = Intent(activity, Customer_Data::class.java)
            intent.putExtra("Id", list[position].Id)
            intent.putExtra("Name", list[position].Customer_Name)
            intent.putExtra("Number", list[position].Mobile)
            intent.putExtra("ItemName", list[position].Item_Name)
            intent.putExtra("Quantity", list[position].Quantity)
            intent.putExtra("GiveAmount", list[position].Amount)
            intent.putExtra("GetAmount", list[position].Amount)
            intent.putExtra("Date", list[position].Date)
            activity?.startActivity(intent)
        }

        holder.crdEdit.setOnClickListener {
            val intent = Intent(activity, Edit_Data::class.java)
            intent.putExtra("Id", list[position].Id)
            intent.putExtra("Name", list[position].Customer_Name)
            intent.putExtra("Number", list[position].Mobile)
            intent.putExtra("ItemName", list[position].Item_Name)
            intent.putExtra("Quantity", list[position].Quantity)
            intent.putExtra("Amount", list[position].Amount)
            intent.putExtra("GetAmount", list[position].Amount)
            intent.putExtra("Date", list[position].Date)
            activity?.startActivity(intent)
        }

        holder.crdDelete.setOnClickListener {

            dBhelper.deleteData(list[position].Id)
            list.removeAt(position)
            hf.sum()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtShowName = itemView.findViewById<TextView>(R.id.txtShowName)
        val txtShowItmName = itemView.findViewById<TextView>(R.id.txtShowItmName)
        val txtShowQua = itemView.findViewById<TextView>(R.id.txtShowQua)
        val txtShowGive = itemView.findViewById<TextView>(R.id.txtShowGive)
        val txtShowGet = itemView.findViewById<TextView>(R.id.txtShowGet)
        val txtShowDate = itemView.findViewById<TextView>(R.id.txtShowDate)
        val crdDelete = itemView.findViewById<CardView>(R.id.crdDelete)
        val crdEdit = itemView.findViewById<CardView>(R.id.crdEdit)
        val txtViewDetails = itemView.findViewById<TextView>(R.id.txtViewDetails)
    }
    fun SearchData(l3: ArrayList<CustomerMD>)
    {
        list= l3
        notifyDataSetChanged()
    }
}