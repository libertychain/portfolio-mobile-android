package com.example.meucartaodevisitas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meucartaodevisitas.data.local.dao.ProjectDao
import com.example.meucartaodevisitas.data.local.entity.ProjectEntity

@Database(
    entities = [ProjectEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProjectDatabase : RoomDatabase() {
    
    abstract fun projectDao(): ProjectDao
    
    companion object {
        const val DATABASE_NAME = "project_database"
    }
}