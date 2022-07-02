package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {
    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList
    init {
        _asteroidList.value = listOf(
            Asteroid(1234, "name1", "2022-1-1", 12.3, 13.4, 14.5, 15.6, true ),
            Asteroid(2345, "name2", "2022-2-2", 22.3, 23.4, 24.5, 25.6, false ))
    }

    fun addAsteroid(asteroid: Asteroid){
        _asteroidList.value = listOf(asteroid)
    }
}