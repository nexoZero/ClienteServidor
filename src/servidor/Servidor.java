package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Leonardo
 */
public class Servidor {
    private ServerSocket servidor;
    
    /**
     * Instanciar um socket tipo servidor na porta que foi passada como parametro 
     * @param porta  
     */
    public Servidor( int porta){
        try {
            this.servidor = new ServerSocket(porta);
            System.out.println("SEVIDOR CRIADO");
        } catch (IOException ex) {
            System.out.println("ERROR NA CRIAÇÃO DO SERVIDOR: \n" + ex.getMessage());
        }
    }
    
    /**
     * Espea uma solicitação de um cliente, quando um host se conectar ao servidor <br/>
     * será retornao um socket de comunicação entre o cliente e o servidor <br/>
     * caso haja algum problema ao estabelecer uma conecçaõ sera´retornado null
     * @return Socket cliente
     */
    public Socket NovaConexao(){
       Socket cliente = null;
        try {
            System.out.println("AGUARDANDO CONEXAO COM CLIENTE...");
            cliente = this.servidor.accept();
            System.out.println("CONEXAO COM UM CLIENTE ESTABELECIDA");
        } catch (IOException ex) {
            System.out.println("ERRO AO SE COMUNICAR COM CLIENTE: \n" + ex.getMessage());
        }
       return cliente;
    }
    
    /**
     * Irar fechar a conexao com o socket cliente que for passado como parametro <br/>
     * Caso nao seja possivel fechar a conexao sera exibido mensagem de error e <br/>
     * serar retornado um valor boolean:<br/>
     *      true - conexao encerrada com sucessor <br/>
     *      false - erro ao encerrar conexao 
     * @param cliente
     * @return boolean
     */
    public boolean closerCliente( Socket cliente){
            try {
                cliente.close();
                System.out.println("conexao com cliente finalizada");
                return true;
            } catch (IOException ex) {
                System.out.println("ERROR AO ENCERAR CONEXAO COM CLIENTE:\n" + ex.getMessage());
            }
        return false;
    }
    
    /**
     * Será tratado toda a comunicação cliente sevidor sendo feita troca das <br/>
     * mensagens necessarias. <br/>
     * este metodo recebe como paramentro o socket do cliente que solicitou o servidor
     * @param cliente 
     */
    public void comunicao( Socket cliente){
        try {
            System.out.println("CONEXAO CLINTE SERVIDOR INICIADA...");
            ObjectOutputStream saida = new ObjectOutputStream ( cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream ( cliente.getInputStream());

            String msg = entrada.readUTF();
            System.out.println("CLIENTE: " + msg);
            
            saida.writeUTF("Hello Word, cliente");
            saida.flush();
            
            entrada.close();
            saida.close();
        } catch (IOException ex) {
            System.out.println("ERRO NA COMUNICAO CLIENTE-SERVIDOR:\n" + ex.getMessage());
        }finally{
            closerCliente(cliente);
            System.out.println("CONEXAO CLINTE SERVIDOR ENCERRADA...");
        }
    }
    
}
