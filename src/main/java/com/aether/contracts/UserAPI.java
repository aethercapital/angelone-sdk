package com.aether.contracts;

import com.aether.types.Profile;

public interface UserAPI {


    void logout();

    Profile getProfile();

    void getFundsAndMargin();
}
