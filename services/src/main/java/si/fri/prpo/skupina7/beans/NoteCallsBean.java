package si.fri.prpo.skupina7.beans;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ApplicationScoped
public class NoteCallsBean {
    private static final Logger log = Logger.getLogger(NoteCallsBean.class.getName());
    private final Map<String, Integer> method2count = new HashMap<>();

    public void noteCall(String method) {
        if (method2count.containsKey(method)) {
            method2count.put(method, method2count.get(method) + 1);
        } else {
            method2count.put(method, 1);
        }
        
        log.info(method + ": Number of invocations: " + method2count.get(method));
    }
}
