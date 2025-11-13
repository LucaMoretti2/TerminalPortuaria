package containers;

import java.time.Duration;
import java.time.LocalDateTime;
/*
 Representa un contenedor Reefer 
 Extiende la clase abstracta Container e incorpora información específicav del uso eléctrico:
 
- consumoPorHora: consumo energético del reefer por hora de conexión
- inicioDeConexion / finConexion: período durante el cual estuvo conectado
- getHorasConectado(): calcula la cantidad total de horas de uso eléctrico 
 Además, mantiene un Bill of Lading asociado, ya que los reefers suelen
 transportar un único tipo de carga sensible a temperatura.
 
 Define su tipo como "Reefer" y utiliza Duration para calcular tiempos
 de conexión de manera precisa.
 */
public class Reefer extends Container {

	double consumoPorHora;
	LocalDateTime inicioDeConexion;
	LocalDateTime finConexion;
	double horasConectado;
	BillOfLading bl;
	
	
	public Reefer(int alto, int ancho, int largo, double pesoTotal, String idContainer, double consumoPorHora, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeRetiro, fechaDeRetiro);
		this.consumoPorHora = consumoPorHora;
		this.tipo = "Reefer";
	
	}
	
	public double getConsumoPorHora() {
		return consumoPorHora;
	}
	
	public void conectar(LocalDateTime inicio) {
        this.inicioDeConexion = inicio;
    }

    public void desconectar(LocalDateTime fin) {
        this.finConexion = fin;
    }
	
    public double getHorasConectado() {
        if (inicioDeConexion != null && finConexion != null) {
            Duration duracion = Duration.between(inicioDeConexion, finConexion);
            return duracion.toHours();
        }
        return horasConectado; 
    }
    
	public BillOfLading getBl() {
		return bl;
	}
}
