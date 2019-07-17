package com.tw.apistackbase.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ProcuratorateRepoTest {

    @Autowired
    private ProcuratorateRepo procuratorateRepo;

    @Autowired
    private ProsecutorRepo prosecutorRepo;

    @Test
    public void should_can_save_when_save_with_prosecutor() {
        Procuratorate procuratorate = new Procuratorate();
        Prosecutor prosecutorA = new Prosecutor();
        prosecutorA.setName("A");
        Prosecutor prosecutorB = new Prosecutor();
        prosecutorB.setName("B");
        List<Prosecutor> prosecutors = Arrays.asList(prosecutorA, prosecutorB);
        procuratorate.setProsecutors(prosecutors);
        procuratorate.setName("p");

        this.procuratorateRepo.save(procuratorate);

        assertEquals(1, this.procuratorateRepo.findAll().size());
        assertEquals(2, this.prosecutorRepo.findAll().size());
        Procuratorate result = this.procuratorateRepo.findAll().get(0);
        assertEquals("p", result.getName());
        assertEquals(2, result.getProsecutors().size());
        assertEquals("A", result.getProsecutors().get(0).getName());
        assertEquals("B", result.getProsecutors().get(1).getName());
    }
}