package reportes;

import containers.Container;
import empresaMaritima.Buque;


public interface ReporteVisitor {
    void visit(Buque buque);
    void visit(Container container);
}