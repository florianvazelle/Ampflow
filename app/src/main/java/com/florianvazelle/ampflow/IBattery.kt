package com.florianvazelle.ampflow

interface IBattery : ISensor {
    val percent: Float
    val capacity: Float
    val health: BatteryHealth
    val voltage: Float
    val current: Float
    val chargingMethod: BatteryChargingMethod
    val chargingStatus: BatteryChargingStatus
}