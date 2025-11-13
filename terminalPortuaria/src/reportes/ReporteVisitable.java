package reportes;
/*
 Define el método 'accept', que permite que el objeto delegue en el visitor la lógica
 correspondiente según su tipo ,se pueden agregar nuevos tipos de reportes sin modificar las clases de dominio.
*/

public interface ReporteVisitable {
	
	void accept(ReporteVisitor visitor);
	
}
