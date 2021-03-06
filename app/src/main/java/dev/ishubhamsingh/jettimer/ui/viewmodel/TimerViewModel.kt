package dev.ishubhamsingh.jettimer.ui.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.text.format.DateUtils
import java.util.concurrent.TimeUnit


class TimerViewModel: ViewModel() {

    private var inputArray: Array<Int> = arrayOf(0,0,0,0,0,0)
    private var arrayPointer = -1

    private var _hourValue: MutableLiveData<String> = MutableLiveData("00")
    var hourValue: LiveData<String> = _hourValue

    private var _minValue: MutableLiveData<String> = MutableLiveData("00")
    var minValue: LiveData<String> = _minValue

    private var _secValue: MutableLiveData<String> = MutableLiveData("00")
    var secValue: LiveData<String> = _secValue

    private var _isValidInput: MutableLiveData<Boolean> = MutableLiveData(false)
    var isValidInput: LiveData<Boolean> = _isValidInput

    private var _isTimerRunning: MutableLiveData<Boolean> = MutableLiveData(false)
    var isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private var _timerHour: MutableLiveData<String> = MutableLiveData("00")
    var timerHour: LiveData<String> = _timerHour

    private var _timerMin: MutableLiveData<String> = MutableLiveData("00")
    var timerMin: LiveData<String> = _timerMin

    private var _timerSec: MutableLiveData<String> = MutableLiveData("00")
    var timerSec: LiveData<String> = _timerSec

    private lateinit var timer: CountDownTimer

    fun setTimerValue(value: Int) {
        if((value == 0 && arrayPointer == -1) || arrayPointer == inputArray.size-1) return

        // Append the new digit.
        System.arraycopy(inputArray, 0, inputArray, 1, arrayPointer + 1)
        inputArray[0] = value
        arrayPointer++
        updateTime()

    }

    fun deleteTimerValue() {
        if(arrayPointer < 0) return

        System.arraycopy(inputArray, 1, inputArray, 0, arrayPointer)
        inputArray[arrayPointer] = 0
        arrayPointer--
        updateTime()
    }

    private fun updateTime() {
        val seconds = inputArray[1] * 10 + inputArray[0]
        val minutes = inputArray[3] * 10 + inputArray[2]
        val hours = inputArray[5] * 10 + inputArray[4]

        _hourValue.value = String.format("%02d", hours)
        _minValue.value = String.format("%02d", minutes)
        _secValue.value = String.format("%02d", seconds)

        _isValidInput.value = arrayPointer != -1

    }

    fun getTimeInMillis(): Long {
        val seconds: Int = inputArray[1] * 10 + inputArray[0]
        val minutes: Int = inputArray[3] * 10 + inputArray[2]
        val hours: Int = inputArray[5] * 10 + inputArray[4]
        return seconds * DateUtils.SECOND_IN_MILLIS + minutes * DateUtils.MINUTE_IN_MILLIS + hours * DateUtils.HOUR_IN_MILLIS
    }

    fun initTimer() {
        timer = object: CountDownTimer(getTimeInMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerValues(millisUntilFinished)
            }

            override fun onFinish() {
                println("Finished")
                resetTimerValues() //TODO do something cool to notify
            }
        }
    }

    fun updateTimerValues(millis: Long) {
        _timerHour.value = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millis))
        _timerMin.value = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millis) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))
        _timerSec.value = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
    }

    fun startTimer() {
        initTimer()
        updateTimerValues(getTimeInMillis())
        _isTimerRunning.value = true
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
        resetTimerValues()
    }

    fun resetTimerValues() {
        inputArray = arrayOf(0,0,0,0,0,0)
        arrayPointer = -1
        _isTimerRunning.value = false
        updateTime()
        updateTimerValues(getTimeInMillis())
    }
}