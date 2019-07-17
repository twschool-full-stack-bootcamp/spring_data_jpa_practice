package com.tw.apistackbase.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CriminalCaseRepoTest {

    @Autowired
    private CriminalCaseRepo caseRepo;

    @Test
    public void should_throw_exception_when_save_case_without_any_fields() {
        CriminalCase criminalCase = new CriminalCase();

        assertThrows(Exception.class, () ->
                caseRepo.saveAndFlush(criminalCase));
    }

    @Test
    public void should_return_criminal_case_when_save_case_successfully() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 1);
        criminalCase.setName("name");

        caseRepo.save(criminalCase);

        assertSame(1, new ArrayList<>(caseRepo.findAll()).size());
    }

    @Test
    public void should_return_order_by_time_criminal_case_when_find_all_case_sort_by_time() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 5);
        criminalCase.setName("a");
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setTime((long) 4);
        criminalCase1.setName("b");
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setTime((long) 3);
        criminalCase2.setName("c");
        caseRepo.save(criminalCase);
        caseRepo.save(criminalCase1);
        caseRepo.save(criminalCase2);

        List<CriminalCase> allByOrderByTimeDesc = caseRepo.findAllByOrderByTimeDesc();

        assertEquals("a", new ArrayList<>(allByOrderByTimeDesc).get(0).getName());
    }

}