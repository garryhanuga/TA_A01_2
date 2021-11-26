package apap.ta.rest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Data

//Data yang dikembaliin
public class RequestUpdateItemDetail {
    private String idItem;
    private Long idKategori;
    private int tambahanStok;
    private Date tanggalRequest;
    private Long idCabang;
}
