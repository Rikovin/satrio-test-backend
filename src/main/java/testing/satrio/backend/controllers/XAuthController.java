package testing.satrio.backend.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import testing.satrio.backend.model.ERole;
import testing.satrio.backend.model.Role;
import testing.satrio.backend.model.User;
import testing.satrio.backend.payload.request.LoginRequest;
import testing.satrio.backend.payload.request.SignupRequest;
import testing.satrio.backend.payload.response.JwtResponse;
import testing.satrio.backend.payload.response.MessageResponse;
import testing.satrio.backend.repository.RoleRepository;
import testing.satrio.backend.repository.UserRepository;
import testing.satrio.backend.security.jwt.JwtUtils;
import testing.satrio.backend.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/xapi/auth")
public class XAuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
//        return ResponseEntity.ok(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return  ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "spv":
                        Role spvRole = roleRepository.findByName(ERole.ROLE_SPV)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(spvRole);
                        break;
                    case "teller":
                        Role tellerRole = roleRepository.findByName(ERole.ROLE_TELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(tellerRole);
                        break;
                    case "it":
                        Role itRole = roleRepository.findByName(ERole.ROLE_IT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(itRole);
                        break;
                    case "cs":
                        Role csRole = roleRepository.findByName(ERole.ROLE_CS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(csRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                }

            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
