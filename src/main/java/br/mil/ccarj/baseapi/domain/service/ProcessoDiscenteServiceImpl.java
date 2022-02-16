package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.ProcessoDiscente;
import br.mil.ccarj.baseapi.domain.repository.ProcessoDiscenteRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcessoDiscenteServiceImpl implements ProcessoDiscenteService {

    private final ProcessoDiscenteRepository repository;

    public ProcessoDiscenteServiceImpl(ProcessoDiscenteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProcessoDiscente create(ProcessoDiscente entity) {
        return repository.save(entity);
    }

    @Override
    public ProcessoDiscente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Processo<->Discente não encontrado"));
    }

    @Override
    public Page<ProcessoDiscente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, ProcessoDiscente entity) {
        ProcessoDiscente processoDiscente = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, processoDiscente);

        repository.save(processoDiscente);
    }

    @Override
    public void delete(Long id) {
        ProcessoDiscente processoDiscente = findById(id);
        repository.delete(processoDiscente);
    }
}
