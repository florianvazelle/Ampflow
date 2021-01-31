package com.florianvazelle.ampflow

interface ISensor {
    val accuracy: Accuracy
    val hasValidReading: Boolean
    fun start(listener: SensorListener)
    fun stop(listener: SensorListener?)
}