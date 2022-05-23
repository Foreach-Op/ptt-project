package com.puzzle.physiotrack.model.dto;

import com.puzzle.physiotrack.model.entity.Patient;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PatientDTO {

    private long id;
    @NotBlank(message = "Patient FirstName is mandatory")
    private String patientFirstName;
    @NotBlank(message = "Patient LastName is mandatory")
    private String patientLastName;
    @NotBlank(message = "Patient Email is mandatory")
    private String patientEmail;
    @NotBlank(message = "Patient TellNo is mandatory")
    private String patientTellNo;
    //@NotBlank(message = "Patient gender is mandatory")
    private Boolean isMan=false;
    private String patientDisease;

    private List<String> exercises;
    private String sessionHour;
    private String weak;

    private int period;
    private int recovery;
    private int sessionAmount;
    private int optimum;


    public PatientDTO(){}

    public PatientDTO(long id, String patientFirstName, String patientLastName, String patientEmail, String patientTellNo, Boolean isMan, String patientDisease, List<String> exercises, String sessionHour, String weak, int period, int sessionAmount, int optimum) {
        this.id = id;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientTellNo = patientTellNo;
        this.isMan = isMan;
        this.patientDisease = patientDisease;
        this.exercises = exercises;
        this.sessionHour = sessionHour;
        this.weak = weak;
        this.period = period;
        this.sessionAmount = sessionAmount;
        this.optimum = optimum;
    }

    public Patient toPatient() {
        Patient p = new Patient();
        //p.setId(this.id);
        p.setPatientFirstName(this.patientFirstName);
        p.setPatientLastName(this.patientLastName);
        p.setPatientEmail(this.patientEmail);
        p.setPatientTellNo(this.patientTellNo);
        p.setIsMan(this.isMan);
        p.setPatientDisease(this.patientDisease);
        p.setOptimum(this.optimum);
        return p;
    }



    @Override
    public String toString() {
        return "PatientDTO{" +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientTellNo='" + patientTellNo + '\'' +
                ", isMan=" + isMan +
                ", patientDisease='" + patientDisease + '\'' +
                ", exercises=" + exercises +
                ", time='" + sessionHour + '\'' +
                ", period=" + period +
                ", sessionAmount=" + sessionAmount +
                '}';
    }
}
