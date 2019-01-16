package com.projekt.projekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projekt.projekt.model.Book;

public interface BooksbycategoryRepository extends JpaRepository<Book, Long> {

}
