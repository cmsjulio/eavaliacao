package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.repository.DisciplinaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaServiceImpl(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Disciplina create(Disciplina entity) {
        return repository.save(entity);
    }

    @Override
    public Disciplina findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
    }

    @Override
    public Page<Disciplina> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Disciplina entity) {
        Disciplina disciplina = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, disciplina);

        repository.save(disciplina);
    }

    @Override
    public void delete(Long id) {
        Disciplina disciplina = findById(id);
        repository.delete(disciplina);
    }
}
