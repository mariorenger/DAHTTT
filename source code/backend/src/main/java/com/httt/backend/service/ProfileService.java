package com.httt.backend.service;

import com.httt.backend.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile createProfile(Profile profile);

    List<Profile> getAllProfiles();
}
