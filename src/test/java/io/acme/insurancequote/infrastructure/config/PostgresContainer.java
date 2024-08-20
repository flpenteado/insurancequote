package io.acme.insurancequote.infrastructure.config;

public class PostgresContainer extends org.testcontainers.containers.PostgreSQLContainer<PostgresContainer> {

    private static final String IMAGE_VERSION = "postgres:16-alpine";

    private static PostgresContainer container;

    private PostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresContainer getInstance() {
        if (container == null) {
            container = new PostgresContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    @Override
    public void stop() {
    }
}