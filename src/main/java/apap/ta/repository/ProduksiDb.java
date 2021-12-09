package apap.ta.repository;

import apap.ta.model.ProduksiModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduksiDb extends JpaRepository<ProduksiModel, Long> {
    Optional<ProduksiModel> findByIdProduksi(Long idProduksi);
}
