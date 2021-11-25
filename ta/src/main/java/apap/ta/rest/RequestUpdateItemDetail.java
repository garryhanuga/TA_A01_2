package apap.ta.rest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

//Data yang dikembaliin
public class RequestUpdateItemDetail {
    private String idItem;
    private Long idKategori;
    private int tambahanStok;
    private Long idCabang;
}
