package io.codiga.server.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.codiga.server.ServerGuiceTestModule;

public class InjectorServiceTestImpl implements InjectorService{
    private Injector injector;


    public InjectorServiceTestImpl() {
        injector = Guice.createInjector(new ServerGuiceTestModule());
    }


    @Override
    public Injector getInjector() {
        return injector;
    }
}
