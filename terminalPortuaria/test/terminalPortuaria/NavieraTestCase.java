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

class NavieraTestCase {

    private Naviera naviera;
    private Buque buqueMock;
    private CircuitoMaritimo circuitoMock;
//Falta arreglar aca
    @BeforeEach
    void setUp() {
        naviera = new Naviera("Titanic");
        naviera.setFlota(new HashSet<>());
        naviera.setCircuitos(new HashSet<>());

        buqueMock = new Buque("Titanic", 10.5, null);
        circuitoMock = new CircuitoMaritimo("Circuito Pacifico");
    }

    @Test
    void testConstructorYNombre() {
        assertEquals("Titanic", naviera.nombre);
    }

    @Test
    void testAgregarBuque() {
        naviera.addBuque(buqueMock);

        assertTrue(naviera.getFlota().contains(buqueMock));
        assertEquals(1, naviera.getFlota().size());
    }

    @Test
    void testAgregarCircuito() {
        naviera.addCircuito(circuitoMock);

        assertTrue(naviera.getCircuitos().contains(circuitoMock));
        assertEquals(1, naviera.getCircuitos().size());
    }
}
