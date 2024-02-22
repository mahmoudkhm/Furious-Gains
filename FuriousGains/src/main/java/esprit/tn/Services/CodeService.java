package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceUser;
import esprit.tn.Models.CodePromo;
import esprit.tn.Models.User;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CodeService implements InterfaceUser<CodePromo> {
    private Connection cnx;

    public CodeService() {
        cnx= MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(CodePromo codePromo){
        String req="INSERT INTO `codepromo`(`code`, `Montant_Reduction`, `Statut`, `Utilisations_Restantes`) " +
                "VALUES ('"+codePromo.getCode()+"','"+codePromo.getMontant_Reduction()+"','"+codePromo.getStatut()+"','"+codePromo.getUtilisations_Restantes()+"')";
        Statement st= null;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Code promo Added Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Code promo ajouter avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec d'ajout");
            alert.showAndWait();
        }
    }
    @Override
    public void utilisateurCanBeAded(CodePromo codePromo) {
    }

    @Override
    public void modifier(CodePromo CodePromo) {
        String req="UPDATE `codepromo` SET `code`=?,`Montant_Reduction`=?,`Statut`=?,`Utilisations_Restantes`=? WHERE id_code_promo=?";
        PreparedStatement ps = null;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1,CodePromo.getCode());
            ps.setInt(2,CodePromo.getMontant_Reduction());
            ps.setString(3,CodePromo.getStatut());
            ps.setInt(4,CodePromo.getUtilisations_Restantes());
            ps.setInt(5,CodePromo.getId_code_promo());

            ps.executeUpdate();
            System.out.println("code promo Updated Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Code promo ajouter avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de modifier");
            alert.showAndWait();        }
    }
    //modifier avec verification
    @Override
    public void modifier2(CodePromo codePromo) {
        String reqVerifier= "SELECT COUNT(*) FROM `codepromo` WHERE `id_code_promo` = ?";
        String req="UPDATE `codepromo` SET `code`=?,`Montant_Reduction`=?,`Statut`=?,`Utilisations_Restantes`=? WHERE id_code_promo=?";

        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,codePromo.getId_code_promo());
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
            if (check==0){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("code promo n'existe pas!!");
                alert.showAndWait();
                System.out.println("code promo n'existe pas!");
            }
            else {
                PreparedStatement ps2 = cnx.prepareStatement(req);
                ps2.setInt(1,codePromo.getCode());
                ps2.setInt(2,codePromo.getMontant_Reduction());
                ps2.setString(3,codePromo.getStatut());
                ps2.setInt(4,codePromo.getUtilisations_Restantes());
                ps2.setInt(5,codePromo.getId_code_promo());

                ps.executeUpdate();
                System.out.println("code promo Updated Successfully!");
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("succes");
                alert.setContentText("Code promo modifier avec succes!");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de modifier");
            alert.showAndWait();        }
    }

    @Override
    public void delete(int id) {
        String req ="DELETE FROM `codepromo` WHERE code=?";
        try {
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("codepromo deleted Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Code promo supprimer avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de supprimer");
            alert.showAndWait();        }

    }

    @Override
    public List afficher() {
        List<CodePromo> codePromos = new ArrayList<>();
        String req ="SELECT * FROM `codepromo`";
        try {
            Statement st =cnx.createStatement();
            ResultSet result =st.executeQuery(req);
            while (result.next()){
                CodePromo codePromo =new CodePromo();
                codePromo.setId_code_promo(result.getInt("id_code_promo"));
                codePromo.setCode(result.getInt("code"));
                codePromo.setmontant_Reduction(result.getInt("Montant_Reduction"));
                codePromo.setStatut(result.getString("Statut"));
                codePromo.setUtilisations_Restantes(result.getInt("Utilisations_Restantes"));
                codePromos.add(codePromo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codePromos;    }

    @Override
    public void delete2(int id) {
        String reqVerifier= "SELECT COUNT(*) FROM `codepromo` WHERE `code` = ?";
        String req ="DELETE FROM `codepromo` WHERE code=?";

        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,id);
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
            if (check==0){
                System.out.println("codepromo  n'existe pas!");
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("codepromo  n'existe pas!!!!");
                alert.showAndWait();

            }
            else {
                PreparedStatement ps2=cnx.prepareStatement(req);
                ps2.setInt(1,id);
                ps2.executeUpdate();
                System.out.println("codepromo deleted Successfully!");
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("succes");
                alert.setContentText("Code promo supprimer avec succes!");
                alert.showAndWait();}

        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec d'ajout");
            alert.showAndWait();        }

    }

    @Override
    public CodePromo getOneByCin(int cin) {
        return null;
    }
}
