package com.todopal.backend.api;

import com.todopal.backend.dto.UserProfileDto;
import com.todopal.backend.services.ProfileService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    private final ProfileService profileService;

    public UserProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{username}")
    public UserProfileDto getProfile(@PathVariable String username) {
        return this.profileService.getProfile(username);
    }
}
