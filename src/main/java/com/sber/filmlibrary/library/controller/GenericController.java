package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
public abstract class GenericController<T extends GenericModel> {

    private final GenericRepository<T> genericRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericController(GenericRepository<T> genericRepository){
        this.genericRepository = genericRepository;
    }

    @Operation(description = "Получить информацию по ID", method = "getById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genericRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Отсутствуют данные по заданному ID: " + id)));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(genericRepository.findAll());
    }

    @Operation(description = "Добавить", method = "create")
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genericRepository.save(newEntity));
    }

    @Operation(description = "Обновить", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T updatedEntity, @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericRepository.save(updatedEntity));
    }
    @Operation(description = "Удалить", method = "delete")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void delete(@RequestParam(value = "id") Long id){
        genericRepository.deleteById(id);
    }
}
