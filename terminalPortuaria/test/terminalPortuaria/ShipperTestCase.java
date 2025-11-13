package terminalPortuaria;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actores.Shipper;

class ShipperTestCase {

    private Shipper shipper;

    @BeforeEach
    void setUp() {
        shipper = new Shipper("Belen Arena",1);
    
    }

    @Test
    void testGetNombre() {
        assertEquals("Belen Arena", shipper.getNombre());
    }

    @Test
    void testGetIdCliente() {
        assertEquals(1, shipper.getIdCliente());
    }

   
}