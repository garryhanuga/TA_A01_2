package apap.ta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="produksi")
public class ProduksiModel implements Serializable {
    @Id
    @Column(name="id_produksi")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProduksi;

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idItem;

    @Column(name = "id_kategori")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private int tambahanStok;

    @NotNull
    @Column(name="tanggal_produksi", nullable = false)
    private Date tanggalProduksi;

    //relasi dengan pegawai
    @ManyToOne(fetch = FetchType.EAGER, optional= false)
    @JoinColumn(name="id_pegawai", referencedColumnName="id_pegawai", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

    //relasi dengan mesin
    @ManyToOne(fetch = FetchType.EAGER, optional= false)
    @JoinColumn(name="id_mesin", referencedColumnName="id_mesin", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MesinModel mesin;

    //relasi dengan request update item
     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name="id_request_update_item", referencedColumnName="id_request_update_item")
     private RequestUpdateItemModel RequestUpdateItem;



}
