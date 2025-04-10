package com.example.textfieldlab

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfieldlab.ui.theme.TextFieldLabTheme
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RandomNumberGenerator(modifier = Modifier.padding(innerPadding))
//                        DisplayDatePicker(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplayDatePicker(modifier: Modifier = Modifier)
{
    val calendar = Calendar.getInstance()
    var birthday by remember { mutableStateOf("") }
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    var showDatepicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }

    Column(modifier = modifier.padding(50.dp))
    {
        TextField(
            value = birthday,
            onValueChange = {},
            readOnly = true,
            label = {Text("Birthday")},
            modifier = Modifier.fillMaxWidth().clickable {
                showDatepicker = true
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showDatepicker = true
                    }.size(40.dp)
                )
            }
        )
        if(showDatepicker)
        {
            DatePickerDialog(
                onDismissRequest = {
                    showDatepicker = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDatepicker = false
                            selectedDate = datePickerState.selectedDateMillis!!
                            birthday = "DoB: ${formatter.format(Date(selectedDate))}"
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatepicker = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DisplayDatePickerPreview()
{
    DisplayDatePicker()
}

@Composable
fun RandomNumberGenerator(modifier: Modifier = Modifier)
{
    val maxi = remember { mutableStateOf("") }
    val rnum = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Column(modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = maxi.value,
            onValueChange = {
                maxi.value = it
            },
//            colors = TextFieldColors,
            label = {Text("Max")}
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        ElevatedButton(
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF6200EE), // Purple background
                contentColor = Color.White // Text/icon color
            ),
            onClick = {
                try {
                    rnum.intValue = Random.nextInt(0, maxi.value.toInt())
                }catch (e : Exception)
                {
                    Toast.makeText(context,"Please enter valid number",
                        Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Random Number")
        }
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Text("Result: ${rnum.intValue}")
    }
}

@Preview(showBackground = true)
@Composable
fun RandomNumberGeneratorPreview()
{
    RandomNumberGenerator()
}


@Composable
fun RememberName()
{
    val name = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp))
    {
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        if(name.value.isNotEmpty())
        {
            Text(
                text = "Hello, ${name.value}",
                modifier= Modifier.padding(bottom = 8.dp),
                fontSize = 40.sp
            )
        }
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = {Text("Name")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RememberNamePreview()
{
    RememberName()
}