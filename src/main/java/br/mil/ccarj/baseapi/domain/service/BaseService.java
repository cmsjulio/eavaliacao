package br.mil.ccarj.baseapi.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Interface Basica e comum de Servico
public interface BaseService<E> {
    E create (E entity);
    E findById (Long id);
    Page<E> findAll (Pageable pageable);
    void update (Long id, E entity);
    void delete (Long id);
}
