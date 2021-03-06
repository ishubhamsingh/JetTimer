package dev.ishubhamsingh.jettimer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ishubhamsingh.jettimer.ui.theme.FiraSans
import dev.ishubhamsingh.jettimer.ui.viewmodel.TimerViewModel

@Composable
fun TimerDisplay(timerViewModel: TimerViewModel = viewModel()) {
    val hour: String by timerViewModel.hourValue.observeAsState(initial = "00")
    val minute: String by timerViewModel.minValue.observeAsState(initial = "00")
    val second: String by timerViewModel.secValue.observeAsState(initial = "00")
    val isValid: Boolean by timerViewModel.isValidInput.observeAsState(false)

    val textColor by animateColorAsState(if(isValid) Color.Red else Color.Black)

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
       Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text = hour, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
           Text(text = "Hours",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
       }
       Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text = minute, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
           Text(text = "Minutes",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
       }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = second, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Seconds",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun NumPad(timerViewModel: TimerViewModel = viewModel()) {
    val isValidInput: Boolean by timerViewModel.isValidInput.observeAsState(initial = false)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(4.dp)) {
            Text(
                text = "1",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(1) })
            Text(
                text = "4",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(4) })
            Text(
                text = "7",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(7) })
        }

        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(4.dp)) {
            Text(
                text = "2",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(2) })
            Text(
                text = "5",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(5) })
            Text(
                text = "8",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(8) })
            Text(
                text = "0",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(0) })
        }

        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(4.dp)) {
            Text(
                text = "3",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(3) })
            Text(
                text = "6",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(6) })
            Text(
                text = "9",
                style = MaterialTheme.typography.h2.copy(fontFamily = FiraSans),
                modifier = Modifier.clickable { timerViewModel.setTimerValue(9) })
            AnimatedVisibility(visible = isValidInput,enter = slideInHorizontally(), exit = slideOutHorizontally()) {
                Image(
                    imageVector = Icons.Outlined.Backspace,
                    contentDescription = "clear",
                    modifier = Modifier
                        .padding(top = 28.dp)
                        .clickable { timerViewModel.deleteTimerValue() })
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
        Spacer(modifier = Modifier.height(30.dp))
        TimerButton()
    }
}

@Composable
fun TimerProgress(timerViewModel: TimerViewModel = viewModel()) {
    val hour: String by timerViewModel.timerHour.observeAsState(initial = "00")
    val minute: String by timerViewModel.timerMin.observeAsState(initial = "00")
    val second: String by timerViewModel.timerSec.observeAsState(initial = "00")
    val isValid: Boolean by timerViewModel.isValidInput.observeAsState(false)

    val textColor by animateColorAsState(if(isValid) Color.Red else Color.Black)

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = hour, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Hours",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = minute, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Minutes",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = second, style = MaterialTheme.typography.h4.copy(fontFamily = FiraSans, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = textColor))
            Text(text = "Seconds",style = MaterialTheme.typography.caption.copy(fontFamily = FiraSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
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
fun TimerRunningScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TimerProgress()
        Spacer(modifier = Modifier.height(30.dp))
        TimerRunningActions()
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerScreen(timerViewModel: TimerViewModel = viewModel()) {
    val isTimerRunning: Boolean by timerViewModel.isTimerRunning.observeAsState(initial = false)
    Crossfade(targetState = isTimerRunning) { timerRunning ->
        when(timerRunning) {
            true -> TimerRunningScreen()
            false -> TimerInputScreen()
        }
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