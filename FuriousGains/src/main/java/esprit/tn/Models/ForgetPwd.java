package esprit.tn.Models;

public class ForgetPwd {
    private int id_f;
    private int code;
    private String email;
    private String time;

    public ForgetPwd(int id_f, int code, String email, String time) {
        this.id_f = id_f;
        this.code = code;
        this.email = email;
        this.time = time;
    }

    public ForgetPwd(String email) {
        this.email = email;
    }

    public ForgetPwd(int code, String email, String time) {
        this.code = code;
        this.email = email;
        this.time = time;
    }

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
