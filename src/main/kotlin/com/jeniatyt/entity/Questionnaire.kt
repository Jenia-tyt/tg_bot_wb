package com.jeniatyt.entity

import com.jeniatyt.dto.QuestionnaireData
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "questionnaire")
class Questionnaire (

    @Id
    val id: Long? = null,


    @Type(JsonBinaryType::class)
    @Column(name = "data", columnDefinition = "jsonb")
    var data: QuestionnaireData? = null
)
