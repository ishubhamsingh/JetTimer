/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ishubhamsingh.jettimer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ishubhamsingh.jettimer.R
import dev.ishubhamsingh.jettimer.ui.theme.FiraSans
import dev.ishubhamsingh.jettimer.ui.theme.purple500
import dev.ishubhamsingh.jettimer.ui.viewmodel.TimerViewModel

@Composable
fun TimerDisplay(timerViewModel: TimerViewModel = viewModel()) {
    val hour: String by timerViewModel.hourValue.observeAsState(initial = "00")
    val minute: String by timerViewModel.minValue.observeAsState(initial = "00")
    val second: String by timerViewModel.secValue.observeAsState(initial = "00")
    val isValid: Boolean by timerViewModel.isValidInput.observeAsState(false)

    val textColor by animateColorAsState(if (isValid) purple500 else MaterialTheme.colors.onBackground)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = hour, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Hours", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
        Text(text = ":", style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = minute, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Minutes", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
        Text(text = ":", style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = second, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Seconds", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun NumPad(timerViewModel: TimerViewModel = viewModel()) {
    val isValidInput: Boolean by timerViewModel.isValidInput.observeAsState(initial = false)
    val bgColor by animateColorAsState(colorResource(id = R.color.greyBackground))
    val textColor by animateColorAsState(MaterialTheme.colors.onBackground)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp),) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(1) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(4) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(7) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "7",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp),) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(2) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(5) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(8) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "8",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(0) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(3) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(6) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "6",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = bgColor)
                    .size(78.dp)
                    .clickable { timerViewModel.setTimerValue(9) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "9",
                    style = MaterialTheme.typography.h3.copy(fontFamily = FiraSans),
                    modifier = Modifier.padding(8.dp), color = textColor
                )
            }

            AnimatedVisibility(visible = isValidInput, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = bgColor)
                        .size(78.dp)
                        .clickable { timerViewModel.deleteTimerValue() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Backspace,
                        contentDescription = "clear",
                        modifier = Modifier.padding(8.dp),
                        tint = textColor
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerButton(timerViewModel: TimerViewModel = viewModel()) {
    val isValidInput: Boolean by timerViewModel.isValidInput.observeAsState(initial = false)
    AnimatedVisibility(visible = isValidInput, enter = fadeIn(), exit = fadeOut()) {
        FloatingActionButton(onClick = { timerViewModel.startTimer() }, modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "play")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerInputScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TimerDisplay()
        Spacer(modifier = Modifier.height(30.dp))
        Divider()
        Spacer(modifier = Modifier.height(50.dp))
        NumPad()
        Spacer(modifier = Modifier.height(25.dp))
        TimerButton()
    }
}

@Composable
fun TimerProgress(timerViewModel: TimerViewModel = viewModel()) {
    val hour: String by timerViewModel.timerHour.observeAsState(initial = "00")
    val minute: String by timerViewModel.timerMin.observeAsState(initial = "00")
    val second: String by timerViewModel.timerSec.observeAsState(initial = "00")
    val isValid: Boolean by timerViewModel.isValidInput.observeAsState(false)

    val textColor by animateColorAsState(if (isValid) purple500 else MaterialTheme.colors.onBackground)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = hour, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Hours", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
        Text(text = ":", style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = minute, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Minutes", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
        Text(text = ":", style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = second, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Seconds", style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor))
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerRunningActions(timerViewModel: TimerViewModel = viewModel()) {
    val isValidInput: Boolean by timerViewModel.isValidInput.observeAsState(initial = false)
    AnimatedVisibility(visible = isValidInput, enter = fadeIn(), exit = fadeOut()) {
        FloatingActionButton(onClick = { timerViewModel.stopTimer() }, modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Stop, contentDescription = "stop")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerRunningScreen(timerViewModel: TimerViewModel = viewModel()) {
    val isTimerFinished: Boolean by timerViewModel.isTimerFinished.observeAsState(initial = false)
    Crossfade(targetState = isTimerFinished) { timerFinishStatus ->
        when (timerFinishStatus) {
            true -> TimerFinishScreen()
            false -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TimerProgress()
                    Spacer(modifier = Modifier.height(30.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(50.dp))
                    TimerRunningActions()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerScreen(timerViewModel: TimerViewModel = viewModel()) {
    val isTimerRunning: Boolean by timerViewModel.isTimerRunning.observeAsState(initial = false)
    Crossfade(targetState = isTimerRunning) { timerRunning ->
        when (timerRunning) {
            true -> TimerRunningScreen()
            false -> TimerInputScreen()
        }
    }
}

@Composable
fun FinishTextArea(timerViewModel: TimerViewModel = viewModel()) {
    val infiniteTransition = rememberInfiniteTransition()
    val textOpacity by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val timerValue: String by timerViewModel.timerValue.observeAsState(initial = "00 : 00 : 00")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Finished", style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = purple500.copy(alpha = textOpacity)))
        Text(text = timerValue, style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colors.onBackground, letterSpacing = 4.sp))
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerFinishAction(timerViewModel: TimerViewModel = viewModel()) {
    val isTimerFinished: Boolean by timerViewModel.isTimerFinished.observeAsState(initial = false)
    AnimatedVisibility(visible = isTimerFinished, enter = fadeIn(), exit = fadeOut()) {
        FloatingActionButton(onClick = { timerViewModel.resetTimerValues() }, modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerFinishScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FinishTextArea()
        Spacer(modifier = Modifier.height(30.dp))
        Divider()
        Spacer(modifier = Modifier.height(50.dp))
        TimerFinishAction()
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun TimerInputScreenPreview() {
    TimerScreen()
}

@ExperimentalAnimationApi
@Preview
@Composable
fun TimerProgressPreview() {
    TimerRunningScreen()
}

@ExperimentalAnimationApi
@Preview
@Composable
fun TimerNumpadPreview() {
    NumPad()
}
