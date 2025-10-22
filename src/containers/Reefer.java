package containers;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reefer extends Container {

	double consumoPorHora;
	LocalDateTime inicioDeConexion;
	LocalDateTime finConexion;
	double horasConectado;
	BillOfLading bl;
	
	
	public Reefer(int alto, int ancho, int largo, double pesoTotal, String idContainer, double consumoPorHora, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeRetiro, fechaDeRetiro);
		this.consumoPorHora = consumoPorHora;
	
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
