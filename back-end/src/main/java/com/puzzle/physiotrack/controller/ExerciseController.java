package com.puzzle.physiotrack.controller;

import com.puzzle.physiotrack.exception.NotFoundException;
import com.puzzle.physiotrack.model.entity.Doctor;
import com.puzzle.physiotrack.model.entity.Exercise;
import com.puzzle.physiotrack.model.entity.Patient;
import com.puzzle.physiotrack.model.entity.Session;
import com.puzzle.physiotrack.service.DoctorService;
import com.puzzle.physiotrack.service.ExerciseService;
import com.puzzle.physiotrack.service.PatientService;
import com.puzzle.physiotrack.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ExerciseController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    SessionService sessionService;

    @Autowired
    ExerciseService exerciseService;

    @RequestMapping(value="/exercise/{sid}", method = RequestMethod.GET)
    public List<Exercise> getExercises(@PathVariable long sid) {
        Session currentSession=sessionService.getSession(sid);
        if(currentSession==null)
            throw new NotFoundException("Session not found with id:" + sid);
        return currentSession.getExercises();
    }

    @RequestMapping(value="/patientexercise/{pid}", method = RequestMethod.GET)
    public HashMap<Integer,HashMap<Integer,HashMap<String,List<Integer>>>> getPatientExercises(@PathVariable long pid) {
        Doctor currentDoctor= doctorService.getDoctor(3);
        Patient patient=patientService.getPatient(currentDoctor,pid);
        List<Session> sessions=patient.getSessions();
        HashMap<Integer,HashMap<Integer,HashMap<String,List<Integer>>>> map=new HashMap<>();

        int key1=1;
        for (Session s : sessions) {
            if (!s.is_completed())
                continue;

            if(!map.containsKey(key1))
                map.put(key1,new HashMap<>());
            HashMap<Integer,HashMap<String,List<Integer>>> currentMap=map.get(key1);
            List<Exercise> exercises=s.getExercises();
            if(exercises==null)
                break;

            int key2=1;
            for (Exercise e : exercises) {
                HashMap<String,List<Integer>> tempMap= new HashMap<>();
                tempMap.put("shoulder",e.getShoulder_angles());
                tempMap.put("hip",e.getHip_angles());
                currentMap.put(key2,tempMap);
                key2++;
            }
            key1++;
        }
        return map;
    }

    @RequestMapping(value = "/exercise/{sid}/{eid}", method = RequestMethod.GET)
    public Exercise getExercise(@PathVariable long sid,@PathVariable long eid) {
        Session currentSession=sessionService.getSession(sid);
        if(currentSession==null)
            throw new NotFoundException("Session not found with id:" + sid);

        return currentSession.getExercise(eid);
    }

    @PostMapping(value="/exercise/{sid}")
    public Exercise addExercise(@PathVariable long sid, @Valid @RequestBody Exercise exercise) {
        Session currentSession=sessionService.getSession(sid);
        exercise.setSession(currentSession);
        return exerciseService.addExercise(exercise);
    }

    @PutMapping(value="/exercise/{eid}")
    public Exercise updateExercise(@PathVariable long eid, @RequestBody Map<String, List<Integer>> payload) {
        Exercise currentExercise=exerciseService.getExercise(eid);
        currentExercise.getHip_angles().addAll(payload.get("hip"));
        currentExercise.getShoulder_angles().addAll(payload.get("shoulder"));
        return exerciseService.updateExercise(currentExercise);
    }

    @RequestMapping(value = "/exercise/{eid}", method = RequestMethod.DELETE)
    public Exercise deleteExercise(@PathVariable long eid) {
        return exerciseService.deleteExercise(eid);
    }
}

