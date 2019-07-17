package com.tw.apistackbase.entity;

import org.junit.Before;
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

    @Autowired
    private ProcuratorateRepo procuratorateRepo;


    private Procuratorate procuratorate;

    @Before
    public void setUp() throws Exception {
        procuratorate = new Procuratorate();
        procuratorate.setName("p");
    }

    @Test
    public void should_throw_exception_when_save_case_without_any_fields() {
        CriminalCase criminalCase = new CriminalCase();

        assertThrows(Exception.class, () ->
                criminalCaseRepo.saveAndFlush(criminalCase));
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
        criminalCase.setProcuratorate(procuratorate);
        criminalCase1.setProcuratorate(procuratorate);
        criminalCase2.setProcuratorate(procuratorate);
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
        criminalCase.setProcuratorate(procuratorate);
        criminalCase1.setProcuratorate(procuratorate);
        criminalCase2.setProcuratorate(procuratorate);
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
        criminalCase.setProcuratorate(procuratorate);

        criminalCaseRepo.saveAndFlush(criminalCase);

        ArrayList<CriminalElements> criminalElementsList = new ArrayList<>(criminalElementRepo.findAll());
        assertEquals(1, criminalElementsList.size());
        assertSame(1, new ArrayList<>(criminalCaseRepo.findAll()).size());
        assertEquals("objective", criminalElementsList.get(0).getObjectiveElementDescription());
    }

    @Test
    public void should_return_procuratorate_with_criminal_element_when_save_criminal_with_procuratorate() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setTime((long) 1);
        criminalCase.setName("name");
        criminalCase.setProcuratorate(procuratorate);

        criminalCaseRepo.saveAndFlush(criminalCase);

        ArrayList<Procuratorate> procuratorates = new ArrayList<>(this.procuratorateRepo.findAll());
        assertEquals(1, procuratorates.size());
        assertSame(1, new ArrayList<>(criminalCaseRepo.findAll()).size());
        assertEquals("p", procuratorates.get(0).getName());
    }
}