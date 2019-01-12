package com.projekt.projekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.projekt.model.Category;

public interface CategoryRepository extends  JpaRepository<Category, Long> {

}
