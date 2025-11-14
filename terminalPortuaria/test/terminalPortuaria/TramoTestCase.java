package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscador.BuscadorDeTrayectosStrategy;
import buscador.BuscadorPorFechaDeLlegada;
import empresaMaritima.TerminalGestionada;
import empresaMaritima.Tramo;

import java.util.Date;

class TramoTestCase {

    private Tramo tramo;
    private Date fecha;

    @BeforeEach
    void setUp() {
    
        long dosHorasEnMilis = 2L * 60 * 60 * 1000;
        fecha = new Date(dosHorasEnMilis);
        
        BuscadorDeTrayectosStrategy motorDeBusqueda = mock(BuscadorPorFechaDeLlegada.class);
        TerminalGestionada buenosAires = new TerminalGestionada(20.0, "Puerto Buenos Aires", motorDeBusqueda);
        TerminalGestionada rio = new TerminalGestionada(120.0, "Rio de janeiro", motorDeBusqueda);
        tramo = new Tramo(5000.0, fecha, buenosAires, rio);
    }

    @Test
    void testGetters() {
    	
        BuscadorDeTrayectosStrategy motorDeBusqueda = mock(BuscadorPorFechaDeLlegada.class);
        TerminalGestionada buenosAires = new TerminalGestionada(20.0, "Puerto Buenos Aires", motorDeBusqueda);
        TerminalGestionada rio = new TerminalGestionada(120.0, "Rio de janeiro", motorDeBusqueda);
        tramo = new Tramo(5000.0, fecha, buenosAires, rio); 
        
        
        assertEquals(5000.0, tramo.getPrecio());
        assertEquals(fecha, tramo.getTiempo());
        assertEquals(buenosAires, tramo.getTerminalOrigen());
        assertEquals(rio, tramo.getTerminalDestino());
    }

    @Test
    void testSetters() {
        tramo.setPrecio(7500.0);
        assertEquals(7500.0, tramo.getPrecio());

        Date nuevaFecha = new Date(4L * 60 * 60 * 1000); // 4 horas
        tramo.setTiempo(nuevaFecha);
        assertEquals(nuevaFecha, tramo.getTiempo());
    }

    @Test
    void testGetDuracionEnHoras() {
     
        long horas = tramo.getDuracionEnHoras();
        assertEquals(2, horas);
    }
}
