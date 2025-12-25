package com.aryana.onlineshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aryana.onlineshop.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert()
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM UserEntity LIMIT 1")
    fun getCurrentUser(): Flow<UserEntity?>

}