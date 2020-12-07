package io.github.abeelfelipe.Vendas.model.repository;

import io.github.abeelfelipe.Vendas.model.entity.ServicoPrestado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ServicoPrestadoCustomRepository {
    private final EntityManager em;

    public List<ServicoPrestado> filterByMonthOrName(Integer id, String nome, Integer mes) {
        String query = "select s from ServicoPrestado s join s.cliente c ";
        String sqlConector = " where ";

        if(id != null && id > 0) {
            query+= sqlConector + " s.id = :id";
            sqlConector = " and ";
        }

        if(nome != null && !nome.isEmpty()) {
            query+= sqlConector + " upper( c.nome ) like upper( :nome )";
            sqlConector = " and ";
        }

        if(mes != null && mes > 0) {
            query+= sqlConector + " MONTH( s.data ) = :mes";
            sqlConector = " and ";
        }

        TypedQuery<ServicoPrestado> q = em.createQuery(query, ServicoPrestado.class);

        if(id != null && id > 0) {
            q.setParameter("id", id);
        }

        if(nome != null && !nome.isEmpty()) {
            q.setParameter("nome", nome);
        }

        if(mes != null && mes > 0) {
            q.setParameter("mes", mes);
        }

        System.out.println(q.getResultList());

        return q.getResultList();
    }
}
