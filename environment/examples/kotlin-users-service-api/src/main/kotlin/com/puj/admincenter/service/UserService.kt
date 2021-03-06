package com.puj.admincenter.service

import com.puj.admincenter.domain.users.User
import com.puj.admincenter.dto.users.UserDto
import com.puj.admincenter.dto.users.CreateUserDto
import com.puj.admincenter.dto.users.UpdateUserPwDto
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.repository.users.UserRepository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

import java.io.Serializable
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {
    companion object {
        val LOG = LoggerFactory.getLogger(UserService::class.java)!!
    }

    fun count(): Long {
        return userRepository.count()
    }

    fun getById(userId: Int,
                authorization: String): ResponseEntity<*> {

        val user = userRepository.findById(userId)  // Hace solo el query
        return if (user.isPresent()) {
            ResponseEntity.ok(UserDto.convert(user.get()))
        } else {
            ResponseEntity<Any>(HttpStatus.NOT_FOUND)
        }
    }

    fun getHashedPassword(password: String) : String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
    
    fun create(createUserDto: CreateUserDto): ResponseEntity<*> {
        if (userRepository.existsByEmail(createUserDto.email)) {
            val messageError = "User with email: ${createUserDto.email} already exists."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                       HttpStatus.CONFLICT)
        }
        val hashedPassword = getHashedPassword(createUserDto.password)
        val user = User(email = createUserDto.email,
                        name = createUserDto.name,
                        password = hashedPassword,
                        username = createUserDto.username)

        val userSaved = userRepository.save(user)
        LOG.info("User ${createUserDto.email} created with id ${userSaved.id}")

        val responseDto = IdResponseDto(userSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                                             HttpStatus.CREATED)
    }

    fun updatePasswordByUsername(updateUserPwDto : UpdateUserPwDto): ResponseEntity<*>{
        val user = userRepository.findUserByUsername(updateUserPwDto.username)
        if (user == null) {
            val messageError = "User with username: ${updateUserPwDto.username} does not exist."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                        HttpStatus.NOT_FOUND)
        }
        else{
            if (BCrypt.checkpw(updateUserPwDto.currentPassword, user.password)) {
                val hashedNewPassword = getHashedPassword(updateUserPwDto.newPassword)
                userRepository.updatePasswordByUsername(hashedNewPassword, updateUserPwDto.username)
                val message = "User ${updateUserPwDto.username} password was updated."
                LOG.info(message)
                return ResponseEntity<Any>(message, HttpStatus.OK)
            } else {
                val messageError = "Password not valid for user with username: ${updateUserPwDto.username}."
                LOG.error(messageError)
                return ResponseEntity<Any>(messageError,
                                            HttpStatus.CONFLICT)
            }

        }
    }
}