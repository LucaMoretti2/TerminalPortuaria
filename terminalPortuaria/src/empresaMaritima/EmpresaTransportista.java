package empresaMaritima;

import java.util.ArrayList;
import java.util.List;
//Representa una empresa transportista dedicada al traslado terrestre de
//contenedores hacia o desde la terminal portuaria.
public class EmpresaTransportista {

    private String nombreEmpresa;
    private List<Camion> camiones = new ArrayList<>();
    private List<Chofer> empleados = new ArrayList<>();

    public EmpresaTransportista(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void addCamion(Camion camion) {
        camiones.add(camion);
    }

    public void removeCamion(Camion camion) {
        camiones.remove(camion);
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public List<Chofer> getEmpleados() {
        return empleados;
    }
    
}
