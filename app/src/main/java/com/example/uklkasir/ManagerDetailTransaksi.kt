package com.example.uklkasir

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasir.adapter.DetailAdapter
import com.example.uklkasir.userdatabase.CafeDatabase
import com.example.uklkasir.userdatabase.DetailTransaksi

class ManagerDetailTransaksi : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var total: TextView

    lateinit var adapter: DetailAdapter
    private var listDetail = arrayListOf<DetailTransaksi>()
    private var id_transaksi = 0
    private var totalHarga = 0

    lateinit var db: CafeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_detail_transaksi)

        recycler = findViewById(R.id.recyclerDetail)
        total = findViewById(R.id.total)

        id_transaksi = intent.getIntExtra("id_transaksi", 0)

        db = CafeDatabase.getInstance(applicationContext)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = DetailAdapter(listDetail)
        recycler.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        getDetail()
        totalHarga = 0
        for (i in listDetail){
            totalHarga += i.harga
        }
        total.text = "Rp." + totalHarga
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getDetail(){
        listDetail.clear()
        listDetail.addAll(db.cafeDao().getDetailTransaksi(id_transaksi))
        adapter.notifyDataSetChanged()
    }
}