package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;
import empresaMaritima.Tramo;

import java.util.Date;
import java.util.List;

class CircuitoMaritimoTestCase {

    private CircuitoMaritimo circuito;
    private Tramo tramo1;
    private Tramo tramo2;
    private Tramo tramo3;
    private TerminalGestionada destinoMock;

    @BeforeEach
    void setUp() {
        circuito = new CircuitoMaritimo("Ruta Sur");
        tramo1 = new Tramo(1000.0, new Date(2L * 60 * 60 * 1000), "Buenos Aires", "Brasil"); 
        tramo2 = new Tramo(2000.0, new Date(3L * 60 * 60 * 1000), "Brasil", "Paraguay");       
        tramo3 = new Tramo(1500.0, new Date(1L * 60 * 60 * 1000), "Paraguay", "Peru");   

        destinoMock = new TerminalGestionada(5.0);
    }
    //Test 1: nombre y lista inicial
    @Test
    void testConstructorYGetters() {
        assertEquals("Ruta Sur", circuito.getNombre());
        assertTrue(circuito.getTramos().isEmpty());
    }

    // Test 2: agregar tramos
    @Test
    void testAgregarTramos() {
        circuito.agregarTramo(tramo1);
        circuito.agregarTramo(tramo2);

        List<Tramo> lista = circuito.getTramos();

        assertEquals(2, lista.size());
        assertTrue(lista.contains(tramo1));
        assertTrue(lista.contains(tramo2));
    }

    //Test 3: calcular tiempo total en horas
    @Test
    void testCalcularTiempoTotalHoras() {
        circuito.agregarTramo(tramo1);
        circuito.agregarTramo(tramo2);
        circuito.agregarTramo(tramo3);

        long total = circuito.calcularTiempoTotalHoras();
        assertEquals(6, total);
    }

    // Test 4: calcular precio total
    @Test
    void testCalcularPrecioTotal() {
        circuito.agregarTramo(tramo1);
        circuito.agregarTramo(tramo2);
        circuito.agregarTramo(tramo3);

        double total = circuito.calcularPrecioTotal();

        assertEquals(4500.0, total);
    }

    // Test 5: calcular duración hasta una terminal destino intermedia
    @Test
    void testCalcularDuracionHasta() {
        circuito.agregarTramo(tramo1);
        circuito.agregarTramo(tramo2);
        circuito.agregarTramo(tramo3);


        TerminalGestionada destino = new TerminalGestionada(0);
        long horasHastaDestino = circuito.calcularDuracionHasta(destino);

        assertTrue(horasHastaDestino >= 0);
    }

    // Test 6: verificar que una ruta vacía da totales en 0
    @Test
    void testCircuitoVacio() {
        assertEquals(0, circuito.calcularPrecioTotal());
        assertEquals(0, circuito.calcularTiempoTotalHoras());
    }
}