package com.tw.apistackbase.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CriminalCaseRepoTest {

    @Autowired
    private CriminalCaseRepo criminalCaseRepo;

    @Autowired
    private CriminalElementRepo criminalElementRepo;

    @Test
    public void should_throw_exception_when_save_case_without_any_fields() {
        CriminalCase criminalCase = new CriminalCase();

        assertThrows(Exception.class, () ->
                criminalCaseRepo.saveAndFlush(criminalCase));
    }

    @Test
    public void should_return_criminal_case_when_save_case_successfully() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 1);
        criminalCase.setName("name");

        criminalCaseRepo.save(criminalCase);

        assertSame(1, new ArrayList<>(criminalCaseRepo.findAll()).size());
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
        criminalCaseRepo.save(criminalCase);
        criminalCaseRepo.save(criminalCase1);
        criminalCaseRepo.save(criminalCase2);

        List<CriminalCase> allByOrderByTimeDesc = criminalCaseRepo.findAllByOrderByTimeDesc();

        assertEquals("a", new ArrayList<>(allByOrderByTimeDesc).get(0).getName());
    }

    @Test
    public void should_return_case_when_find_by_name() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 1);
        criminalCase.setName("a");
        CriminalCase criminalCase1 = new CriminalCase();
        criminalCase1.setTime((long) 2);
        criminalCase1.setName("b");
        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setTime((long) 3);
        criminalCase2.setName("b");
        criminalCaseRepo.save(criminalCase);
        criminalCaseRepo.save(criminalCase1);
        criminalCaseRepo.save(criminalCase2);

        List<CriminalCase> b = criminalCaseRepo.findAllByName("b");

        assertEquals(2, new ArrayList<>(b).size());
        assertEquals("b", new ArrayList<>(b).get(0).getName());
        assertEquals("b", new ArrayList<>(b).get(1).getName());
    }

    @Test
    public void should_return_criminal_case_with_criminal_element_when_save_criminal_with_criminal_element() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 1);
        criminalCase.setName("name");
        CriminalElements criminalElements = new CriminalElements();
        criminalElements.setObjectiveElementDescription("objective");
        criminalElements.setSubjectiveElementDescription("subjective");
        criminalCase.setCriminalElements(criminalElements);

        criminalCaseRepo.saveAndFlush(criminalCase);

        ArrayList<CriminalElements> criminalElementsList = new ArrayList<>(criminalElementRepo.findAll());
        assertEquals(1, criminalElementsList.size());
        assertSame(1, new ArrayList<>(criminalCaseRepo.findAll()).size());
        assertEquals("objective", criminalElementsList.get(0).getObjectiveElementDescription());
    }
}