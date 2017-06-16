package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.VoteRequestData;

public interface VoteRequestFacade {
    
    VoteRequestData getById(long id);
    
    List<VoteRequestData> getActiveRequests();
    
    void accept(long id);
    
    void deny(long id);

}
