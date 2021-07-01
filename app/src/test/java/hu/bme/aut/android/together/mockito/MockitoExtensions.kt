package hu.bme.aut.android.together.mockito

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

fun <T> any(type: Class<T>): T = Mockito.any(type)

fun <T> any(): T = Mockito.any()

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()