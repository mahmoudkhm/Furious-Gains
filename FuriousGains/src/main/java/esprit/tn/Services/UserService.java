package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceUser;
import esprit.tn.Models.ForgetPwd;
import esprit.tn.Models.User;
import esprit.tn.Securite.BCrypt;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService implements InterfaceUser<User> {
    public static int idUser;
    public static String email;
    public static String password;
    public static String username;
    public static Integer num_tel;
    public static Date date_n;
    public static String roles;
    public static String imageUser;
    public Statement stm;
    private Connection cnx;
    private User u =new User();

    public UserService(){
        cnx= MyConnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(User user) {
        int code=1;
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String req="INSERT INTO `user`( `cin`, `nom`, `prenom`, `num_tel`, `adresse`, `email`, `password`, `role`,id_code_promo) " +
                "VALUES ('" + user.getCin()+"','"+ user.getNom() +"','"+user.getPrenom() +"','"+user.getNum_tel()+"','"+user.getAdresse()+"','"+user.getEmail()+"','"+hashed+"','"+user.getRole()+"','"+user.getImage()+"','"+code+"')";
        try {
            PreparedStatement ps =cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("User Added Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Utilisateur ajouter avec succes!");
            alert.showAndWait();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public void utilisateurCanBeAded(User user) {
        int code=1;
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String reqVerifier= "SELECT COUNT(*) FROM `user` WHERE `cin` = ?";
        String reqVerifiermail= "SELECT COUNT(*) FROM `user` WHERE `email` = ?";
        String req="INSERT INTO `user`( `cin`, `nom`, `prenom`, `dateuser`, `num_tel`, `adresse`, `email`, `password`, `role`, `image`, `id_code_promo`) " +
                "VALUES ('" + user.getCin()+"','"+ user.getNom() +"','"+user.getPrenom() +"','"+new java.sql.Date(user.getDateuser().getTime()) +"','"+user.getNum_tel()+"','"+user.getAdresse()+"','"+user.getEmail()+"','"+hashed+"','"+user.getRole()+"','"+user.getImage()+"','"+code+"')";
        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,user.getCin());
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
              if (check==0){
                  PreparedStatement ps2=cnx.prepareStatement(reqVerifiermail);
                  ps2.setString(1,user.getEmail());
                  ResultSet resultatS1 =ps2.executeQuery();
                  resultatS1.next();
                  int check2=resultatS1.getInt(1); //valeur du 1ere colone
                  if (check2==0){
                  PreparedStatement ps3=cnx.prepareStatement(req);
                  ps3.executeUpdate();
                  System.out.println("User Added Successfully!");
                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("User Added Successfully");
                      alert.showAndWait();
                  }
                  else {
                      System.out.println("Email already exists!");
                      Alert alert =new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("error");
                      alert.setContentText("utilisateur exist deja!");
                      alert.showAndWait();
                  }
              }
              else {
                  System.out.println("User already exists!");
                  Alert alert =new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("error");
                  alert.setContentText("utilisateur exist deja!");
                  alert.showAndWait();
              }

        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec d'ajout");
            alert.showAndWait();        }
    }


        @Override
    public void modifier(User user) {
            String reqVerifier= "SELECT COUNT(*) FROM `user` WHERE `cin` = ?";
            String req="UPDATE `user` SET `cin`=?,`nom`=?,`prenom`=?,`dateuser`= ?," +
                    "`num_tel`=?,`adresse`=?,`email`=? ,`image`= ?,`id_code_promo`=? WHERE `cin`=?";
            try {
                PreparedStatement ps=cnx.prepareStatement(reqVerifier);
                ps.setInt(1,user.getCin());
                ResultSet resultatS =ps.executeQuery();
                resultatS.next();
                int check=resultatS.getInt(1); //valeur du 1ere colone

                //verification
                if (check==0){

                    System.out.println("User n'existe pas!");
                }
                else {
                    PreparedStatement ps2=cnx.prepareStatement(req);
                    ps2.setInt(1,user.getCin());
                    ps2.setString(2, user.getNom());
                    ps2.setString(3, user.getPrenom());
                    ps2.setDate(4,new java.sql.Date(user.getDateuser().getTime()));
                    ps2.setInt(5,user.getNum_tel());
                    ps2.setString(6, user.getAdresse());
                    ps2.setString(7, user.getEmail());
                    ps2.setString(8,user.getImage());
                    ps2.setInt(9,user.getId_code_promo());
                    ps2.setInt(10,user.getCin());
                    ps2.executeUpdate();
                    System.out.println("User Updated Successfully!");
                }

            } catch (SQLException e) {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("echec de modifer");
                alert.showAndWait();        }
    }

    @Override
    public void modifier2(User user) {
        String reqVerifier= "SELECT COUNT(*) FROM `user` WHERE `cin` = ?";
        String req="UPDATE `user` SET `cin`=?,`nom`=?,`prenom`=?,`dateuser`= ?," +
                "`num_tel`=?,`adresse`=?,`email`=?,`role`=? ,`image`= ?,`id_code_promo`=? WHERE `cin`=?";
        try {
            PreparedStatement ps=cnx.prepareStatement(reqVerifier);
            ps.setInt(1,user.getCin());
            ResultSet resultatS =ps.executeQuery();
            resultatS.next();
            int check=resultatS.getInt(1); //valeur du 1ere colone

            //verification
            if (check==0){

                System.out.println("User n'existe pas!");
            }
            else {
                PreparedStatement ps2=cnx.prepareStatement(req);
                ps2.setInt(1,user.getCin());
                ps2.setString(2, user.getNom());
                ps2.setString(3, user.getPrenom());
                ps2.setDate(4,new java.sql.Date(user.getDateuser().getTime()));
                ps2.setInt(5,user.getNum_tel());
                ps2.setString(6, user.getAdresse());
                ps2.setString(7, user.getEmail());
                ps2.setString(8,user.getRole());
                ps2.setString(9,user.getRole());
                ps2.setInt(10,user.getId_code_promo());
                ps2.setInt(11,user.getCin());
                ps2.executeUpdate();
                System.out.println("User Updated Successfully!");
            }

        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("echec de modifer");
            alert.showAndWait();        }

    }

    @Override
    public void delete(int id) {
        String req ="DELETE FROM `user` WHERE id_user=?";
        try {
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("User deleted Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("Utilisateursupprime avec succes!");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
       // String req ="SELECT * FROM `user`";
        String req ="SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo;";
        try {
            Statement st =cnx.createStatement();
            ResultSet result =st.executeQuery(req);
            while (result.next()){
                User user =new User();
                user.setId_user(result.getInt("id_user"));
                user.setCin(result.getInt("cin"));
                user.setNom(result.getString("nom"));
                user.setPrenom(result.getString("prenom"));
                user.setDateuser(result.getDate("dateuser"));
                user.setNum_tel(result.getInt("num_tel"));
                user.setAdresse(result.getString("adresse"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
                user.setId_code_promo(result.getInt("Code"));
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
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("User  n'existe pas!");
                alert.showAndWait();
            }
            else {
                PreparedStatement ps2=cnx.prepareStatement(req);
                ps2.setInt(1,id);
                ps2.executeUpdate();
                System.out.println("User deleted Successfully!");
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setContentText("User deleted Successfully!");
                alert.showAndWait();}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getOneByCin(int cin) {
        User u = null;
        String req = "SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo WHERE cin LIKE  ? ";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setString(1, "%" + cin + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    imageUser=rs.getString("image");
                    u = new  User(rs.getInt("id_user"),
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDate("dateuser"),
                            rs.getInt("num_tel"),
                            rs.getString("adresse"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            imageUser,
                            rs.getInt("code")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
    public User getOneBy(String att,String rechercher) {
        User u = null;
        String req = "SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo  WHERE ? = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setString(1, att);
            stmt.setString(2, rechercher);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = new User(
                            rs.getInt("id_user"),
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDate("dateuser"),
                            rs.getInt("num_tel"),
                            rs.getString("adresse"),
                            rs.getString("email"),
                            rs.getString("password"),
                            imageUser,
                            rs.getString("role"),
                            rs.getInt("code")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
    public User verifierEmailMdp(String email,String mdp) {
        User u = null;

        try {
            String req = "SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo  WHERE email = ?";
            PreparedStatement statement = this.cnx.prepareStatement(req);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email) && BCrypt.checkpw(mdp, rs.getString("password")) == true) {
                    u = new User(
                            rs.getInt("id_user"),
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDate("dateuser"),
                            rs.getInt("num_tel"),
                            rs.getString("adresse"),
                            rs.getString("email"),
                            rs.getString("password"),
                            imageUser,
                            rs.getString("role"),
                            rs.getInt("code")
                    );

                }else {
                        System.err.println("Verifier vos donneés !");
                        Alert alert =new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setContentText("mdp!");
                        alert.showAndWait();
                    }
                }

        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        return u;
    }
    public User ajoutermail(ForgetPwd t) {
        User u = null;

        try {
            String req = "SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo  WHERE email = ?";
            PreparedStatement statement = this.cnx.prepareStatement(req);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                u = new User(
                        rs.getInt("id_user"),
                        rs.getInt("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateuser"),
                        rs.getInt("num_tel"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("password"),
                        imageUser,
                        rs.getString("role"),
                        rs.getInt("code")

                );

                    String reqs = "INSERT INTO reset(email, code, timeMils) VALUES (?, ?, ?)";
                    PreparedStatement psts = cnx.prepareStatement(reqs);
                    psts.setString(1, t.getEmail());
                    psts.setInt(2, t.getCode());
                    psts.setString(3, t.getTime());
                    psts.executeUpdate();
                    return u;

            }

        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        return u;
    }
        public User readByPhoneNumber( String phoneNumber) throws SQLException {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            User user = null;try {
                String query = "SELECT * FROM user WHERE num_tel = ?";
                stmt = cnx.prepareStatement(query);
                stmt.setString(1, phoneNumber);
                rs = stmt.executeQuery();

                if (rs != null && rs.next()) {
                    // Assuming User is your user class, replace it with your actual user class name
                    user = new User();
                    user.setId_user(rs.getInt("id_user"));
                    user.setNum_tel(Integer.parseInt(rs.getString("num_tel")));
                    user.setCin(Integer.parseInt(rs.getString("cin")));
                    // Set other user properties as needed
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                // Do not close the connection here since it's passed as a parameter
            }

            return user;
    }
    public int verifiernum( String phoneNumber) throws SQLException {
        PreparedStatement stmt = null;

        ResultSet rs = null;
        int numero = 0;
        try {
            String query = "SELECT num_tel FROM user WHERE num_tel = ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
              numero=rs.getInt("num_tel");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return numero;
    }
    public void setMotDePasse(int cin, String newPassword)  {

        PreparedStatement stmt ;
            String sql = "UPDATE user SET password = ? WHERE cin = ?";
        try {
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setInt(2, cin);
            stmt.executeUpdate();
            Alert alertType = new Alert(Alert.AlertType.INFORMATION);
            alertType.setTitle("Modifier avec succee");
            alertType.setContentText("done.");
            alertType.show();
        } catch (SQLException e) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
        }



    }

}
