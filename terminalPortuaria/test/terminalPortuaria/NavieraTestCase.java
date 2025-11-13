package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import empresaMaritima.Buque;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.Naviera;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
//No delega comportamientos ni contiene lógica compleja, por lo que los tests utilizan objetos reales en lugar de mocks
class NavieraTestCase {

    private Naviera naviera;
    private Buque buqueMock;
    private CircuitoMaritimo circuitoMock;

    @BeforeEach
    void setUp() {
        naviera = new Naviera("Titanic");
        naviera.setFlota(new HashSet<>());
        naviera.setCircuitos(new HashSet<>());

        buqueMock = new Buque("Titanic", 10.5, null);
        circuitoMock = new CircuitoMaritimo("Circuito Pacifico");
    }
//el nombre de la naviera se inicializa correctamente
    @Test
    void testConstructorYNombre() {
        assertEquals("Titanic", naviera.nombre);
    }
//los buques se agregan correctamente a la flota
    @Test
    void testAgregarBuque() {
        naviera.addBuque(buqueMock);

        assertTrue(naviera.getFlota().contains(buqueMock));
        assertEquals(1, naviera.getFlota().size());
    }
//los circuitos se agregan correctamente
    @Test
    void testAgregarCircuito() {
        naviera.addCircuito(circuitoMock);

        assertTrue(naviera.getCircuitos().contains(circuitoMock));
        assertEquals(1, naviera.getCircuitos().size());
    }
}
