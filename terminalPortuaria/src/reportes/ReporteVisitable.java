package reportes;

public interface ReporteVisitable {
	
	void accept(ReporteVisitor visitor);
	
}
