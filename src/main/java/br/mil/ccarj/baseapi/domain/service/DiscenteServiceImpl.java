package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Discente;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.model.ProcessoDiscente;
import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import br.mil.ccarj.baseapi.domain.repository.DiscenteRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscenteServiceImpl implements DiscenteService {

    private final DiscenteRepository repository;

    @Autowired
    private ProcessoDisciplinaService processoDisciplinaService;

    @Autowired
    private ProcessoDiscenteService processoDiscenteService;

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

    // BACKUP COMENTADO DO PRIMEIRO MÉTODO
//    public List<ProcessoDiscente> obterListaDeProcessoDiscentePorIdDeUmDiscente(Long id) {
//        Discente discente = findById(id);
//        List<ProcessoDiscente> processoDiscentes = discente.getProcessoDiscente();
//        return processoDiscentes;
//
//
//    }


    public List<ProcessoDiscente> obterListaDeProcessoDiscentePorIdDeUmDiscente(Long id) {
        Discente discente = findById(id);
        List<ProcessoDiscente> processoDiscentes = discente.getProcessoDiscente();
        List<Long> processoDiscenteIds = new ArrayList<>();
        processoDiscentes.forEach(elemento -> processoDiscenteIds.add(elemento.getId()));
        List<ProcessoDiscente> processoDiscenteResposta = new ArrayList<>();
        processoDiscenteIds.forEach(elemento -> processoDiscenteResposta.add(processoDiscenteService.findById(elemento)));

        return processoDiscenteResposta;

    }

    public List<ProcessoAvaliativo> obterListaDeProcessoAvaliativoPorIdDeUmDiscente(Long id) {
        List<ProcessoDiscente> processoDiscentes = obterListaDeProcessoDiscentePorIdDeUmDiscente(id);
        List<ProcessoAvaliativo> processoAvaliativos = new ArrayList<>();
        processoDiscentes.forEach(elemento -> processoAvaliativos.add(elemento.getProcessoAvaliativo()));
        return processoAvaliativos;
    }

    public List<ProcessoDisciplina> obterListaDeProcessoDisciplinaPorIdDeUmDiscente(Long id) {
        List<ProcessoDiscente> processoDiscentes = obterListaDeProcessoDiscentePorIdDeUmDiscente(id);
        List<Long> processoAvaliativosIds = new ArrayList<>();
        processoDiscentes.forEach(elemento -> processoAvaliativosIds.add(elemento.getProcessoAvaliativo().getId()));
        List<ProcessoDisciplina> processoDisciplinas = new ArrayList<>();
        processoAvaliativosIds.forEach(elemento -> processoDisciplinas.add(processoDisciplinaService.findById(elemento)));
        return processoDisciplinas;
    }

}


