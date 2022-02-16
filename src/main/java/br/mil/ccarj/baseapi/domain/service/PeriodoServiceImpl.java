package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Periodo;
import br.mil.ccarj.baseapi.domain.repository.PeriodoRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    private final PeriodoRepository repository;

    public PeriodoServiceImpl(PeriodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Periodo create(Periodo entity) {
        return repository.save(entity);
    }

    @Override
    public Periodo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período não encontrado"));
    }

    @Override
    public Page<Periodo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Periodo entity) {
        Periodo periodo = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, periodo);

        repository.save(periodo);
    }

    @Override
    public void delete(Long id) {
        Periodo periodo = findById(id);
        repository.delete(periodo);
    }
}
