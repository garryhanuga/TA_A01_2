package apap.ta.controller;

import apap.ta.model.PegawaiModel;
import apap.ta.model.RoleModel;
import apap.ta.service.PegawaiService;
import apap.ta.service.RoleService;
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

import java.util.List;

@Controller
@RequestMapping("/pegawai")
public class PegawaiController {
    @Qualifier("pegawaiServiceImpl")
    @Autowired
    PegawaiService pegawaiService;

    @Autowired
    RoleService roleService;

    @GetMapping(value="/list-pegawai")
    private String listPegawai(Model model){
        List<PegawaiModel> listPegawai = pegawaiService.getPegawaiList();
        List<Integer> listGajiTiapPegawai = pegawaiService.getListGajiTiapPegawai();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        System.out.println(rolePegawai);
        model.addAttribute("role", rolePegawai);
        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("listGajiTiapPegawai", listGajiTiapPegawai);
        return "daftar-pegawai";
    }

    @GetMapping(value = "/add")
    private String addPegawaiFormPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel peg = pegawaiService.getPegawai(auth.getName());
        model.addAttribute("role", peg.getRole().getNamaRole());
        PegawaiModel pegawai = new PegawaiModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listRole", listRole);
        return "form-add-pegawai";
    }

    @PostMapping(value = "/add")
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel peg = pegawaiService.getPegawai(auth.getName());
        model.addAttribute("role", peg.getRole().getNamaRole());
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("username", pegawai.getUsername());
        List<PegawaiModel> listPegawai = pegawaiService.getPegawaiList();
        for(int i =0 ; i<listPegawai.size(); i++){
            if(listPegawai.get(i).getUsername().equals(pegawai.getUsername())){
                return "error-add-pegawai";
            }
        }
        pegawai.setCounter(0);
        pegawaiService.addPegawai(pegawai);
        return "add-pegawai";
    }
}
