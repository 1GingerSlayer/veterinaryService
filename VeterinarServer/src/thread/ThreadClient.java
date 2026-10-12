/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Veterinar;
import domain.KategorijaKlijenta;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Nina
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();

                // switch case ovaj dole
                Response response = handleRequest(request);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success); //stavljam da su mi data(odgovor) i exception null tj nema podataka jos uvek i nema greske
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KATEGORIJA_KLIJENTA:
                    ServerController.getInstance().addKategorijaKlijenta((KategorijaKlijenta) request.getData());
                    break;
                case Operation.DELETE_KATEGORIJA_KLIJENTA:
                    ServerController.getInstance().deleteKategorijaKlijenta((KategorijaKlijenta) request.getData());
                    break;
                case Operation.UPDATE_KATEGORIJA_KLIJENTA:
                    ServerController.getInstance().updateKategorijaKlijenta((KategorijaKlijenta) request.getData());
                    break;
                case Operation.GET_ALL_KATEGORIJA_KLIJENTA:
                    response.setData(ServerController.getInstance().getAllKategorijaKlijenta((KategorijaKlijenta) request.getData()));
                    break;
                case Operation.LOGIN:
                    Veterinar veterinar = (Veterinar) request.getData();
                    Veterinar f = ServerController.getInstance().login(veterinar);
                    response.setData(f);
                    break;
                case Operation.LOGOUT:
                    Veterinar ulogovani = (Veterinar) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) { //ako mi bilo sta od operacija pukne catchujem exception, u response mi se cuva sada Error (a ne success) i postavljam za odgovor exception po izboru
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
