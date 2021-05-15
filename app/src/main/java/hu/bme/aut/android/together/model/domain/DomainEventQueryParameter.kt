package hu.bme.aut.android.together.model.domain

import java.util.*

class DomainEventQueryParameter(
    val name : String,
    val place : String,
    val radius : Int,
    val fromDateTime: Date?,
    val toDateTime: Date?,
    val type : String
)