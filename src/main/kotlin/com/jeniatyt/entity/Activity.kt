package com.jeniatyt.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "activity")
class Activity(

    @Id
    val id: Long? = null,


    @Column(name = "command")
    var command: String? = null
)
