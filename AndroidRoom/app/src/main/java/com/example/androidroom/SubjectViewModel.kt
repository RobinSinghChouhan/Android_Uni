package com.example.androidroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val cRespository: SubjectRepository
    init {
        cRespository = SubjectRepository(application)
    }
    val allSubjects: Flow<List<Subject>> = cRespository.allSubjects

    fun insertSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) {
        cRespository.insert(subject)
    }

    fun updateSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) {
        cRespository.update(subject)
    }

    fun deleteSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) {
        cRespository.delete(subject)
    }
}