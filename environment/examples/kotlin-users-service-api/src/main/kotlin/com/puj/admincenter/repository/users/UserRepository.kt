package com.puj.admincenter.repository.users

import com.puj.admincenter.domain.users.User

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int>,
                           JpaSpecificationExecutor<User> {

    @Query("""
        SELECT user
        FROM User user
        WHERE user.username = :username
    """)
    fun findUserByUser(username: String): User?

    @Query("""
        SELECT COUNT(user) > 0
        FROM User user
        WHERE user.email = :email
    """)
    fun existsByEmail(@Param("email") email: String): Boolean
}