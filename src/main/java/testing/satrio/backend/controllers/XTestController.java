package testing.satrio.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/xapi/test")
public class XTestController {
    @GetMapping("/all")
    public String allAccess() {
        return "All Access Public Content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('SPV') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content";
    }

    @GetMapping("/spv")
    @PreAuthorize("hasRole('SPV')")
    public String spvAccess() {
        return "spv Content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Access";
    }
}
