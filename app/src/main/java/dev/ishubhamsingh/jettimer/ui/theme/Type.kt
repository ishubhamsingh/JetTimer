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
package dev.ishubhamsingh.jettimer.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ishubhamsingh.jettimer.R

val FiraSans = FontFamily(
    Font(R.font.firasans_regular, FontWeight.Normal),
    Font(R.font.firasans_extralight, FontWeight.ExtraLight),
    Font(R.font.firasans_light, FontWeight.Light),
    Font(R.font.firasans_thin, FontWeight.Thin),
    Font(R.font.firasans_medium, FontWeight.Medium),
    Font(R.font.firasans_semibold, FontWeight.SemiBold),
    Font(R.font.firasans_extrabold, FontWeight.ExtraBold),
    Font(R.font.firasans_bold, FontWeight.Bold),
    Font(R.font.firasans_black, FontWeight.Black),
)

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    button = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),

    caption = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)
