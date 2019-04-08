package org.wit.mathstutorappv2.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MTAMemStore : MTAStore, AnkoLogger {

    val challenges = ArrayList<MTAModel>()

    override fun findAll(): List<MTAModel> {
        return challenges
    }

    override fun create(challenge: MTAModel) {
        challenge.id = getId()
        challenges.add(challenge)
        logAll()
    }

    override fun update(challenge: MTAModel) {
        var foundChallenge: MTAModel? = challenges.find { p -> p.id == challenge.id }
        if (foundChallenge != null) {
            foundChallenge.name = challenge.name
            foundChallenge.minNum = challenge.minNum
            foundChallenge.maxNum = challenge.maxNum
            foundChallenge.type = challenge.type
            foundChallenge.image = challenge.image


            logAll()
        }
    }

    override fun delete(challenge: MTAModel) {
        challenges.remove(challenge)
    }

    fun logAll() {
        challenges.forEach { info("${it}") }
    }
}