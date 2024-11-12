import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PedidoImpl extends UnicastRemoteObject implements Pedido {
    private String id;
    private String status;

    public PedidoImpl(String id) throws RemoteException {
        this.id = id;
        this.status = "pendente";
    }

    public String consultar_status() throws RemoteException {
        return status;
    }

    public void atualizar_status(String novo_status) throws RemoteException {
        this.status = novo_status;
    }
}