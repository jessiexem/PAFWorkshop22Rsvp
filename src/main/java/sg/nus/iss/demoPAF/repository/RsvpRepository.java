package sg.nus.iss.demoPAF.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import sg.nus.iss.demoPAF.model.Rsvp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static sg.nus.iss.demoPAF.repository.Queries.*;

@Repository
public class RsvpRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Rsvp> getAllRsvp() {
        final SqlRowSet rs = template.queryForRowSet(SQL_FIND_ALL_RSVPS);
        return Rsvp.create(rs);
    }

    public Optional<List<Rsvp>> getRsvpByName(String q) {
        final SqlRowSet rs = template.queryForRowSet(SQL_FIND_RSVP_BY_NAME,q);
        if (!rs.isBeforeFirst()) {
            System.out.println(">>>> Rsvp repo: getRsvpByName: no data found");
            return Optional.empty();
        }
        else return Optional.of(Rsvp.create(rs));
    }

    public Optional<List<Rsvp>> getRsvpByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(SQL_FIND_RSVP_BY_EMAIL,email);
        if (!rs.isBeforeFirst()) {
            System.out.println(">>>> Rsvp repo: getRsvpByEmail: no data found");
            return Optional.empty();
        }
        else return Optional.of(Rsvp.create(rs));
    }

    public int countRsvp() {
        final SqlRowSet rs = template.queryForRowSet(SQL_COUNT_RSVP);
        if(rs.first()) {
            return rs.getInt("count_rsvp");
        }
        return 0;
    }


    public boolean addRsvp(final Rsvp rsvp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String confirmation_date = dateFormat.format(rsvp.getConfirmation_date());

        System.out.println(confirmation_date);
        int added = template.update(SQL_INSERT_RSVP,
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                confirmation_date,
                rsvp.getComments());

        return added>0;
    }

    public boolean updateRsvp (final Rsvp rsvp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String confirmation_date = dateFormat.format(rsvp.getConfirmation_date());

        int updated = template.update(SQL_UPDATE_RSVP,
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                confirmation_date,
                rsvp.getComments(),
                rsvp.getRsvp_id());

        return updated>0;
    }
}
