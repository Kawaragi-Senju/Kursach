package com.kawaragi.mainservice.security;

import com.kawaragi.mainservice.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final WorkerRepository workerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = workerRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User doesn't exist")
        );
        return SecureUser.fromUser(user);
    }
}
