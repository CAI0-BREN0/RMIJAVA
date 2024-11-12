import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pedido extends Remote {
    String consultar_status() throws RemoteException;
    void atualizar_status(String novo_status) throws RemoteException;
}