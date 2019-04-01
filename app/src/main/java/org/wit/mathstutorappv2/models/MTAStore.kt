package org.wit.mathstutorappv2.models

interface MTAStore {
    fun findAll(): List<MTAModel>
    fun create(challenge: MTAModel)
    fun update(challenge: MTAModel)
    fun delete(challenge: MTAModel)
}