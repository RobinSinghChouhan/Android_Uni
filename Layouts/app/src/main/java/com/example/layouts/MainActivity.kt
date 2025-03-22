package com.example.layouts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layouts.ui.theme.LayoutsTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    SeasonScreen(modifier = Modifier.padding(innerPadding))
                LazyColumnScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnScreenPreview()
{
    LazyColumnScreen()
}

@Composable
fun LazyColumnScreen(modifier: Modifier = Modifier)
{
    val countries = remember { mutableStateListOf("USA","Canada","Australia") }
    val context = LocalContext.current
    var newCountry by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(16.dp)
    )
    {
        Text("List of Countries",
            style = MaterialTheme.typography.bodyLarge)

        OutlinedTextField(
            value = newCountry,
            onValueChange = {newCountry = it},
            label = {Text("Add Country")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                if(newCountry.isNotEmpty())
                {
                    countries.add(newCountry)
                    newCountry=""
                }
            }
        ){
            Text("Add")
        }

        LazyColumn {
            items(countries.size) {

                index -> Row(
                modifier=Modifier.fillMaxSize().padding(8.dp).clickable {
                    Toast.makeText(
                        context,
                        countries[index],
                        Toast.LENGTH_SHORT
                    ).show()
                },
                    verticalAlignment = Alignment.CenterVertically
                )
            {

                Text(
                    countries[index],
                    modifier=modifier.weight(1f).fillMaxSize(),
                    textAlign = TextAlign.Start
                )
                Button(
                    onClick = {
                        countries.removeAt(index)
                    }
                ) {
                   Icon(Icons.Default.Delete,"Delete")
                }
            }
            }
        }
    }
}

@Composable
fun SeasonBtn(title: String)
{
    val context = LocalContext.current
    var msg = ""
    when (title) {
        "Spring" -> msg = "September, October, November"
        "Summer" -> msg = "December, January, February"
        "Autumn" -> msg = "March April May"
        "Winter" -> msg = "June, July, August"
        else -> { // Note the block
            print("None")
        }
    }
    ElevatedButton(
        onClick = {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
    ) {
        Text(title)
    }
}

@Composable
fun SeasonScreen(modifier: Modifier = Modifier)
{
    val imgUrl = painterResource(R.drawable.seasons)
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Seasons and Months",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                fontSize = 24.sp)
        }
        Surface(
            modifier = Modifier.padding(top = 20.dp)
        )
        {
            Image(
                painter = imgUrl,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            SeasonBtn("Spring")
            SeasonBtn("Summer")
            SeasonBtn("Autumn")
            SeasonBtn("Winter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeasonScreenPreview()
{
    SeasonScreen()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LayoutsTheme {
        Greeting("Android")
    }
}