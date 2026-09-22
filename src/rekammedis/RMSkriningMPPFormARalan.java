/*
 * Form A Evaluasi Awal Manajer Pelayanan Pasien untuk pasien rawat jalan.
 */

package rekammedis;

/**
 * Varian rawat jalan dari RMSkriningMPPFormA. Form dan struktur tabelnya sama
 * persis, hanya tabel penyimpanan dan judulnya yang berbeda.
 *
 * @author perpustakaan
 */
public class RMSkriningMPPFormARalan extends RMSkriningMPPFormA {

    /** Creates new form RMSkriningMPPFormARalan
     * @param parent
     * @param modal */
    public RMSkriningMPPFormARalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    protected String tabelEvaluasi() {
        return "mpp_evaluasi_ralan";
    }

    @Override
    protected String tabelEvaluasiMasalah() {
        return "mpp_evaluasi_ralan_masalah";
    }

    @Override
    protected String judul() {
        return "Form A \u2013 Evaluasi Awal Manajer Pelayanan Pasien Rawat Jalan";
    }

    @Override
    protected String laporanEvaluasi() {
        return "rptCetakEvaluasiAwalMPPRalan.jasper";
    }

    @Override
    protected String sqlCetakEvaluasi() {
        return "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
               "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi_ralan.tanggal, " +
               "poliklinik.nm_poli,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') as tgl_registrasi,reg_periksa.jam_reg,"+
               "mpp_evaluasi_ralan.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi_ralan.kd_konsulan,dokterkonsulen.nm_dokter as konsulan, " +
               "mpp_evaluasi_ralan.diagnosis,mpp_evaluasi_ralan.kelompok,mpp_evaluasi_ralan.assesmen,mpp_evaluasi_ralan.identifikasi,mpp_evaluasi_ralan.rencana,mpp_evaluasi_ralan.nip,petugas.nama "+
               "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
               "inner join mpp_evaluasi_ralan on mpp_evaluasi_ralan.no_rawat=reg_periksa.no_rawat " +
               "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "+
               "inner join dokter as dokterpj on mpp_evaluasi_ralan.kd_dokter=dokterpj.kd_dokter " +
               "inner join dokter as dokterkonsulen on mpp_evaluasi_ralan.kd_konsulan=dokterkonsulen.kd_dokter " +
               "inner join petugas on mpp_evaluasi_ralan.nip=petugas.nip " +
               "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
               "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
               "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
               "inner join propinsi on pasien.kd_prop=propinsi.kd_prop ";
    }
}
