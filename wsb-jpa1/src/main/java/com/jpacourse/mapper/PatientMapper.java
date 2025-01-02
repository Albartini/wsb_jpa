package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class PatientMapper
{

    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setGender(patientEntity.getGender());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));

        if (patientEntity.getVisits() != null) {
            patientTO.setVisits(
                    patientEntity.getVisits().stream()
                            .map(VisitMapper::mapToTO)
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if(patientTO == null)
        {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setGender(patientTO.getGender());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));

        if (patientTO.getVisits() != null) {
            patientEntity.setVisits(
                    patientTO.getVisits().stream()
                            .map(VisitMapper::mapToEntity)
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return patientEntity;
    }
}
