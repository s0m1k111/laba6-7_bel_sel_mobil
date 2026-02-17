package com.belyaev_selivanov.android_belyaev_selivanov_tip_time_theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.belyaev_selivanov.android_belyaev_selivanov_tip_time_theme.ui.theme.Android_belyaev_selivanov_Tip_Time_ThemeTheme
import java.text.NumberFormat
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.annotation.StringRes
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_belyaev_selivanov_Tip_Time_ThemeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

private fun calculateTip(amount: Double, tipPrecent: Double = 15.0): String{
    val tip = tipPrecent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}
@Composable
fun TipTimeLayout(){
    var amountInput by remember { mutableStateOf("0")}
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipInput by remember { mutableStateOf("") }
    val tipPrecent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPrecent)
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            label = R.string.bill_amount,
            value = amountInput,
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth())
        EditNumberField(
            label = R.string.how_was_the_service,
            value = tipInput,
            onValueChange = { tipInput = it},
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.tip_amount , tip),
            style = MaterialTheme.typography.displaySmall
       )
        Spacer(modifier = Modifier.height(150.dp))

    }
}
@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview(){
    Android_belyaev_selivanov_Tip_Time_ThemeTheme{
        TipTimeLayout()
    }
}
@Composable
fun EditNumberField(
    @StringRes label: Int,
    value:String,
    onValueChange:(String) -> Unit,
    modifier: Modifier = Modifier
){
//    var amountInput by remember { mutableStateOf("0")}
//    val amount = amountInput.toDoubleOrNull() ?: 0.0
//    val tip = calculateTip(amount)
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(label)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),


    )

}
