package empresaMaritima;

//Representa a un chofer perteneciente a una Empresa Transportista,
//responsable de conducir el camión asignado para entregar o retirar 
//contenedores en la terminal portuaria.
 
public class Chofer {
    String nombre;
    Camion camionAsignado;

    public Chofer(String nombre) {
        this.nombre = nombre;
    }

   public String getNombre(){
        return nombre;
    }
//consulta el vehículo que está usando
   public Camion getCamionAsignado(){
       return camionAsignado;
   }
// permite asociar un camión al chofer
    public void asignarCamion(Camion camion) { 
        this.camionAsignado = camion; 
    }

//aca el chofer podria entregar la carga
} 
