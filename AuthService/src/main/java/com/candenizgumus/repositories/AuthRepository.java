package com.candenizgumus.repositories;

import com.candenizgumus.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long>
{
    /**
     * Username daha önce alınmış mı kontrol sağlamak için.
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * username ve password vt'da kayıtlı mı kontrolü yapar.
     */
    Optional<Auth> findByUsernameAndPassword(String username, String password);
}
