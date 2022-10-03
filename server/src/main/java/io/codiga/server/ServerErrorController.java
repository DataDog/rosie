package io.codiga.server;


import io.codiga.metrics.MetricsInterface;
import io.codiga.server.services.InjectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.codiga.metrics.MetricsName.METRIC_PAGE_NOT_FOUND;

/**
 * Handle 404 and other pages not being found.
 */
@RestController
public class ServerErrorController implements ErrorController {

    final InjectorService injectorService;
    private final Logger logger = LoggerFactory.getLogger(ServerErrorController.class);
    private MetricsInterface metrics;


    public ServerErrorController(InjectorService injectorService) {
        metrics = injectorService.getInjector().getInstance(MetricsInterface.class);
        this.injectorService = injectorService;
    }

    @RequestMapping("/error")
    public String error() {
        metrics.incrementMetric(METRIC_PAGE_NOT_FOUND);
        return "oh no!";

    }

}
