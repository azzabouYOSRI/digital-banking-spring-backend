package org.sid.ebankingbackend.registration;

import org.sid.ebankingbackend.entities.Authorities;
import org.sid.ebankingbackend.entities.AuthoritiesRepository;
import org.sid.ebankingbackend.entities.Users;
import org.sid.ebankingbackend.entities.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    private final AuthoritiesRepository authoritiesRepository;

    public RegistrationServiceImpl(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            AuthoritiesRepository authoritiesRepository
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    @Transactional
    public void registerUser(RegistrationRequest registrationRequest) {
        // Perform validation if needed

        // Create a new user
        Users user = new Users();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEnabled(true); // You may set the initial enabled state based on your requirements
        // Assign a default role or authority
        Authorities authority = new Authorities();
        authority.setUsername(registrationRequest.getUsername());
        authority.setAuthority("USER");
        authority.setUsers(user);

        user.getAuthoritieses().add(authority);

        // Save the user to the database
        usersRepository.save(user);
        authoritiesRepository.save(authority);
}
}
