package com.silamoney.client.domain;

import java.util.List;

public class Entities {

    private List<Entity> individuals;
    private List<Entity> businesses;

    /**
     * @return the individuals
     */
    public List<Entity> getIndividuals() {
        return individuals;
    }

    /**
     * @return the businesses
     */
    public List<Entity> getBusinesses() {
        return businesses;
    }
}