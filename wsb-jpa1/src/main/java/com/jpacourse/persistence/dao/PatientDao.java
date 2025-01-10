package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
     PatientEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
     List<PatientEntity> findPatientsByLastName(String lastName);
     List<VisitEntity> findPatientsVisits(long id);
     List<PatientEntity> findPatientsWithNumberOfVisits(long numberOfVisits);
     List<PatientEntity> findPatientsOlderThan(int years);
}

