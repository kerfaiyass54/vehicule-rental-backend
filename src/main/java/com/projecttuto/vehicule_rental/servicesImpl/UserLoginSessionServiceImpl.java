package com.projecttuto.vehicule_rental.servicesImpl;

import com.projecttuto.vehicule_rental.DTO.SessionDTO;
import com.projecttuto.vehicule_rental.DTO.UserLoginDataDTO;
import com.projecttuto.vehicule_rental.entities.UserLoginSession;
import com.projecttuto.vehicule_rental.records.AiBehaviorRequest;
import com.projecttuto.vehicule_rental.records.AiResult;
import com.projecttuto.vehicule_rental.records.GeoLocation;
import com.projecttuto.vehicule_rental.repositories.UserLoginSessionRepository;
import com.projecttuto.vehicule_rental.services.UserLoginSessionService;
import com.projecttuto.vehicule_rental.utils.JwtUtils;
import com.projecttuto.vehicule_rental.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



@Service
public class UserLoginSessionServiceImpl implements UserLoginSessionService {

    private final UserLoginSessionRepository userLoginSessionRepository;
    private final JwtUtils jwtUtils;
    private final GeoIpService geoIpService;
    private final BehaviorFeatureService behaviorFeatureService;
    private final AiClient aiClient;

    public UserLoginSessionServiceImpl(UserLoginSessionRepository repository,JwtUtils jwtUtils,GeoIpService geoIpService,BehaviorFeatureService behaviorFeatureService,
                                       AiClient aiClient) {
        this.userLoginSessionRepository = repository;
        this.jwtUtils = jwtUtils;
        this.geoIpService = geoIpService;
        this.behaviorFeatureService = behaviorFeatureService;
        this.aiClient = aiClient;
    }

    public SessionDTO mapToDTO(UserLoginSession userLoginSession) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setEmail(userLoginSession.getEmail());
        sessionDTO.setUsername(userLoginSession.getUsername());
        sessionDTO.setId(userLoginSession.getId());
        sessionDTO.setUserId(userLoginSession.getUserId());
        sessionDTO.setSessionStart(userLoginSession.getSessionStart());
        return sessionDTO;
    }

    @Override
    public void saveSession(HttpServletRequest request){
        Jwt jwt = jwtUtils.getJwt();
        String ip = RequestUtils.getClientIp(request);
        String ua = RequestUtils.getUserAgent(request);
        GeoLocation geo = geoIpService.resolve(ip);
        UserLoginSession session = new UserLoginSession();
        session.setUserId(jwtUtils.userId());
        session.setUsername(jwtUtils.username());
        session.setEmail(jwtUtils.email());
        session.setSessionId(jwtUtils.sessionId());
        session.setSessionStart(Instant.now());
        session.setIpAddress(ip);
        session.setUserAgent(ua);
        session.setCountry(geo.country());
        session.setCity(geo.city());
        List<UserLoginSession> history =
                userLoginSessionRepository.findUserLoginSessionsByUserId(session.getUserId());

        if (!history.isEmpty()) {
            AiBehaviorRequest features =
                    behaviorFeatureService.buildFeatures(session, history);

            AiResult ai = aiClient.analyze(features);

            session.setRiskScore(ai.riskScore());
            session.setSuspicious(ai.suspicious());
            session.setSuspiciousReason(
                    ai.suspicious() ? "AI anomaly detected" : null
            );
        } else {
            session.setRiskScore(0.0);
            session.setSuspicious(false);
        }
        userLoginSessionRepository.save(session);
    }

    @Override
    public List<SessionDTO> findAllUserLoginSessions(){
        return ((List<UserLoginSession>) userLoginSessionRepository.findAll()).stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<SessionDTO> findAllUserLoginSessionsByEmail(String email){
        return userLoginSessionRepository.findUserLoginSessionByEmail(email).stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<SessionDTO> findAllUserLoginSessionsByLoginDate(Instant date, String id){
        return userLoginSessionRepository.findUserLoginSessionBySessionStartAndUserId(date,id).stream().map(this::mapToDTO).toList();
    }

    @Override
    public Page<SessionDTO> findAllUseLoginSessionsByEmailPage(String email, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return userLoginSessionRepository.findUserLoginSessionByEmail(email,pageable).map(this::mapToDTO);
    }


}
