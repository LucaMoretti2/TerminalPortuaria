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
        naviera.flota = new HashSet<>();
        naviera.circuitos = new HashSet<>();

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

        assertTrue(naviera.flota.contains(buqueMock));
        assertEquals(1, naviera.flota.size());
    }

    @Test
    void testAgregarCircuito() {
        naviera.addCircuito(circuitoMock);

        assertTrue(naviera.circuitos.contains(circuitoMock));
        assertEquals(1, naviera.circuitos.size());
    }
}
