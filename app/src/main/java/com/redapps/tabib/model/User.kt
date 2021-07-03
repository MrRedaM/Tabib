package com.redapps.tabib.model

data class User(
    var idUser : Int,
    var lastMame : String,
    var firstName : String,
    var type : Int
) {
    companion object{
        val DOCTOR = 0
        val PATIENT = 1
    }

}
