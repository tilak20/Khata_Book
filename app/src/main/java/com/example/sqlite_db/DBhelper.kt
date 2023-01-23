package com.example.sqlite_db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite_db.ModelData.CustomerMD

class DBhelper(context: Context?) : SQLiteOpenHelper(context, "CustomerDetails.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE shop (Id INTEGER PRIMARY KEY AUTOINCREMENT , Customer_Name TEXT ,Mobile TEXT ,Item_Name TEXT,Quantity TEXT,Amount TEXT,Status TEXT,Date TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(
        CN: String,
        CM: String,
        IN: String,
        IQ: String,
        AM: String,
        ST: String,
        DATE: String
    ) {
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put("Customer_Name", CN)
        contentValues.put("Mobile", CM)
        contentValues.put("Item_Name", IN)
        contentValues.put("Quantity", IQ)
        contentValues.put("Amount", AM)
        contentValues.put("Status", ST)
        contentValues.put("Date", DATE)

        db.insert("shop", null, contentValues)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<CustomerMD> {
        val dataList = arrayListOf<CustomerMD>()
        val db = readableDatabase
        val query = "SELECT * FROM shop"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val Id = cursor.getString(cursor.getColumnIndex("Id")).toString()
                val Customer_Name = cursor.getString(cursor.getColumnIndex("Customer_Name")).toString()
                val Mobile = cursor.getString(cursor.getColumnIndex("Mobile")).toString()
                val Item_Name = cursor.getString(cursor.getColumnIndex("Item_Name")).toString()
                val Quantity = cursor.getString(cursor.getColumnIndex("Quantity")).toString()
                val Amount = cursor.getString(cursor.getColumnIndex("Amount")).toString()
                val Status = cursor.getString(cursor.getColumnIndex("Status")).toString()
                val Date = cursor.getString(cursor.getColumnIndex("Date")).toString()

                val d1 = CustomerMD(
                    Id,
                    Customer_Name,
                    Mobile,
                    Item_Name,
                    Quantity,
                    Amount,
                    Status,
                    Date
                )
                dataList.add(d1)

            } while (cursor.moveToNext())
        }
        return dataList
    }


    @SuppressLint("Range")
    fun IncomeData(i: Int): ArrayList<CustomerMD> {
        val dataList = arrayListOf<CustomerMD>()
        val db = readableDatabase
        val query = "SELECT * FROM shop WHERE Status = 1"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val Id = cursor.getString(cursor.getColumnIndex("Id")).toString()
                val Customer_Name = cursor.getString(cursor.getColumnIndex("Customer_Name")).toString()
                val Mobile = cursor.getString(cursor.getColumnIndex("Mobile")).toString()
                val Item_Name = cursor.getString(cursor.getColumnIndex("Item_Name")).toString()
                val Quantity = cursor.getString(cursor.getColumnIndex("Quantity")).toString()
                val Amount = cursor.getString(cursor.getColumnIndex("Amount")).toString()
                val Status = cursor.getString(cursor.getColumnIndex("Status")).toString()
                val Date = cursor.getString(cursor.getColumnIndex("Date")).toString()

                val d1 = CustomerMD(Id, Customer_Name, Mobile, Item_Name, Quantity, Amount, Status, Date)
                dataList.add(d1)

            } while (cursor.moveToNext())
        }
        return dataList
    }


    @SuppressLint("Range")
    fun ExpenseData(i: Int): ArrayList<CustomerMD> {
        val dataList = arrayListOf<CustomerMD>()
        val db = readableDatabase
        val query = "SELECT * FROM shop WHERE Status == 0"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val Id = cursor.getString(cursor.getColumnIndex("Id")).toString()
                val Customer_Name = cursor.getString(cursor.getColumnIndex("Customer_Name")).toString()
                val Mobile = cursor.getString(cursor.getColumnIndex("Mobile")).toString()
                val Item_Name = cursor.getString(cursor.getColumnIndex("Item_Name")).toString()
                val Quantity = cursor.getString(cursor.getColumnIndex("Quantity")).toString()
                val Amount = cursor.getString(cursor.getColumnIndex("Amount")).toString()
                val Status = cursor.getString(cursor.getColumnIndex("Status")).toString()
                val Date = cursor.getString(cursor.getColumnIndex("Date")).toString()

                val d1 = CustomerMD(Id, Customer_Name, Mobile, Item_Name, Quantity, Amount, Status, Date)
                dataList.add(d1)

            } while (cursor.moveToNext())
        }
        return dataList
    }

    fun deleteData(Id: String) {
        val db = writableDatabase
        db.delete("shop", "Id = $Id", null)
    }

    fun UpdateData(ID: String,CN: String, CM: String, IN: String, IQ: String,AM: String, ST: String, DATE: String,) {
        val db  = writableDatabase

        val contentValues = ContentValues()
        contentValues.put("Id",ID)
        contentValues.put("Customer_Name", CN)
        contentValues.put("Mobile", CM)
        contentValues.put("Item_Name", IN)
        contentValues.put("Quantity", IQ)
        contentValues.put("Amount", AM)
        contentValues.put("Status", ST)
        contentValues.put("Date", DATE)

        db.update("shop",contentValues,"id = $ID",null)
    }
}