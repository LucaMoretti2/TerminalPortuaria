package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.EmpresaTransportista;
//No delega comportamiento en otras clases ni depende de servicios externos, por lo que no es necesario utilizar mocks.
class EmpresaTransportistaTestCase {

    private EmpresaTransportista empresa;
    private Camion camion1;
    private Camion camion2;

    @BeforeEach
    void setUp() {
        empresa = new EmpresaTransportista("TransMarítima");
        camion1 = new Camion("Herby", new Chofer("Lindsay Lohan"), empresa);
        camion2 = new Camion("Tito", new Chofer("Diego"), empresa);
    }
//los camiones se agregan correctamente a la lista
    @Test
    void testAgregarCamion() {
        empresa.addCamion(camion1);

        assertTrue(empresa.getCamiones().contains(camion1));
        assertEquals(1, empresa.getCamiones().size());
    }
//los camiones pueden eliminarse correctamente
    @Test
    void testEliminarCamion() {
        empresa.addCamion(camion1);
        empresa.addCamion(camion2);

        empresa.removeCamion(camion1);

        assertFalse(empresa.getCamiones().contains(camion1));
        assertEquals(1, empresa.getCamiones().size());
    }

    @Test
    void testNombreEmpresa() {
        assertEquals("TransMarítima", empresa.getNombreEmpresa());
    }
}