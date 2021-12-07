package apap.ta.repository;

import apap.ta.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDb extends JpaRepository<DeliveryModel, Long>{
    Optional<DeliveryModel> findByIdDelivery(Long idDelivery);
    Optional<DeliveryModel> findByIdCabang(Long idCabang);
}
