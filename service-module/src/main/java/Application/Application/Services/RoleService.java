package Application.Application.Services;

import Application.Abstractions.Repositories.IRoleRepository;
import Application.Contracts.IRoleService;
import Application.Entities.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findUserByName(String name) {
        return roleRepository.findByName(name);
    }

    public RoleEntity getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
