package com.example.sqlite_db.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sqlite_db.Fragment.Homepage_Fragment
import com.example.sqlite_db.Fragment.Item_Fragment
import com.example.sqlite_db.Fragment.Money_Fragment
import com.example.sqlite_db.Fragment.Profile_Fragment

class Bottom_Nav_Adapter(sfm: FragmentManager) :FragmentPagerAdapter(sfm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        val f1 = when(position)
        {
            0 -> Homepage_Fragment()
            1 -> Money_Fragment()
            2 -> Item_Fragment()
            3 -> Profile_Fragment()
            else -> Homepage_Fragment()
        }
        return f1
    }
}