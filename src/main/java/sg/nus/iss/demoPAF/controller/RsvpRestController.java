package sg.nus.iss.demoPAF.controller;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.demoPAF.model.Rsvp;
import sg.nus.iss.demoPAF.service.RsvpService;

import java.util.List;
import java.util.Optional;

@RestController()
public class RsvpRestController {

    @Autowired
    private RsvpService rsvpSvc;

    @GetMapping(value = "api/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvp() {
        System.out.println(">>>>> In the controller: getAllRsvp()");

        Optional<List<Rsvp>> opt = rsvpSvc.getAllRsvps();

        if(opt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Json.createObjectBuilder()
                            .add("errorMessage","unable to find any result")
                            .build().toString()
                    );
        }

        List<Rsvp> rsvpList = opt.get();
        JsonArray rsvpJsonArr = Rsvp.createRsvpsJsonArr(rsvpList);
        return ResponseEntity.ok(rsvpJsonArr.toString());
    }

    @GetMapping(value = "/api/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvpByName(@RequestParam (name ="q", required = true) String q) {
        System.out.println(">>>>> In the controller: getRsvpByName()");

        Optional<List<Rsvp>> opt = rsvpSvc.getRsvpByName(q);

        if(opt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Json.createObjectBuilder()
                            .add("errorMessage","no matching RSVP records found for name: %s".formatted(q))
                            .build().toString()
                    );
        }

        List<Rsvp> rsvpList = opt.get();

        JsonArray rsvpJsonArr = Rsvp.createRsvpsJsonArr(rsvpList);
        return ResponseEntity.ok(rsvpJsonArr.toString());
    }


    @PostMapping (value = "/api/rsvp",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRsvp(@ModelAttribute Rsvp rsvp) {
        System.out.println(">>>>> In the controller: addRsvp()");
        System.out.println(rsvp.getComments()+rsvp.getConfirmation_date());

        try {
            boolean isAddedOrUpdated = rsvpSvc.addRsvp(rsvp);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Json.createObjectBuilder()
                            .add("errorMessage","something went wrong")
                            .build().toString()
                    );
        }

        return ResponseEntity.status(201).body("successfully added/updated Rsvp");
    }

    @GetMapping (value = "api/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> countRsvp() {
        System.out.println(">>>>> In the controller: addRsvp()");
        int rsvpCount = rsvpSvc.countRsvp();

        return ResponseEntity.status(201).body(
                Json.createObjectBuilder()
                        .add("rsvp_count",rsvpCount)
                        .build().toString());

    }


}
