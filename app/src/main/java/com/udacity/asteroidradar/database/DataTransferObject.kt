package com.udacity.asteroidradar.database

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import org.json.JSONObject

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val NetworkAsteroidList: String)

// convert to Domain Object
fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
    return parseAsteroidsJsonResult(JSONObject(NetworkAsteroidList))
}

// convert to to Database Entity
fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return map{
        DatabaseAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}