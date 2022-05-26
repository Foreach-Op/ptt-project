package com.puzzle.physiotrack.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puzzle.physiotrack.model.dto.PatientDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
//@NamedQuery(query = "select p from Patient p where p. like ?1",name = "Product.findByNameLikeAndPriceGreaterThan")
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pt_id")
    private long id;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientTellNo;
    private Boolean isMan=false;
    private String patientDisease;
    private int recovery;
    private int optimum;
    @ElementCollection
    private List<String> exercise_types_weak;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dr_id")
    private Doctor doctor;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE)
    private List<Session> sessions=new ArrayList<>();

    public PatientDTO toPatientDTO() {
        PatientDTO p = new PatientDTO();
        p.setId(this.id);
        p.setPatientFirstName(this.patientFirstName);
        p.setPatientLastName(this.patientLastName);
        p.setPatientEmail(this.patientEmail);
        p.setPatientTellNo(this.patientTellNo);
        p.setIsMan(this.isMan);
        p.setPatientDisease(this.patientDisease);
        p.setExercises(new ArrayList<>());
        p.setPeriod(0);
        p.setSessionAmount(sessions.size());
        p.setRecovery(this.recovery);
        p.setOptimum(this.optimum);
        p.setExercises(exercise_types_weak.stream().map(e->e.split("-")[0]).collect(Collectors.toList()));
        p.setWeak(exercise_types_weak.get(0).split("-")[1]);
        return p;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pt_id=" + id +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientTellNo='" + patientTellNo + '\'' +
                ", isMan=" + isMan +
                ", patientDisease='" + patientDisease + '\'' +
                ", sessions=" + sessions +
                ", optimum=" + optimum +
                '}';
    }

    public Session getSession(long id){
        return sessions.stream().filter(s->s.getId()==id).collect(Collectors.toList()).get(0);
    }
}
