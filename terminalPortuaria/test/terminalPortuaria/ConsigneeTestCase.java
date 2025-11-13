package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actores.Consignee;
//Representa al importador
class ConsigneeTestCase {

    private Consignee consignee;

    @BeforeEach
    void setUp() {
        consignee = new Consignee("Marianela Carbone",2);
      
    }

    @Test
    void testGetNombre() {
        assertEquals("Marianela Carbone", consignee.getNombre());
    }

    @Test
    void testGetIdCliente() {
        assertEquals(2, consignee.getIdCliente());
    }

   
}