package com.example.randomtask

import androidx.lifecycle.ViewModel

class ViewModelMain :ViewModel() {

    private var task : String = "Press Play to Start "

    fun setString(ranString:String){
        task = ranString

    }


    fun getString(): String{
        return task
    }




}