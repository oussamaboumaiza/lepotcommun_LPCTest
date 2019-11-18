package com.lakooz.lpctest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "Pot")
data class Pot(
    @PrimaryKey
    @NonNull
    val id: String,
    @ColumnInfo (name = "NAME")
    val name: String? = "",
    // TODO : add missing fields
    @ColumnInfo (name = "CREATION_DATE")
    val creationDate: String? = "",
    @ColumnInfo (name = "IMAGE_URL")
    val imageUrl: String? = "null",
    @ColumnInfo (name = "CATEGORY")
    val category: Int = 0,
    @ColumnInfo (name = "CONTRIBUTORS_COUNT")
    val contributorsCount: Int? = 0,
    @ColumnInfo (name = "AMOUNT")
    val amount: Double? = 0.0
) {


    companion object {
        const val CATEGORY_BIRTHDAY = 0
        const val CATEGORY_FAREWELL = 1
        const val CATEGORY_SOLIDARITY = 2
    }
}