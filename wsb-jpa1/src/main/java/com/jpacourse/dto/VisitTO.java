package com.jpacourse.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

public class VisitTO implements Serializable {

    private Long id;
    private String description;
    private LocalDateTime time;
    private Collection<MedicalTreatmentTO> treatments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Collection<MedicalTreatmentTO> getTreatments() {
        return treatments;
    }

    public void setTreatments(Collection<MedicalTreatmentTO> treatments) {
        this.treatments = treatments;
    }
}