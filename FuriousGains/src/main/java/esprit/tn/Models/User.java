package esprit.tn.Models;


import java.util.Date;

public class User  {
    private int id_user;
    private int cin;
    private String nom;
    private String prenom;
    private Date dateuser;

    private int num_tel;
    private String adresse;
    private String email;
    private String password;
    private String role;
    private String image;
    private int id_code_promo;

    public User() {}


    public User(int id_user, int cin, String nom, String prenom, Date dateuser, int num_tel, String adresse, String email, String password, String role, String image, int id_code_promo) {
        this.id_user = id_user;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.dateuser = dateuser;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
        this.id_code_promo = id_code_promo;
    }

    public User(int cin, String nom, String prenom, Date dateuser, int num_tel, String adresse, String email, String password, String role, String image, int id_code_promo) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.dateuser = dateuser;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
        this.id_code_promo = id_code_promo;
    }

    public User(int cin, String nom, String prenom, Date dateuser, int num_tel, String adresse, String email, String role, String image, int id_code_promo) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateuser = dateuser;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.email = email;
        this.role = role;
        this.image = image;
        this.id_code_promo = id_code_promo;
    }

    public Date getDateuser() {
        return dateuser;
    }

    public void setDateuser(Date dateuser) {
        this.dateuser = dateuser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId_code_promo() {
        return id_code_promo;
    }

    public void setId_code_promo(int id_code_promo) {
        this.id_code_promo = id_code_promo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", num_tel=" + num_tel +
                ", dateuser=" + dateuser +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", id_code_promo=" + id_code_promo +
                '}';
    }
}
