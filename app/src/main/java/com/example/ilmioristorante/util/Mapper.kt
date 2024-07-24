package com.example.ilmioristorante.util

import com.example.ilmioristorante.util.Constants.UNSPLASH_API_QUERY_PREFIX

fun getMappedQuery(query: String): String {
    return "$UNSPLASH_API_QUERY_PREFIX $query"
}