package test.com.jpacourse.persistance.dao;

import com.jpacourse.WsbJpaApplication;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WsbJpaApplication.class)
public class DoctorsDaoImplTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testOptimisticLockingShouldThrowException() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Isabell");
        doctor.setLastName("Testowa");
        doctor.setDoctorNumber("111");
        doctor.setSpecialization(Specialization.CARDIOLOGY);
        doctor.setTelephoneNumber("111-1111");

        entityManager.persist(doctor);
        entityManager.flush();

        long doctorId =  doctor.getId();
        DoctorEntity doctor1 = entityManager.find(DoctorEntity.class, doctorId);
        DoctorEntity doctor2 = entityManager.find(DoctorEntity.class, doctorId);

        entityManager.clear();

        doctor1.setTelephoneNumber("222-3333");

        entityManager.merge(doctor1);
        entityManager.flush();

        doctor2.setTelephoneNumber("333-2222");

        assertThrows(OptimisticLockException.class, () -> {
            entityManager.merge(doctor2);
            entityManager.flush();
        });
    }
}
