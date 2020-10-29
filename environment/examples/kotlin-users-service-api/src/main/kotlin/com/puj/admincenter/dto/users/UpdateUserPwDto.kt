package com.puj.admincenter.dto.users

data class UpdateUserPwDto(
    val currentPassword: String,
    val newPassword: String,
    val username: String
)