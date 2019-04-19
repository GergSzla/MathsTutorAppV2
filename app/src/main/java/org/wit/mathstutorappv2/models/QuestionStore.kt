package org.wit.mathstutorappv2.models

interface QuestionStore {
    fun findAllQuestions(): List<Question>
    fun createQuestions(question: Question)
    fun deleteQuestions(question: Question)
}