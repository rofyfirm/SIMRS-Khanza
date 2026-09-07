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
}
