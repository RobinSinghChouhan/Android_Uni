package com.example.textfieldlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            TextFieldLabTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RememberName()
//                }
//            }
        }
    }
}

@Composable
fun RememberName()
{
//    var name by remember { mutableStateOf("") }
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