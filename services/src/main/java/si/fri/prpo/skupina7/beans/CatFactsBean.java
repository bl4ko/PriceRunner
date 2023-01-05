package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.annotations.NoteCalls;
import si.fri.prpo.skupina7.clients.CatFactsClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CatFactsBean {

    @Inject
    CatFactsClient catFactsClient;

    @NoteCalls
    public String getCatFact() {
        String catFact = "";

        catFact = catFactsClient.getCatFact();

        return catFact;
    }
}
