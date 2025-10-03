package com.jeniatyt.repository

import com.jeniatyt.entity.Questionnaire
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionnaireRepository: JpaRepository<Questionnaire, Long>
