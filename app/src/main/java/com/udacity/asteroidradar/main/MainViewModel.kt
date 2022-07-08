package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.ImageOfDay
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.AsteroidApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList

    private val _imageOfDay = MutableLiveData<Deferred<ImageOfDay>>()
    val imageOfDay: LiveData<Deferred<ImageOfDay>>
        get() = _imageOfDay



    init {

        _asteroidList.value = listOf(
            Asteroid(1234, "name1", "2022-1-1", 12.3, 13.4, 14.5, 15.6, true ),
            Asteroid(2345, "name2", "2022-2-2", 22.3, 23.4, 24.5, 25.6, false ))
    }

    private fun getImageOfTheDay(){
        viewModelScope.launch {
            try {
                _imageOfDay.value = AsteroidApi.retrofitService.getImageOfDay()
            } catch (e: Exception){
                Log.i("MainViewModel", "Image Of Day not found")
            }
        }
    }

    fun addAsteroid(asteroid: Asteroid){
        _asteroidList.value = listOf(asteroid)
    }
}