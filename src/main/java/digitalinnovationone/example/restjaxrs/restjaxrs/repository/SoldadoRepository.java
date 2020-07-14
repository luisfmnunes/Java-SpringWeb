package digitalinnovationone.example.restjaxrs.restjaxrs.repository;

import digitalinnovationone.example.restjaxrs.restjaxrs.entity.SoldadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldadoRepository extends JpaRepository<SoldadoEntity, Long> {
}
