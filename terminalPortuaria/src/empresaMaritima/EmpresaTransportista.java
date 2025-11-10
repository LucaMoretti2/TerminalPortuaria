package empresaMaritima;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransportista {

    String nombreEmpresa;
    List<Camion> camiones = new ArrayList<>();
    List<Chofer> empleados = new ArrayList<>();

    public EmpresaTransportista(String nombreEmpresa){
        this.nombreEmpresa = nombreEmpresa;
    }

   public void addCamion(Camion camion){
         camiones.add(camion);
   }

   public void removeCamion(Camion camion){
        camiones.remove(camion);
   }

//se podria hacer algo de recibir carga, evaluar

}

