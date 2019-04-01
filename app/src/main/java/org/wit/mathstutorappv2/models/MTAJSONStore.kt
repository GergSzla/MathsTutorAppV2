package org.wit.mathstutorappv2.models


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.mathstutorappv2.helpers.*
import java.util.*

val JSON_FILE = "placemarks.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<MTAModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MTAJSONStore : MTAStore, AnkoLogger {

    val context: Context
    var challenges = mutableListOf<MTAModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MTAModel> {
        return challenges
    }

    override fun create(challenge: MTAModel) {
        challenge.id = generateRandomId()
        challenges.add(challenge)
        serialize()
    }

    override fun update(challenge: MTAModel) {
        val MTAList = findAll() as ArrayList<MTAModel>
        var foundChallenge: MTAModel? = MTAList.find { p -> p.id == challenge.id }
        if (foundChallenge != null) {
            foundChallenge.title = challenge.title
            foundChallenge.description = challenge.description
        }
        serialize()
    }

    override fun delete(challenge: MTAModel) {
        challenges.remove(challenge)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(challenges, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        challenges = Gson().fromJson(jsonString, listType)
    }
}