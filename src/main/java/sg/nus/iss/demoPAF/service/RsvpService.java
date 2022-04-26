package sg.nus.iss.demoPAF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.demoPAF.model.Rsvp;
import sg.nus.iss.demoPAF.repository.RsvpRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RsvpService {

    @Autowired
    private RsvpRepository rsvpRepo;

    public Optional<List<Rsvp>> getAllRsvps() {
        System.out.println(">>>> In rsvpSvc: getAllRsvps");
        List<Rsvp> rsvpList = rsvpRepo.getAllRsvp();
        return Optional.of(rsvpList);
    }

    public Optional<List<Rsvp>> getRsvpByName(String q) {
        System.out.println(">>>>> In rsvpSvc: getRsvpByName");
        Optional <List<Rsvp>> opt = rsvpRepo.getRsvpByName(q);
        return opt;
    }

    public int countRsvp() {
        System.out.println(">>>>> In rsvpSvc: countRsvp");
        return rsvpRepo.countRsvp();
    }


    public boolean addRsvp(final Rsvp rsvp) {
        //check if Rsvp exists, find by email
        Optional<List<Rsvp>> opt = rsvpRepo.getRsvpByEmail(rsvp.getEmail());

        //if rsvp does not exist, add Rsvp.
        if(opt.isEmpty()) {
            System.out.println(">>>> Adding RSVP");
            boolean addedRsvp = rsvpRepo.addRsvp(rsvp);
            System.out.println("added:" +addedRsvp);
            return addedRsvp;
        }

        //if rsvp exists, update rsvp.
        else if(opt.isPresent()){
            List<Rsvp> list = opt.get();
            int rsvp_id = list.get(0).getRsvp_id();
            rsvp.setRsvp_id(rsvp_id);

            System.out.println(">>>>> Updating RSVP");
            boolean updatedRsvp = rsvpRepo.updateRsvp(rsvp);
            System.out.println("updated:" + updatedRsvp);
            return updatedRsvp;

        }
        else return false;
    }
}
