package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.UserData;
import com.hnyp.ahp.core.models.User;

public class UserDataPopulator implements Populator<User, UserData> {

    @Override
    public void populate(User source, UserData target) {
        target.setEmail(source.getEmail());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
    }

}
