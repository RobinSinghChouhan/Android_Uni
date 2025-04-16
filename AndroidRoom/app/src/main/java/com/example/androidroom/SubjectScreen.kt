package com.example.androidroom

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SubjectScreen(subjectViewModel: SubjectViewModel, modifier: Modifier) {
    val subjects by subjectViewModel.allSubjects.collectAsState(emptyList())
    var name by remember { mutableStateOf("") }
    var selectedSubject by remember { mutableStateOf<Subject?>(null)}
    val context = LocalContext.current

    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Database Operations",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Subject Name")},
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        val subject = Subject(name = name)
                        subjectViewModel.insertSubject(subject)
                        name = ""
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Add")
            }

            Button(
                onClick = {
                    selectedSubject?.let {
                        val updated = it.copy(name = name)
                        subjectViewModel.updateSubject(updated)
                        name = ""
                        selectedSubject = null
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Update")
            }
            Button(
                onClick = {
                    if (selectedSubject != null) {
                        subjectViewModel.deleteSubject(selectedSubject!!)
                    } else {
                        Toast.makeText(
                            context,
                            "Please select a subject to delete",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    name = ""
                    selectedSubject = null
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Delete")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(subjects.size) {
                index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if(selectedSubject?.uid == subjects[index].uid) Color.LightGray else Color.Transparent
                        )
                        .clickable {
                            name = subjects[index].name
                            selectedSubject = subjects[index]
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = subjects[index].name,
                        modifier = Modifier.weight(1f)
                    )
                }
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}