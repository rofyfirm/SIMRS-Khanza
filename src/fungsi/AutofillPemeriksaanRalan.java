package fungsi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.JTextComponent;

/**
 * Mengisi field form dari data pemeriksaan_ralan pada no_rawat yang sama.
 * Semua kolom diambil sekaligus dengan satu query. Bila no_rawat belum punya
 * data pemeriksaan_ralan, field dibiarkan apa adanya.
 *
 * <pre>
 * AutofillPemeriksaanRalan.dari(TNoRw.getText())
 *         .isi("keluhan",KeluhanUtama)
 *         .isi("tensi",TD)
 *         .jalankan();
 * </pre>
 *
 * Nama kolom harus benar-benar ada di tabel pemeriksaan_ralan. Karena diambil
 * dalam satu query, satu nama kolom yang salah membatalkan seluruh pengisian.
 */
public final class AutofillPemeriksaanRalan {
    private final String noRawat;
    private final List<String> kolom=new ArrayList<>();
    private final List<JTextComponent> field=new ArrayList<>();

    private AutofillPemeriksaanRalan(String noRawat){
        this.noRawat=noRawat;
    }

    /** @param noRawat no_rawat yang datanya dicari di pemeriksaan_ralan
     * @return  */
    public static AutofillPemeriksaanRalan dari(String noRawat){
        return new AutofillPemeriksaanRalan(noRawat);
    }

    /** Isi field dengan nilai dari kolom pemeriksaan_ralan.
     * @param namaKolom nama kolom di tabel pemeriksaan_ralan
     * @param target field yang diisi
     * @return  */
    public AutofillPemeriksaanRalan isi(String namaKolom,JTextComponent target){
        kolom.add(namaKolom);
        field.add(target);
        return this;
    }

    public void jalankan(){
        if(kolom.isEmpty()){
            return;
        }
        // Koneksi dipakai bersama seluruh aplikasi, jadi hanya statement dan
        // resultset yang ditutup.
        try(PreparedStatement ps=koneksiDB.condb().prepareStatement(
                "select "+String.join(",",kolom)+" from pemeriksaan_ralan where no_rawat=?")){
            ps.setString(1,noRawat);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    for(int i=0;i<field.size();i++){
                        String nilai=rs.getString(i+1);
                        field.get(i).setText(nilai==null?"":nilai);
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Notif Autofill Pemeriksaan Ralan : "+e);
        }
    }
}
