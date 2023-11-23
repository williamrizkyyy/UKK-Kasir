package com.example.uklkasir

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasir.adapter.TransaksiAdapter
import com.example.uklkasir.userdatabase.CafeDatabase
import com.example.uklkasir.userdatabase.Transaksi

class ManagerTransaksiList : AppCompatActivity() {
    lateinit var recycler: RecyclerView

    lateinit var adapter: TransaksiAdapter

    lateinit var db: CafeDatabase

    private var listTransaksi = arrayListOf<Transaksi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_transaksi_list)

        recycler = findViewById(R.id.recyclerTransaksi)
        db = CafeDatabase.getInstance(applicationContext)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TransaksiAdapter(listTransaksi)
        recycler.adapter = adapter
        adapter.onHolderClick = {
            val moveIntent = Intent(this@ManagerTransaksiList, ManagerDetailTransaksi::class.java)
            moveIntent.putExtra("id_transaksi", it.id_transaksi)
            startActivity(moveIntent)
        }

    }

    override fun onResume() {
        super.onResume()
        getTransaksi()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getTransaksi(){
        listTransaksi.clear()
        listTransaksi.addAll(db.cafeDao().getAllTransaksi())
        adapter.notifyDataSetChanged()
    }
}