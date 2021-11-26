package apap.ta.controller;
import apap.ta.model.PegawaiModel;
import apap.ta.model.RoleModel;
import apap.ta.service.PegawaiService;
import apap.ta.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class PegawaiController {
    @Qualifier("pegawaiServiceImpl")
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addPegawaiFormPage(Model model) {
        PegawaiModel pegawai = new PegawaiModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listRole", listRole);
        return "form-add-pegawai";
    }

    @PostMapping(value = "/add")
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
        boolean cek = pegawaiService.checkUsername(pegawai.getUsername());
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("pegawai", pegawai);
        if(cek == true){
            pegawaiService.addPegawai(pegawai);
            return "home";
        }
        else{
            return "error";
        }
    }
}
