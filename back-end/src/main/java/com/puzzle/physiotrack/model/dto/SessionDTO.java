package com.puzzle.physiotrack.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puzzle.physiotrack.model.entity.Exercise;
import com.puzzle.physiotrack.model.entity.Patient;
import com.puzzle.physiotrack.model.entity.Session;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SessionDTO {

    private Long id;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    private String comment;
    private Long pt_id;
    private boolean is_completed;

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", time=" + time +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Session toSession() {
        Session s = new Session();
        s.setId(this.id);
        s.setComment(this.comment);
        s.setDate(this.date);
        s.setTime(this.time);
        s.set_completed(this.is_completed);
        //s.setPt_id(this.patient.getId());
        return s;
    }
}
