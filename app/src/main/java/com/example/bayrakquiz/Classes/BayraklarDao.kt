package com.example.bayrakquiz.Classes

import android.annotation.SuppressLint

class BayraklarDao {

    @SuppressLint("Range")
    fun random5flag(db:VeriTabaniYardimcisi):ArrayList<Bayraklar>{

        val bayrakList = ArrayList<Bayraklar>()

        val cursor = db.writableDatabase.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (cursor.moveToNext()){
            val bayrak = Bayraklar(
                cursor.getInt(cursor.getColumnIndex("bayrak_id")),
                cursor.getString(cursor.getColumnIndex("bayrak_ad")),
                cursor.getString(cursor.getColumnIndex("bayrak_resim"))
            )
            bayrakList.add(bayrak)
        }
        return bayrakList
    }


    @SuppressLint("Range")
    fun random3WrongFlag(db: VeriTabaniYardimcisi,bayrak_id:Int):ArrayList<Bayraklar>{
        val yanlisBayraklar = ArrayList<Bayraklar>()

        val cursor = db.writableDatabase.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id  ORDER BY RANDOM() LIMIT 3",null)

        while(cursor.moveToNext()){
            val yanlisBayrak = Bayraklar(cursor.getInt(cursor.getColumnIndex("bayrak_id")),
                cursor.getString(cursor.getColumnIndex("bayrak_ad")),
                cursor.getString(cursor.getColumnIndex("bayrak_resim")))
            yanlisBayraklar.add(yanlisBayrak)
        }

        return yanlisBayraklar
    }


}