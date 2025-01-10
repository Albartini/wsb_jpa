package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface PatientService
{
     PatientTO findById(final Long id);
     void delete(final Long id);
     List<VisitTO> findPatientsVisits(Long id);
}
