package esprit.tn.Models;

public class reservation {
    private int id_Res ;
    private int nb_place;

    private String status_Res;
    private int id_event ;
    private int id_client ;

    public reservation() {}

    public reservation(int id_Res, int nb_place, String status_Res, int id_event, int id_client) {
        this.id_Res = id_Res;
        this.nb_place = nb_place;
        this.status_Res = status_Res;
        this.id_event = id_event;
        this.id_client = id_client;
    }

    public reservation(int nb_place, String status_Res, int id_event, int id_client) {
        this.nb_place = nb_place;
        this.status_Res = status_Res;
        this.id_event = id_event;
        this.id_client = id_client;
    }

    public int getId_Res() {
        return id_Res;
    }

    public void setId_Res(int id_Res) {
        this.id_Res = id_Res;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getStatus_Res() {
        return status_Res;
    }

    public void setStatus_Res(String status_Res) {
        this.status_Res = status_Res;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "reservation{" +
                "id_Res=" + id_Res +
                ", nb_place=" + nb_place +
                ", status_Res='" + status_Res + '\'' +
                ", id_event=" + id_event +
                ", id_client=" + id_client +
                '}';
    }
}
