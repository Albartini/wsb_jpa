package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    public PatientEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null || doctor == null) {
            throw new EntityNotFoundException("Patient or Doctor not found");
        }

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitDate);
        visit.setDescription(description);

        entityManager.persist(visit);

        if (doctor.getVisits() == null) {
            doctor.setVisits(new ArrayList<>());
        }
        doctor.getVisits().add(visit);

        entityManager.merge(doctor);

        return entityManager.merge(patient);
    }

}
