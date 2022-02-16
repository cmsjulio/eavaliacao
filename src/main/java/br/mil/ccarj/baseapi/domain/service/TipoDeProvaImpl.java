package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.TipoDeProva;
import br.mil.ccarj.baseapi.domain.repository.TipoDeProvaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoDeProvaImpl implements TipoDeProvaService {

    private final TipoDeProvaRepository repository;

    public TipoDeProvaImpl(TipoDeProvaRepository repository) {
        this.repository = repository;
    }

    @Override
    public TipoDeProva create(TipoDeProva entity) {
        return repository.save(entity);
    }

    @Override
    public TipoDeProva findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de Prova não encontrado"));
    }

    @Override
    public Page<TipoDeProva> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, TipoDeProva entity) {
        TipoDeProva tipoDeProva = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, tipoDeProva);

        repository.save(tipoDeProva);
    }

    @Override
    public void delete(Long id) {
        TipoDeProva tipoDeProva = findById(id);
        repository.delete(tipoDeProva);
    }
}
