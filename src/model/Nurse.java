package model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "nurses")
public class Nurse extends MedicalStaff {

    @NotBlank(message = "Qualification level is required")
    private String qualificationLevel;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    public Nurse() {
        super();
    }


    public Nurse(String name, String departmentId, String qualificationLevel, Shift shift) {
        super(name, departmentId);
        this.qualificationLevel = qualificationLevel;
        this.shift = shift;
    }

    public String getQualificationLevel() { return qualificationLevel; }
    public void setQualificationLevel(String qualificationLevel) { this.qualificationLevel = qualificationLevel; }

    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }


    @Override
    public String getRole() {
        return "Nurse";
    }

    public enum Shift {
        MORNING,
        AFTERNOON,
        NIGHT
    }
}