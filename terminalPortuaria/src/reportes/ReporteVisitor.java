package reportes;

import containers.Container;
import empresaMaritima.Buque;

// Define operaciones 'visit' para cada tipo de elemento que puede ser recorrido
// durante la creación del reporte (Buque y Container)

public interface ReporteVisitor {
    void visit(Buque buque);
    void visit(Container container);
}