package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.repository.ModeloDeAvaliacaoRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModeloDeAvaliacaoServiceImpl implements ModeloDeAvaliacaoService {

    private final ModeloDeAvaliacaoRepository repository;

    public ModeloDeAvaliacaoServiceImpl(ModeloDeAvaliacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ModeloDeAvaliacao create(ModeloDeAvaliacao entity) {
        return repository.save(entity);
    }

    @Override
    public ModeloDeAvaliacao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Modelo de Avaliação não encontrado"));
    }

    @Override
    public Page<ModeloDeAvaliacao> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, ModeloDeAvaliacao entity) {
        ModeloDeAvaliacao modeloDeAvaliacao = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, modeloDeAvaliacao);

        repository.save(modeloDeAvaliacao);
    }

    @Override
    public void delete(Long id) {
        ModeloDeAvaliacao modeloDeAvaliacao = findById(id);
        repository.delete(modeloDeAvaliacao);
    }
}
