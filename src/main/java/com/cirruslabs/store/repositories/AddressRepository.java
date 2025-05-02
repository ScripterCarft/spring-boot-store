package com.cirruslabs.store.repositories;

import com.cirruslabs.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}