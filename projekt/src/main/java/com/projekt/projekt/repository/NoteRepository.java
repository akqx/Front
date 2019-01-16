package com.projekt.projekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.projekt.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
