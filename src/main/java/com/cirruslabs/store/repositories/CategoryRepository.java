package com.cirruslabs.store.repositories;

import com.cirruslabs.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}