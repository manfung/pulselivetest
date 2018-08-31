package com.tephra.mc.pulselivetest.data.model

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

import java.util.*

/**
 * For simplicity this is both the API and ROOM entity class for an Article.
 *
 * @param id            Primary Key - The article id
 * @param title         The title of the article
 * @param subtitle      The subtitle of the article
 * @param body          Article body content
 * @param date          The date and time that the article was published, format "DD/MM/YYYY hh:mm"
 */
@Entity
data class Article( @PrimaryKey val id: Int,
                   val title: String,
                   val subtitle: String,
                   val body: String?,
                   val date: Date
                   )