package com.example.uklkasir.userdatabase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_menu") var id_menu: Int?,
    @ColumnInfo(name = "nama_menu") var nama_menu: String,
    @ColumnInfo(name = "jenis") var jenis: String,
    @ColumnInfo(name = "harga") var harga: Int
)