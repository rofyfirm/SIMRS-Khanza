# Screenshot Fitur RSKJ per Release

Dokumentasi tampilan fitur yang dikembangkan di branch `rskj/release/v3` sampai `rskj/release/v6`.

- Tiap branch di-compile dan dijalankan terpisah, jadi screenshot menunjukkan tampilan pada release tersebut (label versi di status bar).
- Data yang tampil adalah **data contoh fiktif** dari database uji (pasien BUDI/SITI/AHMAD/SRI CONTOH, dr. Andi Contoh, dll.), bukan data pasien asli. Kop surat memakai identitas RSKJ Soeprapto dari tabel `setting`.
- Kotak merah menandai elemen yang baru/berubah.
- Hasil cetak (Jasper) dirender langsung dari laporan, bukan foto layar.

## v3: `rskj/release/v3` (versi 826.v3.1)

### 00. Versi aplikasi di status bar halaman utama

Commit: 08f807a9a

- [halaman utama](v3/00_versi_aplikasi/1_halaman_utama.png)

### 01. Edukasi Pasien & Keluarga Rawat Jalan: pilihan topik edukasi "Keperawatan" + cetak formulir

Commit: a86effe4c, 4662cce25

- [form input](v3/01_edukasi_pasien_keluarga_rj_keperawatan/1_form_input.png)
- [cetak formulir](v3/01_edukasi_pasien_keluarga_rj_keperawatan/2_cetak_formulir.png)

### 02. Permintaan Laboratorium: kolom Jenis Bayar, Kelas dan Tarif pada daftar pemeriksaan

Commit: b0193dcdc

- [daftar pemeriksaan dengan tarif](v3/02_permintaan_lab_tampil_tarif/1_daftar_pemeriksaan_dengan_tarif.png)

### 03. Pop-up peringatan riwayat alergi pasien (Yes/No) dari Kasir Ralan sebelum membuka data perawatan

Commit: e74c1e297

- [popup peringatan alergi](v3/03_peringatan_alergi/1_popup_peringatan_alergi.png)

### 04. Pelaksanaan Informasi & Edukasi: label "Materi&Edukasi"

Commit: e74c1e297

- [form input](v3/04_pelaksanaan_informasi_edukasi_label/1_form_input.png)

### 05. KFR: Form Re-Assessment Rehabilitasi Medik (input, cetak, menu Kasir Ralan)

Commit: 8b747db07, 31ef68032, 15644d041, 148b1f734

- [form input](v3/05_kfr_form_reassessment/1_form_input.png)
- [cetak form](v3/05_kfr_form_reassessment/2_cetak_form.png)
- [menu kasir ralan](v3/05_kfr_form_reassessment/3_menu_kasir_ralan.png)

### 06. KFR: Form Lembar Program Terapi Rehabilitasi Medik (input, cetak, menu Kasir Ralan)

Commit: 8b747db07, 15644d041, 148b1f734

- [form input](v3/06_kfr_lembar_program_terapi/1_form_input.png)
- [cetak form](v3/06_kfr_lembar_program_terapi/2_cetak_form.png)
- [menu kasir ralan](v3/06_kfr_lembar_program_terapi/3_menu_kasir_ralan.png)

### 07. Penjualan Obat: PPN default 0%

Commit: b5738e4f1

- [form penjualan obat](v3/07_penjualan_ppn_0/1_form_penjualan_obat.png)

### 08. Data HAIs: isian Decubitus + cetak data

Commit: 7514e63af

- [form input](v3/08_data_hais_decubitus/1_form_input.png)
- [cetak data](v3/08_data_hais_decubitus/2_cetak_data.png)

### 09. Edukasi Pasien & Keluarga Terintegrasi Rawat Inap (input, cetak formulir, menu Kamar Inap)

Commit: ec23db6bd, dac620ef0, a278b125e, 08282b324

- [form input](v3/09_edukasi_pasien_keluarga_ranap/1_form_input.png)
- [cetak formulir](v3/09_edukasi_pasien_keluarga_ranap/2_cetak_formulir.png)
- [menu kamar inap](v3/09_edukasi_pasien_keluarga_ranap/3_menu_kamar_inap.png)

### 10. Skrining MPP: parameter ke-17 "Permintaan Surat (Visum/Assesing/Surat Lainnya)" + cetak

Commit: b0e175244

- [form input](v3/10_skrining_mpp_parameter_17/1_form_input.png)
- [cetak lembar skrining](v3/10_skrining_mpp_parameter_17/2_cetak_lembar_skrining.png)

### 11. Riwayat Perawatan: bagian Form Re-Assessment, Lembar Program Terapi dan Edukasi Rawat Inap

Commit: 0a4ae7be6, 87daf113d

- [riwayat kfr](v3/11_riwayat_perawatan_kfr_edukasi/1_riwayat_kfr.png)
- [isi riwayat kfr lengkap](v3/11_riwayat_perawatan_kfr_edukasi/1b_isi_riwayat_kfr_lengkap.png)
- [riwayat edukasi ranap](v3/11_riwayat_perawatan_kfr_edukasi/2_riwayat_edukasi_ranap.png)
- [isi riwayat edukasi ranap lengkap](v3/11_riwayat_perawatan_kfr_edukasi/2b_isi_riwayat_edukasi_ranap_lengkap.png)

### 12. MPP Rawat Jalan: Skrining, Form A Evaluasi Awal, Form B Catatan Implementasi + menu Kasir Ralan

Commit: df36b9333, c45b18dc2

- [skrining mpp ralan](v3/12_mpp_rawat_jalan/1_skrining_mpp_ralan.png)
- [form a evaluasi awal ralan](v3/12_mpp_rawat_jalan/2_form_a_evaluasi_awal_ralan.png)
- [form b catatan implementasi ralan](v3/12_mpp_rawat_jalan/3_form_b_catatan_implementasi_ralan.png)
- [menu kasir ralan](v3/12_mpp_rawat_jalan/4_menu_kasir_ralan.png)

### 13. Cetak label Aturan Pakai Obat (layout baru: No. resep & tanggal, nama petugas)

Commit: b989e1c82

- [cetak label aturan pakai hal1](v3/13_cetak_aturan_pakai_obat/2_cetak_label_aturan_pakai_hal1.png)
- [cetak label aturan pakai hal2](v3/13_cetak_aturan_pakai_obat/2_cetak_label_aturan_pakai_hal2.png)
- [cetak label aturan pakai hal3](v3/13_cetak_aturan_pakai_obat/2_cetak_label_aturan_pakai_hal3.png)


## v3a: `rskj/release/v3a` (versi 826.v3.2)

### 00. Versi aplikasi di status bar halaman utama

Commit: 045c6cf64

- [halaman utama](v3a/00_versi_aplikasi/1_halaman_utama.png)

### 01. Skrining MPP (hotfix): kembali 16 parameter, butir 5 menjadi "Sering masuk IGD dalam waktu 1x24 jam"

Commit: 045c6cf64

- [form input](v3a/01_skrining_mpp_perbaikan/1_form_input.png)
- [cetak lembar skrining](v3a/01_skrining_mpp_perbaikan/2_cetak_lembar_skrining.png)

> Catatan: di `rskj/release/v3a` cetak ini gagal (`Unknown column name param17 in result set`) karena `report/rptCetakSkriningMPP.jasper` masih memuat parameter 17, sedangkan query di `RMSkriningMPP.java` sudah tidak memilihnya. Perbaikannya ada di branch `rskj/bugfix/v3a-cetak-skrining-mpp` (report dikembalikan ke versi 16 parameter sebelum `b0e175244`); screenshot cetak di atas diambil dengan report dari branch perbaikan tersebut.


## v4: `rskj/release/v4` (versi 826.v4)

### 00. Versi aplikasi di status bar halaman utama

Commit: ef9098d9f

- [halaman utama](v4/00_versi_aplikasi/1_halaman_utama.png)

### 01. Pengkajian Tambahan Bunuh Diri: lebih dari satu entri per no. rawat, cetak entri yang dipilih

Commit: 65071731a

- [dua entri satu perawatan](v4/01_bunuh_diri_multi_entri/1_dua_entri_satu_perawatan.png)
- [cetak entri terpilih](v4/01_bunuh_diri_multi_entri/2_cetak_entri_terpilih.png)

### 02. Kamar Inap: pop-up catatan pasien saat nama pasien diklik

Commit: c486cfc1b

- [kamar inap popup catatan](v4/02_popup_catatan_pasien_ranap/1_kamar_inap_popup_catatan.png)

### 03. Telaah Resep: butir ke-9 "Obat High Alert" + cetak data

Commit: 201013675

- [form input](v4/03_telaah_resep_obat_high_alert/1_form_input.png)
- [cetak data](v4/03_telaah_resep_obat_high_alert/2_cetak_data.png)

### 04. Daftar Permintaan Resep: cetak resep menampilkan umur pasien

Commit: 2c7cb6483

- [daftar permintaan resep](v4/04_cetak_resep_umur_pasien/1_daftar_permintaan_resep.png)
- [cetak lembar obat dengan umur](v4/04_cetak_resep_umur_pasien/2_cetak_lembar_obat_dengan_umur.png)

### 05. MPP Rawat Jalan: cetak Form A Evaluasi Awal dan Form B Catatan Implementasi

Commit: d82f68332, 096066961

- [data evaluasi form a](v4/05_mpp_ralan_cetak_form_a_b/1_data_evaluasi_form_a.png)
- [cetak form a evaluasi awal](v4/05_mpp_ralan_cetak_form_a_b/2_cetak_form_a_evaluasi_awal.png)
- [cetak form b catatan implementasi](v4/05_mpp_ralan_cetak_form_a_b/3_cetak_form_b_catatan_implementasi.png)


## v5: `rskj/release/v5` (versi 826.v5)

### 00. Versi aplikasi di status bar halaman utama

Commit: c8a61eddd

- [halaman utama](v5/00_versi_aplikasi/1_halaman_utama.png)

### 01. Autofill tanda vital, keluhan, pemeriksaan dan diagnosis dari SOAP ke Pengkajian Awal Medis/Keperawatan (4 contoh dari 37 form; kotak merah = terisi otomatis)

Commit: 76ede09d3, 3a8620e62, 529ce3cc5

- [medis ralan psikiatrik](v5/01_autofill_pemeriksaan/1_medis_ralan_psikiatrik.png)
- [medis ralan psikiatrik form lengkap](v5/01_autofill_pemeriksaan/1_medis_ralan_psikiatrik_form_lengkap.png)
- [keperawatan ralan psikiatri](v5/01_autofill_pemeriksaan/2_keperawatan_ralan_psikiatri.png)
- [keperawatan ralan psikiatri form lengkap](v5/01_autofill_pemeriksaan/2_keperawatan_ralan_psikiatri_form_lengkap.png)
- [medis igd psikiatri](v5/01_autofill_pemeriksaan/3_medis_igd_psikiatri.png)
- [medis igd psikiatri form lengkap](v5/01_autofill_pemeriksaan/3_medis_igd_psikiatri_form_lengkap.png)
- [medis ranap psikiatrik](v5/01_autofill_pemeriksaan/4_medis_ranap_psikiatrik.png)
- [medis ranap psikiatrik form lengkap](v5/01_autofill_pemeriksaan/4_medis_ranap_psikiatrik_form_lengkap.png)

### 02. SOAP Rawat Jalan/Inap: pilihan Kesadaran "-"

Commit: 3bb1f8b5f

- [soap rawat jalan](v5/02_soap_kesadaran_opsi_strip/1_soap_rawat_jalan.png)
- [pilihan kesadaran](v5/02_soap_kesadaran_opsi_strip/2_pilihan_kesadaran.png)

### 03. Pengkajian Awal Medis IGD Psikiatri: nilai awal "Dipulangkan" = "-"

Commit: 6ab004782

- [form input](v5/03_igd_psikiatri_default_dipulangkan/1_form_input.png)

### 04. Peringatan alergi menjadi pesan informasi (OK) lalu langsung lanjut

Commit: eb7339cf4

- [popup peringatan alergi](v5/04_peringatan_alergi_info/1_popup_peringatan_alergi.png)

### 05. Monitor PANSS EC IGD (input, cetak formulir, menu IGD)

Commit: b08c564c7, 430970a10

- [form input](v5/05_monitor_panss_ec_igd/1_form_input.png)
- [cetak formulir](v5/05_monitor_panss_ec_igd/2_cetak_formulir.png)
- [menu igd](v5/05_monitor_panss_ec_igd/3_menu_igd.png)

### 06. Monitor PANSS EC Rawat Inap (input, cetak formulir, menu Kamar Inap & Perawatan Ranap)

Commit: 430970a10, 4481f726a

- [form input](v5/06_monitor_panss_ec_ranap/1_form_input.png)
- [cetak formulir](v5/06_monitor_panss_ec_ranap/2_cetak_formulir.png)
- [menu kamar inap](v5/06_monitor_panss_ec_ranap/3_menu_kamar_inap.png)
- [menu perawatan ranap](v5/06_monitor_panss_ec_ranap/4_menu_perawatan_ranap.png)

### 07. Riwayat Perawatan: bagian Monitor PANSS EC IGD & Ranap

Commit: 430970a10

- [riwayat panss ec igd](v5/07_riwayat_perawatan_panss_ec/1_riwayat_panss_ec_igd.png)
- [isi riwayat panss ec igd lengkap](v5/07_riwayat_perawatan_panss_ec/1b_isi_riwayat_panss_ec_igd_lengkap.png)
- [riwayat panss ec ranap](v5/07_riwayat_perawatan_panss_ec/2_riwayat_panss_ec_ranap.png)
- [isi riwayat panss ec ranap lengkap](v5/07_riwayat_perawatan_panss_ec/2b_isi_riwayat_panss_ec_ranap_lengkap.png)

### 08. Menu Utama: Monitor PANSS EC (menu utama hasil refactor)

Commit: be8d0929b, PR #23

- [menu utama cari panss](v5/08_menu_utama_panss_ec/1_menu_utama_cari_panss.png)


## v6: `rskj/release/v6` (versi 826.v6.1)

### 00. Versi aplikasi di status bar halaman utama

Commit: 9dacf1f13, 394762622

- [halaman utama](v6/00_versi_aplikasi/1_halaman_utama.png)

### 01. PANSS Score Rawat Inap (input, cetak formulir 2 halaman, menu Perawatan Ranap & Kamar Inap)

Commit: 7999bc227, 1876a64b7

- [form input](v6/01_panss_score_ranap/1_form_input.png)
- [cetak formulir hal1](v6/01_panss_score_ranap/2_cetak_formulir_hal1.png)
- [cetak formulir hal2](v6/01_panss_score_ranap/2_cetak_formulir_hal2.png)
- [menu perawatan ranap](v6/01_panss_score_ranap/3_menu_perawatan_ranap.png)
- [menu kamar inap](v6/01_panss_score_ranap/4_menu_kamar_inap.png)

### 02. Skrining Risiko Bunuh Diri (C-SSRS) (input, cetak, menu Perawatan Ralan & Kasir Ralan)

Commit: a8ef57dc3, cbce6140a, e0a51780b

- [form input](v6/02_cssrs_skrining_risiko_bunuh_diri/1_form_input.png)
- [cetak formulir](v6/02_cssrs_skrining_risiko_bunuh_diri/2_cetak_formulir.png)
- [menu perawatan ralan](v6/02_cssrs_skrining_risiko_bunuh_diri/3_menu_perawatan_ralan.png)
- [menu kasir ralan](v6/02_cssrs_skrining_risiko_bunuh_diri/4_menu_kasir_ralan.png)

### 03. Monitor PANSS EC Ralan untuk poli (input, menu Kasir Ralan & Perawatan Ralan)

Commit: 711efb5c1, 15f0adffc, bc8ef0738

- [form input](v6/03_monitor_panss_ec_ralan/1_form_input.png)
- [menu kasir ralan](v6/03_monitor_panss_ec_ralan/2_menu_kasir_ralan.png)
- [menu perawatan ralan](v6/03_monitor_panss_ec_ralan/3_menu_perawatan_ralan.png)

### 04. Surat Keterangan Kesehatan Jiwa (input, cetak surat dengan QR, cetak data, menu Kasir Ralan)

Commit: 996edbbe1, 9876f7664, 1426394c3

- [form input](v6/04_surat_keterangan_kesehatan_jiwa/1_form_input.png)
- [cetak surat dengan qr](v6/04_surat_keterangan_kesehatan_jiwa/2_cetak_surat_dengan_qr.png)
- [cetak data](v6/04_surat_keterangan_kesehatan_jiwa/3_cetak_data.png)
- [menu kasir ralan](v6/04_surat_keterangan_kesehatan_jiwa/4_menu_kasir_ralan.png)

### 05. Surat Keterangan Sehat: isian Golongan Darah + cetak surat sehat 1 & 2

Commit: b887637d5

- [form input](v6/05_surat_keterangan_sehat_golongan_darah/1_form_input.png)
- [cetak surat sehat 1](v6/05_surat_keterangan_sehat_golongan_darah/2_cetak_surat_sehat_1.png)
- [cetak surat sehat 2](v6/05_surat_keterangan_sehat_golongan_darah/3_cetak_surat_sehat_2.png)

### 06. PANSS Remisi Rawat Inap (Masuk/Keluar, persen peningkatan skor; input, cetak, menu)

Commit: a384c66b7

- [form input](v6/06_panss_remisi_ranap/1_form_input.png)
- [cetak formulir](v6/06_panss_remisi_ranap/2_cetak_formulir.png)
- [menu perawatan ranap](v6/06_panss_remisi_ranap/3_menu_perawatan_ranap.png)

### 07. Kotak "Cari menu..." untuk menyaring menu kiri Perawatan Ranap/Ralan dan Riwayat Perawatan

Commit: 27ea6350c

- [filter menu perawatan ranap](v6/07_filter_menu/1_filter_menu_perawatan_ranap.png)
- [filter menu perawatan ralan](v6/07_filter_menu/2_filter_menu_perawatan_ralan.png)
- [filter menu riwayat perawatan](v6/07_filter_menu/3_filter_menu_riwayat_perawatan.png)

### 08. SKBN 2: keperluan sampai 300 karakter (font mengecil otomatis bila panjang)

Commit: 9dacf1f13, a87bf81f2

- [form input](v6/08_skbn_2_keperluan_300_karakter/1_form_input.png)
- [cetak skbn 2](v6/08_skbn_2_keperluan_300_karakter/2_cetak_skbn_2.png)

### 09. Daftar Permintaan Resep Rawat Inap: filter pasien, jumlah baris dan navigasi halaman

Commit: ba31d1850

- [resep rawat inap halaman 1](v6/09_daftar_permintaan_resep_paginasi/1_resep_rawat_inap_halaman_1.png)
- [resep rawat inap halaman 2](v6/09_daftar_permintaan_resep_paginasi/2_resep_rawat_inap_halaman_2.png)

### 10. Riwayat Perawatan: bagian PANSS Score, PANSS Remisi dan C-SSRS

Commit: 1876a64b7, cbce6140a, a384c66b7, 38751a288

- [riwayat panss score remisi](v6/10_riwayat_perawatan_panss_cssrs/1_riwayat_panss_score_remisi.png)
- [isi riwayat panss lengkap](v6/10_riwayat_perawatan_panss_cssrs/1b_isi_riwayat_panss_lengkap.png)
- [riwayat cssrs](v6/10_riwayat_perawatan_panss_cssrs/2_riwayat_cssrs.png)
- [isi riwayat cssrs lengkap](v6/10_riwayat_perawatan_panss_cssrs/2b_isi_riwayat_cssrs_lengkap.png)

### 11. Menu Utama: pencarian menu PANSS dan Bunuh Diri

Commit: 7999bc227, a8ef57dc3, bc8ef0738

- [menu utama cari panss](v6/11_menu_utama_psikiatri/1_menu_utama_cari_panss.png)
- [menu utama cari bunuh diri](v6/11_menu_utama_psikiatri/2_menu_utama_cari_bunuh_diri.png)


## Perubahan tanpa screenshot

- v3 `89e85de65`: perbaikan simpan Triase IGD (tidak mengubah tampilan).
- v3 `79b5d3d81`: pembaruan `rptDataTelaahResep`; tampilan terbarunya ada di v4/03.
- v5 `be8d0929b`: refactor `frmUtama` ke `MenuUtama`/`DaftarMenuUtama` (tampilan sama; lihat v5/08 dan v6/11).
- v6 `38751a288`: tinggi panel menu Riwayat Perawatan (semua checkbox kini terlihat; tercakup di v6/10).
