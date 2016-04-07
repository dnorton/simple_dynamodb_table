package org.dnorton.dynamodb

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import java.util.*

/**
 * Simple example of creating a DynamoDB table
 */
fun main(args : Array <String>) {

    val dbClient = AmazonDynamoDBClient()
            dbClient.setEndpoint("http://localhost:8000")

//    val dynamoDB = DynamoDB(AmazonDynamoDBClient(ProfileCredentialsProvider()))
    val dynamoDB = DynamoDB(dbClient)
    println(dynamoDB)

    /**
     * Create a workouts table
     *
     * Workouts
     *
     * workout_id = Hash key (maybe date?)
     * datetime = range key
     * type : String (run, bike, weights, etc)
     * duration: time for workout
     * distance: number -- distance in meters
     * rating: number (1-5) -- based on perceived value
     * notes: String
     **/


    val tableName = "Workouts"
    deleteTable(tableName)

    // create Workouts table

    val attributeDefinitions =
            ArrayList<AttributeDefinition>()

    attributeDefinitions.add(AttributeDefinition().withAttributeName("Id").withAttributeType(ScalarAttributeType.N))
    attributeDefinitions.add(AttributeDefinition().withAttributeName("Date").withAttributeType(ScalarAttributeType.N))

    val keySchema = ArrayList<KeySchemaElement>()

    keySchema.add(KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH))
    keySchema.add(KeySchemaElement().withAttributeName("Date").withKeyType(KeyType.RANGE))

    val createTableRequest = CreateTableRequest()
        .withTableName(tableName)
        .withAttributeDefinitions(attributeDefinitions)
        .withKeySchema(keySchema)
        .withProvisionedThroughput(ProvisionedThroughput()
            .withReadCapacityUnits(5L)
            .withWriteCapacityUnits(6L))

    val table = dynamoDB.createTable(createTableRequest)

    val workoutTableDescription = table.waitForActive()
    println(workoutTableDescription.tableStatus)

}

/**
 * Delete the table with tableName
 */
fun deleteTable(tableName: String) : Boolean {

    val dbClient = AmazonDynamoDBClient()
    dbClient.setEndpoint("http://localhost:8000")

    //    val dynamoDB = DynamoDB(AmazonDynamoDBClient(ProfileCredentialsProvider()))
    val dynamoDB = DynamoDB(dbClient)
    val table = dynamoDB.getTable(tableName)

    dynamoDB.listTables().forEach { listTable ->
        if (listTable.tableName.equals(tableName)) {
            listTable.delete()
            listTable.waitForDelete()
        }
    }

    return !dynamoDB.listTables().contains(table)

}

fun createTable(tableName : String, hashKey : String, rangeKey: String? = null) {

}