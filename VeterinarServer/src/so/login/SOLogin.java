/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Veterinar;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOLogin extends AbstractSO {

    Veterinar ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Veterinar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Veterinar!");
        }

        Veterinar f = (Veterinar) ado;

//        if(ServerController.getInstance().getUlogovaniVeterinari().size()>3){
//            throw new Exception("Vec je ulogovano 3 veterinara na sistem.");
//        }
        for (Veterinar veterinar : ServerController.getInstance().getUlogovaniVeterinari()) {
            if (veterinar.getUsername().equals(f.getUsername())) {
                throw new Exception("Ovaj veterinar je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Veterinar f = (Veterinar) ado;

        ArrayList<Veterinar> listaVeterinara
                = (ArrayList<Veterinar>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Veterinar veterinar : listaVeterinara) {
            if (veterinar.getUsername().equals(f.getUsername())
                    && veterinar.getPassword().equals(f.getPassword())) {
                ulogovani = veterinar;
                ServerController.getInstance().getUlogovaniVeterinari().add(veterinar);
                return;
            }
        }

        throw new Exception("Ne postoji veterinar sa tim kredencijalima.");

    }

    public Veterinar getUlogovani() {
        return ulogovani;
    }

}
