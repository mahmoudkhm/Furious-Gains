package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Evenement;
import esprit.tn.Models.Reservation;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements InterfaceFuriousGains <Reservation> {
    private Connection cnx;
    public ReservationService() {
        cnx = MyConnexion.getInstance().getCnx();
    }


    @Override
    public void ajouter(Reservation reservation) {
        String req = "INSERT INTO reservation (nb_place, status_Res,id_event, id_client) VALUES ('" + reservation.getNb_place() + "', '" + reservation.getStatus_Res() + "', '" + reservation.getId_event() + "', '" + reservation.getId_client() +  "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("reservation ajouter avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec d'ajout!");
            alert.showAndWait();
        }
    }



    @Override
    public void modifier(Reservation reservation) {
        String req = "UPDATE reservation set  nb_place =? , status_Res =?, id_event =?, id_client =? where id_Res= ?";
        PreparedStatement rs = null;
        try {
            rs = cnx.prepareStatement(req);
            rs.setInt(1, reservation.getNb_place());
            rs.setString(2, reservation.getStatus_Res());
            rs.setInt(3, reservation.getId_event());
            rs.setInt(4, reservation.getId_client());
            rs.setInt(5, reservation.getId_Res());



            rs.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("reservation modifier avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de modification!");
            alert.showAndWait();
        }


    }




    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM reservation where id_Res = ?";
        PreparedStatement rs = null;
        try {
            rs = cnx.prepareStatement(req);
            rs.setInt(1, id);
            rs.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("reservation asupprimer!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de suppression!");
            alert.showAndWait();
        }

    }

    @Override
    public List<Reservation> affichage() {
        List<Reservation> reservations = new ArrayList<>();
        String req = "Select * FROM reservation";
        try {
            Statement es = cnx.createStatement();
            ResultSet rs = es.executeQuery(req);
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId_Res(rs.getInt("id_Res"));
                reservation.setNb_place(rs.getInt("Nb_place"));
                reservation.setStatus_Res(rs.getString("Status_Res"));
                reservation.setId_client(rs.getInt("Id_client"));

                reservations.add(reservation);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;

    }

}
