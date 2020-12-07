package io.github.abeelfelipe.Vendas.model.repository;

import io.github.abeelfelipe.Vendas.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
