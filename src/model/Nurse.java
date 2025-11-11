package model;

public class Nurse extends MedicalStaff {
    private String qualificationLevel;

    public Nurse() {
        super();
    }

    public Nurse(String id, String name, String departmentId, String qualificationLevel) {
        super(id, name, departmentId);
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }


    public enum Shift {
        MORNING,
        AFTERNOON,
        NIGHT
    }
}
