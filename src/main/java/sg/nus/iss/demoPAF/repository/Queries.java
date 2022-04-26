package sg.nus.iss.demoPAF.repository;

public interface Queries {

    public static final String SQL_FIND_ALL_RSVPS =
            "select * from rsvp;";

    public static final String SQL_FIND_RSVP_BY_NAME =
            "select * from rsvp where name like CONCAT( '%',?,'%');";

    public static final String SQL_INSERT_RSVP =
            """
                    insert into rsvp (name ,email ,phone ,confirmation_date,comments)
                    values (?,?,?, ?,?)
                    """;

    public static final String SQL_FIND_RSVP_BY_EMAIL =
            "select * from rsvp where email = ?;";

    public static final String SQL_UPDATE_RSVP =
            """
                    update rsvp set
                    name = ?,
                    email = ?,
                    phone = ?,
                    confirmation_date = ?,
                    comments = ?
                    where rsvp_id = ?;
                    """;

    public static final String SQL_COUNT_RSVP =
            "select count(*) as count_rsvp from rsvp;";
}
