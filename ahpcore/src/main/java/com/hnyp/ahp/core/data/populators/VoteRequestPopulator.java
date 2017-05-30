package com.hnyp.ahp.core.data.populators;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.UserData;
import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.models.VoteRequest;

public class VoteRequestPopulator implements Populator<VoteRequest, VoteRequestData> {

    @Resource
    private Converter<User, UserData> userConverter;
    
    @Override
    public void populate(VoteRequest source, VoteRequestData target) {
        target.setEmail(source.getEmail());
        target.setId(source.getId());
        target.setStatus(source.getStatus());
        target.setToken(source.getToken());
        if (source.getRegisteredUser() != null) {
            target.setRegisteredUser(userConverter.convert(source.getRegisteredUser()));
        }
    }

}
