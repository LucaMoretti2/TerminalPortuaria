package reportes;

import empresaMaritima.TerminalGestionada;

public interface ReporteVisitor {
    void visitarTerminal(TerminalGestionada terminal);
}