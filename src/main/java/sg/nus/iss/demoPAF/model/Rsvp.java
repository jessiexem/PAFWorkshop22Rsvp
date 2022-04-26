package sg.nus.iss.demoPAF.model;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rsvp {

    private int rsvp_id;
    private String name;
    private String email;
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date confirmation_date;

    private String comments;

    public int getRsvp_id() {
        return rsvp_id;
    }

    public void setRsvp_id(int rsvp_id) {
        this.rsvp_id = rsvp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(Date confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static List<Rsvp> create(SqlRowSet rs) {
        List<Rsvp> rsvpList = new ArrayList<>();

        while (rs.next()) {
            Rsvp rsvp = new Rsvp();
            rsvp.setRsvp_id(rs.getInt("rsvp_id"));
            rsvp.setName(rs.getString("name"));
            rsvp.setEmail(rs.getString("email"));
            rsvp.setPhone(rs.getString("phone"));
            rsvp.setConfirmation_date(rs.getDate("confirmation_date"));
            rsvp.setComments(rs.getString("comments"));
            rsvpList.add(rsvp);
        }
        return rsvpList;
    }

    public static JsonArray createRsvpsJsonArr(List<Rsvp> list) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder rsvpBuilder = Json.createObjectBuilder();
        list.stream().forEach(r->{
            rsvpBuilder.add("rsvp_id",r.getRsvp_id())
                    .add("name",r.getName())
                    .add("email",r.getEmail())
                    .add("phone",r.getPhone())
                    .add("confirmation_date",r.getConfirmation_date().toString())
                    .add("comments",r.getComments());
            arrayBuilder.add(rsvpBuilder);
        });

        JsonArray rsvpJsonArr = arrayBuilder.build();
        return rsvpJsonArr;
    }
}
