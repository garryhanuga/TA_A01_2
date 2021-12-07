package apap.ta.service;

import java.util.List;
import apap.ta.model.ProduksiModel;


public interface ProduksiService {
    ProduksiModel createProduksi(ProduksiModel produksi);
    List<ProduksiModel> retrieveListProduksi();
    ProduksiModel getProduksiByIdProduksi(Long id);
}
