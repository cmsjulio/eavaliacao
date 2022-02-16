package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import br.mil.ccarj.baseapi.domain.repository.ProcessoDisciplinaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcessoDisciplinaServiceImpl implements ProcessoDisciplinaService {

    private final ProcessoDisciplinaRepository repository;

    public ProcessoDisciplinaServiceImpl(ProcessoDisciplinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProcessoDisciplina create(ProcessoDisciplina entity) {
        return repository.save(entity);
    }

    @Override
    public ProcessoDisciplina findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Processo<->Disciplina não encontrado"));
    }

    @Override
    public Page<ProcessoDisciplina> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, ProcessoDisciplina entity) {
        ProcessoDisciplina processoDisciplina = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, processoDisciplina);

        repository.save(processoDisciplina);
    }

    @Override
    public void delete(Long id) {
        ProcessoDisciplina processoDisciplina = findById(id);
        repository.delete(processoDisciplina);
    }
}
