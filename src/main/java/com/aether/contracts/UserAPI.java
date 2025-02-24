package com.aether.contracts;

import com.aether.types.Profile;
import com.aether.types.RMSLimit;

public interface UserAPI {


    void logout();

    Profile getProfile();

    RMSLimit getFundsAndMargin();
}
