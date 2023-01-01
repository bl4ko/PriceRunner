package si.fri.prpo.skupina7.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@OpenAPIDefinition(info = @Info(
        title = "PriceRunner API",
        version = "v1",
        description = "API for PriceRunner project",
        license = @License(name = "Apache 2.0")
), servers = {
        @Server(url = "http://localhost:8080/"),
        @Server(url = "https://prpo.bl4ko.com/")
}, security = @SecurityRequirement(name = "openid-connect")
)
public class CartApplication extends Application {
}
