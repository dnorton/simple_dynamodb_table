package org.dnorton.dynamodb

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.document.DynamoDB

/**
 * Created by dnorton on 4/4/16.
 */
fun main(args : Array <String>) {
    val dynamoDB = DynamoDB(AmazonDynamoDBClient(ProfileCredentialsProvider()))
    println(dynamoDB)

    /**
     * Create a workouts table
     *
     * Workouts
     *
     * workout_id = Hash key (maybe date?)
     * type : String (run, bike, weights, etc)
     * duration: time for workout
     * distance: number -- distance in meters
     * rating: number (1-5) -- based on perceived value
     * notes: String
     **/



}