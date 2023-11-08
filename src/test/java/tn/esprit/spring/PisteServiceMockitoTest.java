package tn.esprit.spring;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PisteServiceMockitoTest {

    @Mock
    IPisteRepository pisteRepository;

    @InjectMocks
    PisteServicesImpl pisteServices;

    Piste piste = new Piste();
    List<Piste> mockPistes = new ArrayList<Piste>(){
        {
            Piste p1 = new Piste();
            p1.setNumPiste(1L);
            add(p1);
            Piste p2 = new Piste();
            p2.setNumPiste(2L);
            add(p2);
        }
    };


    @Test
    @Order(1)
    public void testRetrievePiste() {
        Mockito.when(pisteRepository.findById(piste.getNumPiste())).thenReturn(Optional.of(piste));
        Piste piste1 = pisteServices.retrievePiste(piste.getNumPiste());
        assertNotNull(piste1);
        verify(pisteRepository, times(1)).findById(piste.getNumPiste());
    }

    @Test
    @Order(2)
    public void testRetrieveAllPistes(){
        Mockito.when(pisteRepository.findAll()).thenReturn(mockPistes);
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        assertEquals(mockPistes, pistes);
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    @Order(3)
    public void testAddPiste(){
        Mockito.when(pisteRepository.save(piste)).thenReturn(piste);
        Piste piste1 = pisteServices.addPiste(piste);
        assertEquals(piste1, piste);
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    @Order(4)
    public void testRemovePiste(){
        Mockito.doNothing().when(pisteRepository).deleteById(piste.getNumPiste());
        pisteServices.removePiste(piste.getNumPiste());
        Piste piste2 = pisteServices.retrievePiste(piste.getNumPiste());
        assertNull(piste2);
        verify(pisteRepository, times(1)).deleteById(piste.getNumPiste());
    }



}
