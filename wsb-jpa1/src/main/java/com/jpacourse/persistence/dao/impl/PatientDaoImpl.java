package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @Override
    public List<PatientEntity> findPatientsByLastName(String lastName) {
        return entityManager.createQuery(" select pat from PatientEntity pat " +
                " where pat.lastName = :lastName ", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<VisitEntity> findPatientsVisits(long id) {
        return entityManager.createQuery(" select v from PatientEntity pat " +
                        " join pat.visits v " +
                        " where pat.id = :id ", VisitEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithNumberOfVisits(long numberOfVisits) {
        return entityManager.createQuery(" select pat from PatientEntity pat " +
                        " join pat.visits vis " +
                        " group by pat " +
                        " having count(vis) >= :num ", PatientEntity.class)
                .setParameter("num", numberOfVisits)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsOlderThan(int years) {
        LocalDate date = LocalDate.now().minusYears(years);

        return entityManager.createQuery(" select pat from PatientEntity pat " +
                        " where pat.dateOfBirth <= :date ", PatientEntity.class)
                .setParameter("date", date)
                .getResultList();
    }

}
