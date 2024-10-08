package com.sventas.sventas.controller;

import com.sventas.sventas.exception.ModelNotFoundException;
import com.sventas.sventas.model.Cliente;
import com.sventas.sventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Cliente cliente) {
        clienteService.update(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Integer idCliente) {
        Cliente cliente = clienteService.findById(idCliente);

        if (cliente == null) {
            throw new ModelNotFoundException("Cliente no encontrado");
        }
        return new ResponseEntity<>(clienteService.findById(idCliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idCliente) throws Exception {
        Cliente cliente = clienteService.findById(idCliente);

        if (cliente == null) {
            throw new ModelNotFoundException("El cliente ah eliminar no existe");
        }
        clienteService.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
