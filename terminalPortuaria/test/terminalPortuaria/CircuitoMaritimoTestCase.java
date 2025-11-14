package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscador.BuscadorDeTrayectosStrategy;
import buscador.BuscadorPorFechaDeLlegada;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;
import empresaMaritima.Tramo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//No se utilizan mocks: todos los métodos operan sobre datos
// propios y no requieren verificar interacciones con colaboradores.
class CircuitoMaritimoTestCase {

    private CircuitoMaritimo circuito;
    private Tramo tramo1;
    private Tramo tramo2;
    private Tramo tramo3;
    private LocalDateTime llegada;
    private TerminalGestionada buenosAires ;
    private TerminalGestionada rio;
    private TerminalGestionada paraguay;
    private TerminalGestionada peru;
    private BuscadorDeTrayectosStrategy motorDeBusqueda;

    @BeforeEach
    void setUp() {
    	
    	motorDeBusqueda = mock(BuscadorPorFechaDeLlegada.class);
        buenosAires = new TerminalGestionada(20.0, "Puerto Buenos Aires", motorDeBusqueda);
        rio = new TerminalGestionada(120.0, "Rio de janeiro", motorDeBusqueda);
        paraguay = new TerminalGestionada(80.0, "Paraguay", motorDeBusqueda);
        peru = new TerminalGestionada(300.0, "Paraguay", motorDeBusqueda);
      
        circuito = new CircuitoMaritimo("Ruta Sur");
        tramo1 = new Tramo(1000.0, new Date(2L * 60 * 60 * 1000), buenosAires, rio); 
        tramo2 = new Tramo(2000.0, new Date(3L * 60 * 60 * 1000), rio, paraguay);       
        tramo3 = new Tramo(1500.0, new Date(1L * 60 * 60 * 1000), paraguay, peru);   
        
        llegada = LocalDateTime.of(2025, 11, 1, 8, 0);
        List<CircuitoMaritimo> lista = new ArrayList<>();
        lista.add(circuito);
        
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
        List<CircuitoMaritimo> lista = new ArrayList<>();
        lista.add(circuito);


        TerminalGestionada destino = new TerminalGestionada(0, "Retiro",new BuscadorPorFechaDeLlegada(lista,llegada));
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