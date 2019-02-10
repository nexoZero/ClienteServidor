package principal;

import java.net.Socket;
import servidor.Servidor;

/**
 *
 * @author Leonardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor servidor = new Servidor(5555);
        Socket cliente = servidor.NovaConexao();
    }
    
}
