package com.gimnasio.gimnasio.repositories;


import com.gimnasio.gimnasio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByRol(Usuario.Rol rol);
    List<Usuario> findByActivo(boolean activo);
}