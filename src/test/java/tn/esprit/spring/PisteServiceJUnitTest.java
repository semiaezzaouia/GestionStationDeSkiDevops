package tn.esprit.spring;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PisteServiceJUnitTest {

    @Autowired
    IPisteServices pisteServices;

    @Test
    @Order(1)
    public void testRetrieveAllPistes(){
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        assertEquals(0, pistes.size());
    }

    @Test
    @Order(2)
    public void testAddPiste(){
        Piste p1 = new Piste();
        pisteServices.addPiste(p1);
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        assertTrue(pistes.contains(p1), "La liste des pistes devrait contenir p1");
    }

    @Test
    @Order(3)
    public void testRemovePiste(){
        Piste p2 = new Piste();
        p2.setNumPiste(1L);
        pisteServices.addPiste(p2);
        pisteServices.removePiste(p2.getNumPiste());
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        assertFalse(pistes.contains(p2), "La liste des pistes ne devrait pas contenir la piste supprimée");
    }

    @Test
    @Order(4)
    public void testRetrievePiste (){
        Piste p3 = new Piste();
        p3.setNumPiste(2L);
        pisteServices.addPiste(p3);
        Piste retrievedPiste = pisteServices.retrievePiste(p3.getNumPiste());
        assertEquals(p3, retrievedPiste, "La piste récupérée devrait être la même que celle ajoutée");
    }

}
