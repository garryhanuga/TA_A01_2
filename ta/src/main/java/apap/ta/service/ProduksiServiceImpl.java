package apap.ta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.model.ProduksiModel;
import apap.ta.repository.ProduksiDb;

@Service
public class ProduksiServiceImpl implements ProduksiService {
    @Autowired
    ProduksiDb produksiDb;

    @Override
    public ProduksiModel createProduksi(ProduksiModel produksi) {
        return produksiDb.save(produksi);
    }

    @Override
    public ProduksiModel getProduksiByIdProduksi(Long id) {
        Optional<ProduksiModel> produksi = produksiDb.findByIdProduksi(id);
        if(produksi.isPresent()){
            return produksi.get();
        }
        return null;
    }

    @Override
    public List<ProduksiModel> retrieveListProduksi() {
        return produksiDb.findAll();
    }
    
}
