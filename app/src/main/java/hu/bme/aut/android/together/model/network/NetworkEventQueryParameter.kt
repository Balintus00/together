package hu.bme.aut.android.together.model.network

import java.util.*

class NetworkEventQueryParameter (
    val name : String,
    val place : String,
    val radius : Int,
    val fromDateTime: String,
    val toDateTime: String,
    val type : String
)