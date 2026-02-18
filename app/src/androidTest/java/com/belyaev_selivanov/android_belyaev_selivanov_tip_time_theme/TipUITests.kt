package com.belyaev_selivanov.android_belyaev_selivanov_tip_time_theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.belyaev_selivanov.android_belyaev_selivanov_tip_time_theme.ui.theme.Android_belyaev_selivanov_Tip_Time_ThemeTheme
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import java.text.NumberFormat
import androidx.compose.foundation.layout.fillMaxSize


class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            Android_belyaev_selivanov_Tip_Time_ThemeTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found"
        )
    }
}
}