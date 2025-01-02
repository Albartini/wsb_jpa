package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    @Transactional
    public void testShouldAddVisitToPatient() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.now().plusDays(1);
        String description = "Regular checkup";

        // when
        PatientEntity updatedPatient = patientDao.addVisitToPatient(patientId, doctorId, visitDate, description);

        // then
        assertThat(updatedPatient).isNotNull();

        DoctorEntity doctor = doctorDao.findOne(doctorId);
        assertThat(doctor.getVisits()).isNotNull();
        assertThat(doctor.getVisits())
                .extracting(VisitEntity::getDescription)
                .contains(description);

        VisitEntity createdVisit = doctor.getVisits()
                .stream()
                .filter(v -> description.equals(v.getDescription()))
                .findFirst()
                .orElse(null);

        assertThat(createdVisit).isNotNull();
        assertThat(createdVisit.getTime()).isEqualTo(visitDate);
    }

    @Test
    @Transactional
    public void testShouldThrowExceptionWhenPatientNotFound() {
        // given
        Long nonExistentPatientId = 999L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.now().plusDays(1);
        String description = "Regular checkup";

        // when/then
        assertThatThrownBy(() ->
                patientDao.addVisitToPatient(nonExistentPatientId, doctorId, visitDate, description)
        ).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Transactional
    public void testShouldThrowExceptionWhenDoctorNotFound() {
        // given
        Long patientId = 1L;
        Long nonExistentDoctorId = 999L;
        LocalDateTime visitDate = LocalDateTime.now().plusDays(1);
        String description = "Regular checkup";

        // when/then
        assertThatThrownBy(() ->
                patientDao.addVisitToPatient(patientId, nonExistentDoctorId, visitDate, description)
        ).isInstanceOf(EntityNotFoundException.class);
    }
}
