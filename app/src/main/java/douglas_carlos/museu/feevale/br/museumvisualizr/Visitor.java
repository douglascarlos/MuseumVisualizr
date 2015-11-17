package douglas_carlos.museu.feevale.br.museumvisualizr;

import java.util.Date;

/**
 * Created by Douglas on 17/11/2015.
 */
public class Visitor {

    private Integer id;
    private String name;
    private String email;
    private String kiosk;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKiosk() {
        return kiosk;
    }

    public void setKiosk(String kiosk) {
        this.kiosk = kiosk;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
