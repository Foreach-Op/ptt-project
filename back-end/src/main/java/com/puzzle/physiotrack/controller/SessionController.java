package com.puzzle.physiotrack.controller;

import com.puzzle.physiotrack.exception.NotFoundException;
import com.puzzle.physiotrack.model.dto.SessionDTO;
import com.puzzle.physiotrack.model.entity.Doctor;
import com.puzzle.physiotrack.model.entity.Patient;
import com.puzzle.physiotrack.model.entity.Session;
import com.puzzle.physiotrack.service.DoctorService;
import com.puzzle.physiotrack.service.PatientService;
import com.puzzle.physiotrack.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class SessionController {
    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value="/session/{pid}", method = RequestMethod.GET)
    public List<SessionDTO> getSessions(@PathVariable long pid) {
        //Doctor currentDoctor= doctorService.getCurrentlyLoggedInDoctor();
        Doctor currentDoctor= doctorService.getDoctor(3);

        Patient currentPatient=patientService.getPatient(currentDoctor,pid);
        if(currentPatient==null)
            throw new NotFoundException("Patient not found with id:" + pid);
        return currentPatient.getSessions().stream().map(Session::toSessionDTO).collect(Collectors.toList());
    }

    @RequestMapping(value = "/session/{pid}/{sid}", method = RequestMethod.GET)
    public SessionDTO getSession(@PathVariable long pid,@PathVariable long sid) {
        //Doctor currentDoctor= doctorService.getCurrentlyLoggedInDoctor();
        Doctor currentDoctor= doctorService.getDoctor(3);

        Patient currentPatient=patientService.getPatient(currentDoctor,pid);
        if(currentPatient==null)
            throw new NotFoundException("Patient not found with id:" + pid);
        Session session=currentPatient.getSession(sid);
        if(session==null)
            throw new NotFoundException("No patient");
        return session.toSessionDTO();
    }

    @RequestMapping(value="/session", method = RequestMethod.GET)
    public List<SessionDTO> getTodaySessions() {
        //Doctor currentDoctor= doctorService.getCurrentlyLoggedInDoctor();
        Doctor currentDoctor= doctorService.getDoctor(3);

        List<Session> allSessions=sessionService.getTodaySessions();
        if (allSessions==null)
            return null;

        return allSessions.stream()
                .filter(s->s.getPatient().getDoctor().getDr_id() == currentDoctor.getDr_id()).map(Session::toSessionDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(value="/session/{pid}")
    public SessionDTO addSession(@PathVariable long pid, @Valid @RequestBody SessionDTO sessiondto) {
        //Doctor currentDoctor= doctorService.getCurrentlyLoggedInDoctor();
        Doctor currentDoctor= doctorService.getDoctor(3);
        Session session=sessiondto.toSession();
        Patient currentPatient=patientService.getPatient(currentDoctor,pid);
        session.setPatient(currentPatient);
        return sessionService.addSession(session).toSessionDTO();
    }

    @PutMapping(value="/session/{pid}")
    public SessionDTO updateSession(@PathVariable long pid, @Valid @RequestBody SessionDTO sessiondto) {
        //Doctor currentDoctor= doctorService.getCurrentlyLoggedInDoctor();
        Doctor currentDoctor= doctorService.getDoctor(3);
        Session session=sessiondto.toSession();
        Patient currentPatient=patientService.getPatient(currentDoctor,pid);
        session.setPatient(currentPatient);
        return sessionService.updateSession(session).toSessionDTO();
    }

    @RequestMapping(value = "/session/{sid}", method = RequestMethod.DELETE)
    public SessionDTO deleteSession(@PathVariable long sid) {
        return sessionService.deleteSession(sid).toSessionDTO();
    }
}
