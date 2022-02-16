package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Nota;
import br.mil.ccarj.baseapi.domain.model.TipoDeProva;
import br.mil.ccarj.baseapi.domain.repository.NotaRepository;
import br.mil.ccarj.baseapi.domain.repository.TipoDeProvaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;

    public NotaServiceImpl(NotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Nota create(Nota entity) {
        return repository.save(entity);
    }

    @Override
    public Nota findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nota não encontrada"));
    }

    @Override
    public Page<Nota> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Nota entity) {
        Nota nota = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, nota);

        repository.save(nota);
    }

    @Override
    public void delete(Long id) {
        Nota nota = findById(id);
        repository.delete(nota);
    }
}
