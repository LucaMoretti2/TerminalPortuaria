package containers;

import java.time.Duration;
import java.time.LocalDateTime;

 //Extiende la clase abstracta Container e incorpora información específicav del uso eléctrico:

public class Reefer extends Container {

	double consumoPorHora;
	LocalDateTime inicioDeConexion;
	LocalDateTime finConexion;
	double horasConectado;
	BillOfLading bl; 
	
	
	public Reefer(int alto, int ancho, int largo, double pesoTotal, String idContainer, double consumoPorHora, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeRetiro, fechaDeRetiro);
		this.consumoPorHora = consumoPorHora;
	//Aca define el tipo
		this.tipo = "Reefer";
	
	}
//consumo energético del reefer por hora de conexión
	public double getConsumoPorHora() {
		return consumoPorHora;
	}
	
	public void conectar(LocalDateTime inicio) {
        this.inicioDeConexion = inicio;
    }

    public void desconectar(LocalDateTime fin) {
        this.finConexion = fin;
    }
	
   //calcula la cantidad total de horas de uso eléctrico 
    public double getHorasConectado() {
        if (inicioDeConexion != null && finConexion != null) {
            Duration duracion = Duration.between(inicioDeConexion, finConexion);
            return duracion.toHours();
        }
        return horasConectado; 
    }
  // mantiene un Bill of Lading asociado, ya que los reefers suelen
  //transportar un único tipo de carga sensible a temperatura.
	public BillOfLading getBl() {
		return bl;
	}
}
