package hu.bme.aut.android.together.mockito

import org.mockito.Mockito

fun <T> any(type: Class<T>): T = Mockito.any(type)