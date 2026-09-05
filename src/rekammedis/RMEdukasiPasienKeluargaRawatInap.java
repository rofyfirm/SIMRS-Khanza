/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;

import fungsi.akses;

/**
 * Formulir Edukasi Pasien &amp; Keluarga Terintegrasi - Rawat Inap.
 *
 * Class ini sengaja dibuat dengan meng-extends {@link RMEdukasiPasienKeluargaRawatJalan}
 * (prinsip OOP - inheritance) sehingga seluruh UI (initComponents, layout, validasi,
 * event handler umum, dsb) tetap dipakai ulang (reuse) dari class induk.
 *
 * Yang di-override di sini HANYA bagian kecil yang memang berbeda antara
 * Rawat Jalan dan Rawat Inap, yaitu:
 *  - nama tabel query      : edukasi_pasien_keluarga_rj  -> edukasi_pasien_keluarga_ri
 *  - nama file laporan     : rptFormulirEdukasiPasienRJ.jasper -> rptFormulirEdukasiPasienRI.jasper
 *  - judul laporan/form    : "...Rawat Jalan..." -> "...Rawat Inap..."
 *  - hak akses menu        : akses.getedukasi_pasien_keluarga_rj() -> akses.getedukasi_pasien_keluarga_ri()
 *
 * Semua query (tampil, simpan, ganti/edit, hapus, cetak) pada class induk sudah
 * dibuat generik dengan memanggil getNamaTabelEdukasi() / getNamaLaporanEdukasi() /
 * getJudulLaporanEdukasi() / getHakAksesEdukasi(), sehingga class ini tidak perlu
 * menduplikasi query SQL sama sekali.
 *
 * Catatan: pastikan pada class fungsi.akses sudah tersedia method
 * getedukasi_pasien_keluarga_ri() dan pada folder report sudah tersedia
 * file rptFormulirEdukasiPasienRI.jasper. Jika belum ada, silakan
 * ditambahkan terlebih dahulu (mengikuti pola akses/report RJ yang sudah ada).
 *
 * @author perpustakaan
 */
public class RMEdukasiPasienKeluargaRawatInap extends RMEdukasiPasienKeluargaRawatJalan {

    /** Creates new form RMEdukasiPasienKeluargaRawatInap
     * @param parent
     * @param modal */
    public RMEdukasiPasienKeluargaRawatInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        // Satu-satunya penyesuaian UI yang diperlukan: judul panel/border
        // yang semula menyebut "Rawat Jalan" diganti "Rawat Inap".
        // internalFrame1 sudah protected pada class induk sehingga bisa
        // diakses langsung di sini tanpa perlu meng-override initComponents().
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)),
                "::[ Edukasi Pasien & Keluarga Terintegrasi Rawat Inap ]::",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 11),
                new java.awt.Color(50, 50, 50)));
    }

    /** Ganti tabel query dari edukasi_pasien_keluarga_rj ke edukasi_pasien_keluarga_ri. */
    @Override
    protected String getNamaTabelEdukasi() {
        return "edukasi_pasien_keluarga_ri";
    }

    /** Ganti nama file jasper laporan menjadi versi rawat inap. */
    @Override
    protected String getNamaLaporanEdukasi() {
        return "rptFormulirEdukasiPasienRI.jasper";
    }

    /** Ganti judul laporan menjadi "...Rawat Inap...". */
    @Override
    protected String getJudulLaporanEdukasi() {
        return "::[ Formulir Edukasi Pasien & Keluarga Terintegrasi Rawat Inap ]::";
    }

    /** Ganti pengecekan hak akses menu sesuai modul rawat inap. */
    @Override
    protected boolean getHakAksesEdukasi() {
        return akses.getedukasi_pasien_keluarga_rj();
    }
}
