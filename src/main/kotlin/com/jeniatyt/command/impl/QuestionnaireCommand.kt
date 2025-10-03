package com.jeniatyt.command.impl

import com.jeniatyt.command.Command

enum class QuestionnaireCommand(private val command: String): Command {

    Q1("q1"),
    Q2("q2"),
    Q3("q3"),
    Q4("q4"),
    Q5("q5"),
    Q6("q6"),
    Q7("q7"),
    Q8("q8"),
    Q9("q9"),
    SENT("sent_questionnaire");

    override fun getCommand(): String {
        return command
    }
}
