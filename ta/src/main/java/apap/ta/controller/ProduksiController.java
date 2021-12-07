package apap.ta.controller;
import apap.ta.model.ProduksiModel;
import apap.ta.service.ProduksiService;
import apap.ta.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produksi")
public class ProduksiController {
    @Qualifier("produksiServiceImpl")
    @Autowired
    ProduksiService produksiService;
    
    @GetMapping(value="/list-produksi")
    private String viewAllProduksi(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("role", rolePegawai);

        List<ProduksiModel> listProduksi = produksiService.retrieveListProduksi();
        model.addAttribute("listProduksi", listProduksi);
        return "daftar-produksi";
    }
    
}
