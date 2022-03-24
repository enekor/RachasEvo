package com.example.rachasevo.baseDeDatos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.rachasevo.baseDeDatos.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = REPLACE)
    fun insertItem(item: Item)

    @Query("Select * from item")
    fun getAllItems():List<Item>

    @Delete
    fun deleteItem(item:Item)
}