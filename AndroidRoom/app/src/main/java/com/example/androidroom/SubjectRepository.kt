package com.example.androidroom

import android.app.Application
import kotlinx.coroutines.flow.Flow

class SubjectRepository (application: Application) {
    private var subjectDao: SubjectDAO =
        SubjectDatabase.getDatabase(application).subjectDAO()
    val allSubjects: Flow<List<Subject>> = subjectDao.getAllSubjects()

    suspend fun insert(subject: Subject) {
        subjectDao.insertSubject(subject)
    }

    suspend fun delete(subject: Subject) {
        subjectDao.deleteSubject(subject)
    }

    suspend fun update(subject: Subject) {
        subjectDao.updateSubject(subject)
    }
}