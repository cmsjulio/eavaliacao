package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Discente;
import br.mil.ccarj.baseapi.domain.repository.DiscenteRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiscenteServiceImpl implements DiscenteService {

    private final DiscenteRepository repository;

    public DiscenteServiceImpl(DiscenteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Discente create(Discente entity) {
        return repository.save(entity);
    }

    @Override
    public Discente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Discente não encontrado"));
    }

    @Override
    public Page<Discente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Discente entity) {
        Discente discente = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, discente);

        repository.save(discente);
    }

    @Override
    public void delete(Long id) {
        Discente discente = findById(id);
        repository.delete(discente);
    }
}
