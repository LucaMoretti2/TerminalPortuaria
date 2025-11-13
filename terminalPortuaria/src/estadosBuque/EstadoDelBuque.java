package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
// Interfaz del patrón State que define el comportamiento que puede adoptar un buque según su estado operativo
//Cada estado concreto implementa esta interfaz para definir la lógica

public interface EstadoDelBuque {

	String nombreEstado();
	
	void actualizarPosicion(Buque buque, Double nuevalocalizacion, TerminalGestionada terminal); //se actualiza la posición del buque
	
	void informarExportacion(Buque buque); //se realizan operaciones de exportación
	
	void informarImportacion(Buque buque); //se realizan operaciones de importación
	
	void realizarPagos(Buque buque); //se ejecutan pagos asociados a la terminal

	
	
	
}
