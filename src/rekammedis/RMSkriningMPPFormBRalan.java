/*
 * Form B Catatan Implementasi Manajer Pelayanan Pasien untuk pasien rawat jalan.
 */

package rekammedis;

/**
 * Varian rawat jalan dari RMSkriningMPPFormB. Form dan struktur tabelnya sama
 * persis, hanya tabel penyimpanan dan judulnya yang berbeda.
 *
 * @author perpustakaan
 */
public class RMSkriningMPPFormBRalan extends RMSkriningMPPFormB {

    /** Creates new form RMSkriningMPPFormBRalan
     * @param parent
     * @param modal */
    public RMSkriningMPPFormBRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    protected String tabelCatatan() {
        return "mpp_evaluasi_ralan_catatan";
    }

    @Override
    protected String judul() {
        return "Form B \u2013 Catatan Implementasi Manager Pelayanan Pasien Rawat Jalan";
    }
}
