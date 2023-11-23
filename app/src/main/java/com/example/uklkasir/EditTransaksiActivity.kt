package com.example.uklkasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.uklkasir.userdatabase.CafeDatabase

class EditTransaksiActivity : AppCompatActivity() {
    lateinit var inputNamaPelanggan: EditText
    lateinit var nomorMeja: TextView
    lateinit var simpanButton: Button
    lateinit var dibayar: CheckBox

    lateinit var db: CafeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_transaksi)

        inputNamaPelanggan = findViewById(R.id.namaPelanggan)
        nomorMeja = findViewById(R.id.nomorMeja)
        simpanButton = findViewById(R.id.simpan)
        dibayar = findViewById(R.id.dibayar)

        db = CafeDatabase.getInstance(applicationContext)

        var id_transaksi: Int? = null
        id_transaksi = intent.getIntExtra("ID", 0)

        inputNamaPelanggan.setText(db.cafeDao().getTransaksi(id_transaksi).nama_pelanggan)
        nomorMeja.text = db.cafeDao().getMeja(db.cafeDao().getTransaksi(id_transaksi).id_meja).nomor_meja

        simpanButton.setOnClickListener{
            var status = "Belum Dibayar"
            if(dibayar.isChecked){
                status = "Dibayar"
            }
            if(inputNamaPelanggan.text.toString().isNotEmpty()){
                db.cafeDao().updateTransaksi(
                    inputNamaPelanggan.text.toString(),
                    status,
                    id_transaksi
                )
                if(dibayar.isChecked){
                    var meja = db.cafeDao().getMeja(db.cafeDao().getTransaksi(id_transaksi).id_meja)
                    db.cafeDao().updateMeja(meja.nomor_meja, meja.id_meja!!, false)
                }

                finish()
            }
        }
    }
}