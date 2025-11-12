package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empresaMaritima.Tramo;

import java.util.Date;

class TramoTestCase {

    private Tramo tramo;
    private Date fecha;

    @BeforeEach
    void setUp() {
    
        long dosHorasEnMilis = 2L * 60 * 60 * 1000;
        fecha = new Date(dosHorasEnMilis);

        tramo = new Tramo(5000.0, fecha, "Buenos Aires", "Brasil");
    }

    @Test
    void testGetters() {
        assertEquals(5000.0, tramo.getPrecio());
        assertEquals(fecha, tramo.getTiempo());
        assertEquals("Buenos Aires", tramo.getTerminalOrigen());
        assertEquals("Brasil", tramo.getTerminalDestino());
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
