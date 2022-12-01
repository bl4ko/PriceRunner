package si.fri.prpo.skupina7.interceptors;

import si.fri.prpo.skupina7.annotations.NoteCalls;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

@Interceptor
@NoteCalls
public class NoteCallsInterceptor {

    Logger log = Logger.getLogger(NoteCallsInterceptor.class.getName());

    private Map<String, Integer> method2count;

    @AroundInvoke
    public Object NoteCalls(InvocationContext context) throws Exception {

        if (method2count.containsKey(context.getMethod().getName())) {
            method2count.put(context.getMethod().getName(), method2count.get(context.getMethod().getName()) + 1);
        } else {
            method2count.put(context.getMethod().getName(), 1);
        }

        log.log(INFO, "Method invocated: " + context.getMethod().getName());
        log.log(INFO, "Number of invocations: " + method2count.get(context.getMethod().getName()));

        return null;
    }
}
