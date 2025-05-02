package com.cirruslabs.store.repositories;

import com.cirruslabs.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}