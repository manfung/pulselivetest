package com.tephra.mc.pulselivetest.data.model

/**
 * API response Model for a News object.
 *
 * @param items     list of articles
 */
data class News(val items: List<Article>)