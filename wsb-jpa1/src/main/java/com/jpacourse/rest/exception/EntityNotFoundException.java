package com.jpacourse.rest.exception;

public class EntityNotFoundException extends RuntimeException
{

    public EntityNotFoundException(String id)
    {
        super("Could not find entity of id " + id);
    }
}
