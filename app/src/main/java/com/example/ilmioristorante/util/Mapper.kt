package com.example.ilmioristorante.util

import com.example.ilmioristorante.util.Constants.RESTAURANT_API_QUERY_PREFIX

fun getMappedQuery(query: String): String {
    return "$RESTAURANT_API_QUERY_PREFIX $query"
}