package apap.ta.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="mesin")

public class MesinModel {
    @Id
    @Column(name="id_mesin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesin;

    @NotNull
    @Size(max = 50)
    @Column(name="nama", nullable = false)
    private String namaMesin;

    @NotNull
    @Column(name="id_kategori", nullable = false)
    private Long idKategori;

    @NotNull
    @Column(name="tanggal_dibuat", nullable = false)
    private Date tanggalDibuat;

    @NotNull
    @Column(name="kapasitas", nullable = false)
    private int kapasitas;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<ProduksiModel> pegawaiRole;
}
