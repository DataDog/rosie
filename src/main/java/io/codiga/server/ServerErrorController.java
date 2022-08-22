package io.codiga.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle 404 and other pages not being found.
 */
@RestController
public class ServerErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ServerErrorController.class);

    @RequestMapping("/error")
    public String error() {
        return "oh no!";
    }

}
