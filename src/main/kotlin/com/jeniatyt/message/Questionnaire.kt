package com.jeniatyt.message

object Questionnaire : AbstractMessage() {

    val question1: String = loadTemplate("/message/questionnaire/q1.md")
    val question2: String = loadTemplate("/message/questionnaire/q2.md")
    val question3: String = loadTemplate("/message/questionnaire/q3.md")
    val question4: String = loadTemplate("/message/questionnaire/q4.md")
    val question5: String = loadTemplate("/message/questionnaire/q5.md")
    val question6: String = loadTemplate("/message/questionnaire/q6.md")
    val question7: String = loadTemplate("/message/questionnaire/q7.md")
    val question8: String = loadTemplate("/message/questionnaire/q8.md")
    val question9: String = loadTemplate("/message/questionnaire/q9.md")
    val question10: String = loadTemplate("/message/questionnaire/q10.md")
    val questionEnd: String = loadTemplate("/message/questionnaire/end.md")
}
