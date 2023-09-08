import br.edu.umfg.TelaInicial;

import java.io.IOException;

public class FrontEnd {
    public static void main(String[] args) {
        try {
            TelaInicial.menuDeOpcoes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}