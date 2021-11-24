package apap.ta.repository;

import apap.ta.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<PegawaiModel, Long> {
    PegawaiModel findByRole(String Role);
}
