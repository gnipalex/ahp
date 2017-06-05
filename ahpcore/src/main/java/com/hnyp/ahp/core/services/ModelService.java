package com.hnyp.ahp.core.services;

public interface ModelService {

    void save(Object object);
    
    void remove(Object object);
    
    <T> void remove(Class<T> type, long id);
    
    <T> T getById(Class<T> type, long id);
    
    <T> T loadById(Class<T> type, long id);
    
}
