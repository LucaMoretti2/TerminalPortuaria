package empresaMaritima;

public class Chofer {
    String nombre;
    Camion camionAsignado;

    public Chofer(String nombre) {
        this.nombre = nombre;
    }

   public String getNombre(){
        return nombre;
    }

   public Camion getCamionAsignado(){
       return camionAsignado;
   }

    public void asignarCamion(Camion camion) { 
        this.camionAsignado = camion; 
    }

//aca el chofer podria entregar la carga
} 
