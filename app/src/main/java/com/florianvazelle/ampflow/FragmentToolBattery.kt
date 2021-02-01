package com.florianvazelle.ampflow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.math.absoluteValue
import com.florianvazelle.ampflow.databinding.FragmentToolBatteryBinding
import kotlin.math.roundToInt

class FragmentToolBattery : Fragment() {

    private var _binding: FragmentToolBatteryBinding? = null
    private val binding get() = _binding!!

    private val battery by lazy { Battery(requireContext()) }

    private val monitor: Runnable = object : Runnable {
        override fun run() {
            update()
        }
    }

    private val intervalometer = Intervalometer(monitor)

    companion object {
        fun newInstance() = FragmentToolBattery()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentToolBatteryBinding.inflate(inflater, container, false)
        return binding.root
        // return inflater.inflate(R.layout.fragment_tool_battery, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        battery.start(this::onBatteryUpdate)
        intervalometer.interval(20)
    }

    private fun onBatteryUpdate(): Boolean {
        return true
    }

    private fun update() {
        val chargingStatus = battery.chargingStatus
        val isCharging = chargingStatus == BatteryChargingStatus.Charging

        // If charging and current is negative, invert current
        val current = battery.current.absoluteValue * if (isCharging) 1 else -1
        val capacity = battery.capacity
        val percent = battery.percent.roundToInt()

        binding.batteryPercentage.text = getString(R.string.percent_format, percent)
        binding.batteryCapacity.text = getString(R.string.battery_capacity_format, capacity)
        binding.batteryCapacity.visibility = if (capacity == 0f) View.GONE else View.VISIBLE
        binding.batteryLevelBar.progress = percent

        if (current.absoluteValue >= 0.5f) {
            val formattedCurrent = getString(R.string.current_format, current)

            binding.batteryCurrent.text = when {
                current > 500 -> getString(R.string.charging_fast, formattedCurrent)
                current > 0 -> getString(R.string.charging_slow, formattedCurrent)
                current < -500 -> getString(R.string.discharging_fast, formattedCurrent)
                else -> getString(R.string.discharging_slow, formattedCurrent)
            }

        } else {
            val chargeMethod = battery.chargingMethod

            binding.batteryCurrent.text = when {
                isCharging && chargeMethod == BatteryChargingMethod.AC -> getString(
                    R.string.charging_fast,
                    getString(R.string.battery_power_ac)
                )
                isCharging && chargeMethod == BatteryChargingMethod.USB -> getString(
                    R.string.charging_slow,
                    getString(R.string.battery_power_usb)
                )
                isCharging && chargeMethod == BatteryChargingMethod.Wireless -> getString(
                    R.string.charging_wireless,
                    getString(R.string.battery_power_wireless)
                )
                else -> ""
            }

        }
    }
}