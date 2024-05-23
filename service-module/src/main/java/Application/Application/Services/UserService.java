package Application.Application.Services;

import Application.Abstractions.Repositories.IUserRepository;
import Application.Contracts.IRoleService;
import Application.Entities.UserEntity;
import Application.Dtos.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = "Configs")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)));
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public UserEntity createNewUser(RegistrationUserDto registrationUserDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationUserDto.getUsername());
        userEntity.setEmail(registrationUserDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        userEntity.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(userEntity);
    }
}
