package com.projekt.projekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projekt.projekt.model.Bookcopies;

public interface BookcopiesRepository extends JpaRepository<Bookcopies, Long> {

	Bookcopies findByidbook(int i);

	Bookcopies save(List<Bookcopies> bookcopiesList);

	//@Modifying
	//@Query("UPDATE library.bookcopies SET copiesnumber = copiesnumber + :one WHERE idbook= :two;")
	//int updateBookCopiesDifference(@Param("one") int copiesnumberDiffrence, @Param("two") int idbook);
}
