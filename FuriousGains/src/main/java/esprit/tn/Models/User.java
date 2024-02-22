package esprit.tn.Models;



public class User  {
    private int id_user;
    private int cin;
    private String nom;
    private String prenom;
    private int num_tel;

    private String adresse;
    private String email;
    private String password;
    private String role;
    private int id_code_promo;

    public User() {}

    public User(int id_user, int cin, String nom, String prenom, int num_tel, String adresse, String email, String password, int id_code_promo) {
        this.id_user = id_user;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.id_code_promo = id_code_promo;
    }

    public User(int cin, String nom, String prenom, int num_tel, String adresse, String email, String password, String role, int id_code_promo) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id_code_promo = id_code_promo;
    }

    public User(int cin, String nom, String prenom, int num_tel, String adresse, String email, String password, int id_code_promo) {
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.id_code_promo = id_code_promo;
    }

    public User(int id_user, int cin, String nom, String prenom, int num_tel, String adresse, String email, String password, String role) {
        this.id_user = id_user;
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int i, String nomTFText, String prenomTFText, int cin, String adresseTFText, String text) {
        this.cin = cin;
    }

    public User(int cin, String nom, String prenom, int num_tel, String adresse, String email, String password) {
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }

    public User(int cin, String nom, String prenom, int num_tel, String adresse, String email, String password, String role) {
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
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
                ", num_tel=" + num_tel +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id_code_promo=" + id_code_promo +
                '}';
    }
}
