package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Outils_Ids;

public interface Membreoutilrepository extends JpaRepository<Membre_Outil, Membre_Outils_Ids> {
	@Query("select m from Membre_Outil m where auteur_id=:x")
	List<Membre_Outil> findoutilId(@Param("x") Long autId);

}
