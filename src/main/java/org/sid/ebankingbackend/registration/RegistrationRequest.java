package org.sid.ebankingbackend.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String password;
    private boolean enabled;
    // Add other registration-related fields as needed
}
