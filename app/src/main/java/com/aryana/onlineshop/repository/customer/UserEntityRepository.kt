package com.aryana.onlineshop.repository.customer

import com.aryana.onlineshop.db.dao.UserDao
import com.aryana.onlineshop.db.entity.UserEntity
import javax.inject.Inject

class UserEntityRepository @Inject constructor(
    private val dao: UserDao,
) {

    suspend fun insert(userEntity: UserEntity) {
        dao.deleteAll()
       return dao.insert(userEntity)
    }

    fun getCurrentUser() = dao.getCurrentUser()

    suspend fun deleteAll() {
        dao.deleteAll()
    }

}


