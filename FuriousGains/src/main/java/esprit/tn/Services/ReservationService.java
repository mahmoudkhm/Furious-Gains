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
        // Récupérer le nombre de participants pour l'événement associé à la réservation
        int nbParticipants = getNbParticipantsPourEvenement(reservation.getId_event());

        // Récupérer le nombre de réservations pour l'événement associé à la réservation
        int nbReservations = getNbReservationsPourEvenement(reservation.getId_event());

        // Vérifier si le nombre de réservations + le nombre de places de la nouvelle réservation dépasse le nombre de participants autorisés
        if (nbReservations + reservation.getNb_place() <= nbParticipants) {
            // Effectuer l'ajout de la réservation
            String req = "INSERT INTO reservation (nb_place, status_Res, id_event, id_client) VALUES (?, ?, ?, ?)";
            try (PreparedStatement st = cnx.prepareStatement(req)) {
                st.setInt(1, reservation.getNb_place());
                st.setString(2, reservation.getStatus_Res());
                st.setInt(3, reservation.getId_event());
                st.setInt(4, reservation.getId_client());
                st.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Réservation ajoutée avec succès !");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Échec de l'ajout de la réservation !");
                alert.showAndWait();
            }
        } else {
            // Afficher un message d'erreur si le nombre de réservations dépasse le nombre de participants autorisés
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le nombre de réservations dépasse le nombre de participants autorisés pour cet événement !");
            alert.showAndWait();
        }
    }

    private int getNbReservationsPourEvenement(int idEvent) {
        int nbReservations = 0;
        String req = "SELECT COUNT(*) AS nb_reservations FROM reservation WHERE id_event = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, idEvent);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nbReservations = rs.getInt("nb_reservations");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nbReservations;
    }

    private int getNbParticipantsPourEvenement(int idEvent) {
        int nbParticipants = 0;
        String req = "SELECT nb_participation FROM evenement WHERE id_event = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, idEvent);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nbParticipants = rs.getInt("nb_participation");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nbParticipants;

    }


    @Override
    public void modifier(Reservation reservation) {
        // Récupérer le nombre de réservations actuel pour l'événement
        int nbReservations = getNbReservationsPourEvenement(reservation.getId_event());

        // Vérifier si le nombre de places à modifier ne dépasse pas le nombre de réservations actuel
        if (reservation.getNb_place() <= nbReservations) {
            String req = "UPDATE reservation set  nb_place =? , status_Res =?, id_event =?, id_client =? where id_Res= ?";
            try (PreparedStatement rs = cnx.prepareStatement(req)) {
                rs.setInt(1, reservation.getNb_place());
                rs.setString(2, reservation.getStatus_Res());
                rs.setInt(3, reservation.getId_event());
                rs.setInt(4, reservation.getId_client());
                rs.setInt(5, reservation.getId_Res());

                rs.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Réservation modifiée avec succès !");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Échec de la modification de la réservation !");
                alert.showAndWait();
            }
        } else {
            // Afficher un message d'erreur si le nombre de places à modifier dépasse le nombre de réservations
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le nombre de places ne peut pas être supérieur au nombre de réservations !");
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
            int rowCount = rs.executeUpdate();
            if (rowCount > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Réservation supprimée !");
                alert.showAndWait();
            } else {
                // Vérifier si l'ID de réservation existe
                String verifReq = "SELECT * FROM reservation WHERE id_Res = ?";
                PreparedStatement verifPs = cnx.prepareStatement(verifReq);
                verifPs.setInt(1, id);
                ResultSet verifRs = verifPs.executeQuery();
                if (!verifRs.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("L'ID de réservation n'existe pas !");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Échec de suppression !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Échec de suppression !");
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
                reservation.setId_event(rs.getInt("Id_event"));
                reservation.setId_client(rs.getInt("Id_client"));

                reservations.add(reservation);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;

    }
    public Reservation getOneByCin(int id) {
        Reservation r = null;
        String req = "SELECT * FROM reservation WHERE id_Res = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    r = new Reservation(
                            rs.getInt("Nb_place"),
                            rs.getString("Status_Res"),
                            rs.getInt("id_event"),
                            rs.getInt("id_client"));
                           // rs.getString("prenom");

                    //);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

}
