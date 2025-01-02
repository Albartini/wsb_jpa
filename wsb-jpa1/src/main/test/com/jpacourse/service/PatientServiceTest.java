package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientService patientService;

    @Test
    @Transactional
    public void shouldDeletePatientWithVisitsButNotDoctors() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        Long visitId = 1L;

        PatientEntity patientBeforeDelete = patientDao.findOne(patientId);
        DoctorEntity doctorBeforeDelete = doctorDao.findOne(doctorId);

        assertThat(patientBeforeDelete).isNotNull();
        assertThat(doctorBeforeDelete).isNotNull();
        // when
        patientService.delete(patientId);

        // then
        assertThat(patientDao.findOne(patientId)).isNull();
        assertThat(visitDao.findOne(visitId)).isNull();

        DoctorEntity doctorAfterDelete = doctorDao.findOne(doctorId);
        assertThat(doctorAfterDelete)
                .isNotNull()
                .satisfies(doctor -> {
                    assertThat(doctor.getId()).isEqualTo(doctorBeforeDelete.getId());
                    assertThat(doctor.getFirstName()).isEqualTo(doctorBeforeDelete.getFirstName());
                    assertThat(doctor.getLastName()).isEqualTo(doctorBeforeDelete.getLastName());
                });
    }


    @Test
    @Transactional
    public void shouldNotFailWhenDeletingNonExistentPatient() {
        // given
        Long nonExistentPatientId = 999999L;

        // Sprawdzamy czy pacjent rzeczywiście nie istnieje
        assertThat(patientDao.findOne(nonExistentPatientId)).isNull();

        // when & then
        assertThatCode(() -> patientService.delete(nonExistentPatientId))
                .doesNotThrowAnyException();
    }

    @Test
    @Transactional
    public void shouldFindPatientByIdAndMapCorrectly() {
        // given
        Long patientId = 1L;

        // when
        PatientTO patientTO = patientService.findById(patientId);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);
        assertThat(patientTO.getFirstName()).isNotEmpty();
        assertThat(patientTO.getLastName()).isNotEmpty();
        assertThat(patientTO.getTelephoneNumber()).isNotEmpty();
        assertThat(patientTO.getPatientNumber()).isNotEmpty();
        assertThat(patientTO.getDateOfBirth()).isNotNull();
        assertThat(patientTO.getGender()).isNotNull();

        AddressTO addressTO = patientTO.getAddress();
        assertThat(addressTO).isNotNull()
                .satisfies(address -> {
                    assertThat(address.getId()).isNotNull();
                    assertThat(address.getCity()).isNotEmpty();
                    assertThat(address.getAddressLine1()).isNotEmpty();
                    assertThat(address.getPostalCode()).isNotEmpty();
                });

        Collection<VisitTO> visitsTO = patientTO.getVisits();
        assertThat(visitsTO).isNotNull();

        if (!visitsTO.isEmpty()) {
            assertThat(visitsTO).allSatisfy(visit -> {
                assertThat(visit.getId()).isNotNull();
                assertThat(visit.getDescription()).isNotNull();
                assertThat(visit.getTime()).isNotNull();

                assertThat(visit.getTime()).isBeforeOrEqualTo(LocalDateTime.now());

                Collection<MedicalTreatmentTO> treatments = visit.getTreatments();
                if (treatments != null && !treatments.isEmpty()) {
                    assertThat(treatments).allSatisfy(treatment -> {
                        assertThat(treatment.getId()).isNotNull();
                    });
                }
            });
        }
    }

    @Test
    @Transactional
    public void shouldReturnNullWhenPatientNotFound() {
        // given
        Long nonExistentPatientId = 999999L;

        // when
        PatientTO patientTO = patientService.findById(nonExistentPatientId);

        // then
        assertThat(patientTO).isNull();
    }
}