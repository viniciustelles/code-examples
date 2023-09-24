package com.crud.catalog.item.exception;

public class EntityNotFoudException extends RuntimeException {

    public EntityNotFoudException(String uuid) {
        super("Could not find entity for id: " + uuid);
    }
}
