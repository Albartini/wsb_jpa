package com.jpacourse.mapper;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());

        if (visitEntity.getTreatments() != null) {
            visitTO.setTreatments(
                    visitEntity.getTreatments().stream()
                            .map(MedicalTreatmentMapper::mapToTO)
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());

        if (visitTO.getTreatments() != null) {
            visitEntity.setTreatments(
                    visitTO.getTreatments().stream()
                            .map(MedicalTreatmentMapper::mapToEntity)
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return visitEntity;
    }
}