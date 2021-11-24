package apap.ta.repository;

import apap.ta.model.RequestUpdateItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestUpdateItemDb extends JpaRepository<RequestUpdateItemModel, Long> {
    Optional<RequestUpdateItemModel> findByidRequestUpdateItem(Long idRequestUpdateItem);
}
