package com.jeniatyt.repository

import com.jeniatyt.entity.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActionRepository: JpaRepository<Activity, Long> {
}
