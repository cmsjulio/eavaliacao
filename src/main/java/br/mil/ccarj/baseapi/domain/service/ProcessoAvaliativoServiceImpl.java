package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.repository.ProcessoAvaliativoRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcessoAvaliativoServiceImpl implements ProcessoAvaliativoService {

    private final ProcessoAvaliativoRepository repository;

    public ProcessoAvaliativoServiceImpl(ProcessoAvaliativoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProcessoAvaliativo create(ProcessoAvaliativo entity) {
        return repository.save(entity);
    }

    @Override
    public ProcessoAvaliativo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Processo avaliativo não encontrado"));
    }

    @Override
    public Page<ProcessoAvaliativo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, ProcessoAvaliativo entity) {
        ProcessoAvaliativo processoAvaliativo = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, processoAvaliativo);

        repository.save(processoAvaliativo);
    }

    @Override
    public void delete(Long id) {
        ProcessoAvaliativo processoAvaliativo = findById(id);
        repository.delete(processoAvaliativo);
    }
}
