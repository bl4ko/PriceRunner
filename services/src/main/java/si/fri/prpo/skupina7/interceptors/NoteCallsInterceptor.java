package si.fri.prpo.skupina7.interceptors;

import si.fri.prpo.skupina7.annotations.NoteCalls;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@NoteCalls
public class NoteCallsInterceptor {

    Logger log = Logger.getLogger(NoteCallsInterceptor.class.getName());

    @AroundInvoke
    public Object NoteCalls(InvocationContext context) throws Exception {
        /* TODO */
        return null;
    }
}
