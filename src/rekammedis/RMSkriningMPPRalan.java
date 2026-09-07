/*
 * Skrining Manajer Pelayanan Pasien untuk pasien rawat jalan.
 */

package rekammedis;

/**
 * Varian rawat jalan dari RMSkriningMPP. Form dan struktur tabelnya sama
 * persis, hanya tabel penyimpanan dan judulnya yang berbeda.
 *
 * @author perpustakaan
 */
public class RMSkriningMPPRalan extends RMSkriningMPP {

    /** Creates new form RMSkriningMPPRalan
     * @param parent
     * @param modal */
    public RMSkriningMPPRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    protected String tabelSkrining() {
        return "mpp_skrining_ralan";
    }

    @Override
    protected String judul() {
        return "Skrining Manager Pelayanan Pasien Rawat Jalan";
    }

    @Override
    protected RMSkriningMPPFormA formEvaluasiA() {
        return new RMSkriningMPPFormARalan(null,false);
    }

    @Override
    protected RMSkriningMPPFormB formEvaluasiB() {
        return new RMSkriningMPPFormBRalan(null,false);
    }
}
