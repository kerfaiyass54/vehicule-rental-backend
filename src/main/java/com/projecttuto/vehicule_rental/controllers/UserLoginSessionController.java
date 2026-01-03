package com.projecttuto.vehicule_rental.controllers;


import com.projecttuto.vehicule_rental.DTO.SessionDTO;
import com.projecttuto.vehicule_rental.services.UserLoginSessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/sessions")
@CrossOrigin("*")
public class UserLoginSessionController {

    private final UserLoginSessionService service;
    public UserLoginSessionController(UserLoginSessionService service) {
        this.service = service;
    }


    @PostMapping("/save")
    public ResponseEntity<Void> save(HttpServletRequest request) {
        service.saveSession(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-date/{date}/{id}")
    public ResponseEntity<List<SessionDTO>> findAllUserLoginSessionsByLoginDate(@PathVariable Instant date, @PathVariable String id){
        List<SessionDTO> sessions = service.findAllUserLoginSessionsByLoginDate(date,id);
        return ResponseEntity.ok().body(sessions);
    }

    @GetMapping("/")
    public ResponseEntity<List<SessionDTO>> findAllUserLoginSessions(){
        List<SessionDTO> sessions = service.findAllUserLoginSessions();
        return ResponseEntity.ok().body(sessions);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<List<SessionDTO>> findAllUserLoginSessionsByLoginDate(@PathVariable String email){
        List<SessionDTO> sessions = service.findAllUserLoginSessionsByEmail(email);
        return ResponseEntity.ok().body(sessions);
    }

    @GetMapping("/list/sessions")
    public ResponseEntity<Page<SessionDTO>> findAllUserLoginSessionsByEmail(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size,
                                                                            @RequestParam(required = true) String email){
        Page<SessionDTO> sessionList = service.findAllUseLoginSessionsByEmailPage(email,page,size);
        if (sessionList != null)  {
            return  ResponseEntity.ok().body(sessionList);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



}
