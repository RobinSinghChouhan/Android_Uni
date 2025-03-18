package com.example.textfieldlab

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfieldlab.ui.theme.TextFieldLabTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RandomNumberGenerator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
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
            label = {Text("Max")}
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        ElevatedButton(
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