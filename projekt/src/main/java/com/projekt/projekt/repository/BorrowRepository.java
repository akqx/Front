package com.projekt.projekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.projekt.model.Borrow;

public interface BorrowRepository extends  JpaRepository<Borrow, Long> {

}
