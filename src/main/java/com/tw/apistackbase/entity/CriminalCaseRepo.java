package com.tw.apistackbase.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriminalCaseRepo extends JpaRepository<CriminalCase, String> {

    List<CriminalCase> findAllByOrderByTimeDesc();

    List<CriminalCase> findAllByName(String name);

}
