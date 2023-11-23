package com.example.uklkasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasir.adapter.MejaAdapter
import com.example.uklkasir.userdatabase.CafeDatabase
import com.example.uklkasir.userdatabase.Meja

class ManagerMeja : AppCompatActivity() {
    lateinit var recycler: RecyclerView

    lateinit var adapter: MejaAdapter
    private var listMeja = mutableListOf<Meja>()

    lateinit var db: CafeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_meja)

        recycler = findViewById(R.id.recyclerMeja)
        db = CafeDatabase.getInstance(applicationContext)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = MejaAdapter(listMeja)
        recycler.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        getMeja()
    }

    fun getMeja(){
        listMeja.clear()
        listMeja.addAll(db.cafeDao().getAllMeja())
        adapter.notifyDataSetChanged()
    }
}