package io.codiga.server.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.codiga.server.guice.ServerGuiceModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
public class InjectorServiceImpl implements InjectorService {
    private Injector injector;
    private Logger logger = LoggerFactory.getLogger(InjectorServiceImpl.class);

    public InjectorServiceImpl() {
        logger.info("Instantiating injector service");
        injector = Guice.createInjector(new ServerGuiceModule());
    }


    @Override
    public Injector getInjector() {
        return injector;
    }
}
