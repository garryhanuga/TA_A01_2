package apap.ta.controller;

import apap.ta.model.PegawaiModel;
import apap.ta.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PegawaiController {
    @Autowired
    PegawaiService pegawaiService;

    @GetMapping(value="/list-pegawai")
    private String listPegawai(Model model){
        List<PegawaiModel> listPegawai = pegawaiService.getPegawaiList();
        model.addAttribute("listPegawai", listPegawai);
        return "daftar-pegawai";

    }
}
