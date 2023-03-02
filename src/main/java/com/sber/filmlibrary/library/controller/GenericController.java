package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.dto.GenericDTO;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDTO> {

    private GenericService<T, N> service;

    public GenericController(GenericService<T, N> genericService) {
        this.service = genericService;
    }

    @Operation(description = "Получить информацию по ID", method = "getById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> getById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @Operation(description = "Добавить", method = "create")
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> create(@RequestBody N newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newEntity));
    }

    @Operation(description = "Обновить", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> update(@RequestBody N updatedEntity, @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(updatedEntity));
    }

    @Operation(description = "Удалить", method = "delete")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    void delete(@RequestParam(value = "id") Long id) {
        service.delete(id);
    }
}
