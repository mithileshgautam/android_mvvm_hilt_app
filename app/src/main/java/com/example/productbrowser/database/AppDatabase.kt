package com.example.productbrowser.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productbrowser.model.Product
import javax.inject.Singleton

@Singleton
@Database(entities = [Product::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
}