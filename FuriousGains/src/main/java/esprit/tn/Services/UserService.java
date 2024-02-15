package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Interfaces.InterfaceUser;
import esprit.tn.Models.User;
import esprit.tn.Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements InterfaceUser<User> {

    private Connection cnx;

    public UserService(){
        cnx= MyConnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(User user) {
        String req="INSERT INTO `user`( `cin`, `nom`, `prenom`, `num_tel`, `adresse`, `email`, `password`) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps =cnx.prepareStatement(req);
            ps.setInt(1,user.getCin());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getPrenom());
            ps.setInt(4,user.getNum_tel());
            ps.setString(5, user.getAdresse());
            ps.setString(6, user.getEmail());
            ps.setString(7,user.getPassword());
            ps.executeUpdate();
            System.out.println("User Added Successfully!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 /*   @Override
    public void ajouter2(User user) {


    }*/

    @Override
    public void utilisateurCanBeAded(User user) {
        String reqVerifier= "SELECT COUNT(*) FROM `user` WHERE `cin` = ?";
        String req="INSERT INTO `user`( `cin`, `nom`, `prenom`, `num_tel`, `adresse`, `email`, `password`) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,user.getCin());
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
              if (check==0){
                  PreparedStatement ps2=cnx.prepareStatement(req);
                  ps2.setInt(1,user.getCin());
                  ps2.setString(2, user.getNom());
                  ps2.setString(3, user.getPrenom());
                  ps2.setInt(4,user.getNum_tel());
                  ps2.setString(5, user.getAdresse());
                  ps2.setString(6, user.getEmail());
                  ps2.setString(7,user.getPassword());
                  ps2.executeUpdate();
                  System.out.println("User Added Successfully!");
              }
              else {
                  System.out.println("User already exists!");
              }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void modifier(User user) {
        String req="UPDATE `user` SET `cin`=?,`nom`=?,`prenom`=?," +
                "`num_tel`=?,`adresse`=?,`email`=?,`password`=?,`role`=? WHERE `id_user`=?";
        try {
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,user.getCin());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getPrenom());
            ps.setInt(4,user.getNum_tel());
            ps.setString(5, user.getAdresse());
            ps.setString(6, user.getEmail());
            ps.setString(7,user.getPassword());
            ps.setString(8,user.getRole());
            ps.setInt(9,user.getId_user());
            ps.executeUpdate();
            System.out.println("User Updated Successfully!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(int id) {
        String req ="DELETE FROM `user` WHERE id_user=?";
        try {
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("User deleted Successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        String req ="SELECT * FROM `user`";
        try {
            Statement st =cnx.createStatement();
            ResultSet result =st.executeQuery(req);
            while (result.next()){
                User user =new User();
                user.setId_user(result.getInt("id_user"));
                user.setCin(result.getInt("cin"));
                user.setNom(result.getString("nom"));
                user.setPrenom(result.getString("prenom"));
                user.setNum_tel(result.getInt("num_tel"));
                user.setAdresse(result.getString("adresse"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void delete2(int id) {
        String reqVerifier= "SELECT COUNT(*) FROM `user` WHERE `cin` = ?";
        String req ="DELETE FROM `user` WHERE cin=?";

        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,id);
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
            if (check==0){
                System.out.println("User  n'existe pas!");

            }
            else {
                PreparedStatement ps2=cnx.prepareStatement(req);
                ps2.setInt(1,id);
                ps2.executeUpdate();
                System.out.println("User deleted Successfully!");            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getOneByCin(int cin) {
        User u = null;
        String req = "SELECT * FROM user WHERE cin = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, cin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = new User(
                            rs.getInt("id_user"),
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getInt("num_tel"),
                            rs.getString("adresse"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }


}
