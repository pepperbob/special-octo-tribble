package de.byoc.quarkus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MumbleService {

    public String doMumble() {
        return "Mumble me";
    }

}
