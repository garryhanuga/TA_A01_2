package apap.ta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduksiDb extends JpaRepository<ProduksiModel, Long> {
    ProduksiModel findByIdProduksi(Long idProduksi);
}
