package digitalinnovationone.example.restjaxrs.restjaxrs.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.SoldadoController;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.response.SoldadoListResponse;
import digitalinnovationone.example.restjaxrs.restjaxrs.controller.response.SoldadoResponse;
import digitalinnovationone.example.restjaxrs.restjaxrs.entity.SoldadoEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceSoldado{
    private ObjectMapper objectMapper;

    public ResourceSoldado(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity) {
        SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity,SoldadoListResponse.class);
        Link link = linkTo(methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId())).withSelfRel();
        soldadoListResponse.add(link);
        return soldadoListResponse;
    }

    public SoldadoResponse criarLinkDetalhe(SoldadoEntity soldadoEntity) {
        SoldadoResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity,SoldadoResponse.class);
        if(soldadoEntity.getStatus().equals("morto")) {
            Link link = linkTo(methodOn(SoldadoController.class).deletarSoldado(soldadoEntity.getId()))
                    .withRel("remover")
                    .withTitle("Deletar Soldado")
                    .withType("delete");
            soldadoListResponse.add(link);
        } else if (soldadoEntity.getStatus().equals("vivo")) {
            Link link = linkTo(methodOn(SoldadoController.class).buscarSoldado(soldadoEntity.getId()))
                    .withRel("batalhar")
                    .withTitle("Ir para frente do castelo")
                    .withType("put");
            soldadoListResponse.add(link);
        }
        return soldadoListResponse;
    }
}
