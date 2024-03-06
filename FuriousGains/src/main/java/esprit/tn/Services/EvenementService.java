package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Evenement;
import esprit.tn.Models.Reservation;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements InterfaceFuriousGains <Evenement > {
    private Connection cnx;

    public EvenementService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement evenement) {
        String req = "INSERT INTO Evenement (nom_event, lieu_event, prix_event, nb_participation, date_event, heure_event, description) VALUES ('" + evenement.getNom_event() + "', '" + evenement.getLieu_event() + "', '" + evenement.getPrix_event() + "', '" + evenement.getNb_participation() + "', '" + new java.sql.Date(evenement.getDate_event().getTime())
                + "', '" + evenement.getHeure_event() +    "', '" + evenement.getDescription() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("evenement ajouter avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec d'ajout!");
            alert.showAndWait();
        }
    }

    @Override
    public void modifier(Evenement evenement) {
        String req = "UPDATE Evenement set nom_event = ?, lieu_event =? , prix_event =?, nb_participation =?, date_event =?, heure_event=?,description =? where id_event= ?";
        PreparedStatement es = null;
        try {
            es = cnx.prepareStatement(req);
            es.setString(1, evenement.getNom_event());
            es.setString(2, evenement.getLieu_event());
            es.setFloat(3, evenement.getPrix_event());
            es.setInt(4, evenement.getNb_participation());
            es.setDate(5, new java.sql.Date(evenement.getDate_event().getTime()));

            es.setString(6, evenement.getHeure_event());

            es.setString(7, evenement.getDescription());
            es.setInt(8, evenement.getId_event());

            es.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Evenement  modifier avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de modifier!");
            alert.showAndWait();
        }


    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM Evenement where id_event = ?";
        PreparedStatement es = null;
        try {
            es = cnx.prepareStatement(req);
            es.setInt(1, id);
            int rowCount = es.executeUpdate();
            if (rowCount > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Événement supprimé !");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("L'ID de l'événement n'existe pas !");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Échec de suppression !");
            alert.showAndWait();
        }
    }
    @Override
    public List<Evenement> affichage() {
        List<Evenement> evenements = new ArrayList<>();
        String req = "Select * FROM evenement";
        try {
            Statement es = cnx.createStatement();
            ResultSet rs = es.executeQuery(req);
            while (rs.next()) {
                Evenement evenement = new Evenement();
                evenement.setId_event(rs.getInt("id_event"));
                evenement.setNom_event(rs.getString("Nom_event"));
                evenement.setLieu_event(rs.getString("lieu_event"));
                evenement.setPrix_event(rs.getFloat("prix_event"));
                evenement.setNb_participation(rs.getInt("Nb_participation"));
                evenement.setDate_event(rs.getDate("Date_event"));
                evenement.setHeure_event(rs.getString("heure_event"));
                evenement.setDescription(rs.getString("Description"));
                evenements.add(evenement);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return evenements;


    }



    public Evenement getOneByCin(int id) {
        Evenement ev = null;
        String req = "SELECT * FROM evenement WHERE id_event = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, id);
            try (ResultSet es = stmt.executeQuery()) {
                if (es.next()) {
                    ev = new Evenement(
                            es.getInt("id_event"),
                            es.getString("nom_event"),
                            es.getString("lieu_event"),
                            es.getFloat("prix_event"),
                            es.getInt("nb_participation"),
                            es.getDate("date_event"),
                            es.getString("heure_event"),
                            es.getString("description"));


                    // rs.getString("prenom");

                    //);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ev;
    }
    public List<Evenement> verifierEmailMdp(String par) {
        List<Evenement> listevenement = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement WHERE id_event like ?";
            PreparedStatement statement = this.cnx.prepareStatement(requete);
            statement.setString(1, "%"+par+"%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Evenement evenement = new Evenement();
                    evenement.setId_event(rs.getInt("id_event"));
                    evenement.setNom_event(rs.getString("nom_event"));
                    evenement.setLieu_event(rs.getString("lieu_event"));
                    evenement.setPrix_event(rs.getFloat("lieu_event"));
                    evenement.setNb_participation(rs.getInt("nb_participation"));
                    evenement.setDate_event(rs.getDate("date_event"));
                    evenement.setHeure_event(rs.getString("heure_event"));
                    evenement.setDescription(rs.getString("description"));

                    listevenement.add(evenement);

                }
            }
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        return listevenement;
    }


}