package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationBar()
//            BottomNavigationBarTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android ",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
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
    BottomNavigationBarTheme {
        Greeting("Android")
    }
}

@Composable
fun BottomNavigationBar() {
    val navRoutes = listOf(
        NavRoute("home", Icons.Filled.Home,"Home"),
        NavRoute("profile",Icons.Filled.Person,"Profile"),
        NavRoute("about",Icons.Filled.Info,"About")
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.padding(bottom = 20.dp),
                backgroundColor = Color(0xffadd8e6)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navRoutes.forEach{ navRoute ->
                    BottomNavigationItem(
                        icon = { Icon(navRoute.icon, contentDescription = navRoute.label) },
                        label = {Text(navRoute.label)},
                        selected = currentDestination?.route == navRoute.route,
                        onClick = {
                            navController.navigate(navRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        )
        {
            composable("home") { Home() }
            composable("profile") {  Profile() }
            composable("about") { About() }
        }
    }
}