package digitalinnovationone.example.restjaxrs.restjaxrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.request.SoldadoEditRequest;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.response.SoldadoListResponse;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.response.SoldadoResponse;
import digitalinnovationone.example.restjaxrs.restjaxrs.dto.Soldado;
import digitalinnovationone.example.restjaxrs.restjaxrs.service.SoldadoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/soldado") //router definindo a rota base dessa classe
public class SoldadoController {

    private SoldadoService soldadoService;

    public SoldadoController(SoldadoService soldadoService) {
        this.soldadoService = soldadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable Long id) {
        SoldadoResponse soldado = soldadoService.buscarSoldado(id);
        //return ResponseEntity.ok(soldado);
        return ResponseEntity.status(HttpStatus.OK).body(soldado);
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody Soldado soldado) {
        soldadoService.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editarSoldade(@PathVariable() Long id,
                                        @RequestBody SoldadoEditRequest soldadoEditRequest) {
        soldadoService.alterarSoldado(id,soldadoEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSoldado(@PathVariable Long id) {
        soldadoService.deletarSoldado(id);
        return ResponseEntity.ok().build();
        
    }

    @PutMapping("/frente-castelo/{id}")
    public ResponseEntity frenteCastelo(@PathVariable Long id) {
        //FAZER ALGO
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<SoldadoListResponse>> buscarSoldados(Long id) {
        CollectionModel<SoldadoListResponse> soldados = soldadoService.buscarSoldados();
        return ResponseEntity.status(HttpStatus.OK).body(soldados);
    }
}
