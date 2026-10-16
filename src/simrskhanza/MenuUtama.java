package simrskhanza;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.ImageIcon;
import widget.ButtonBig;

/**
 * Daftar tombol menu utama. Satu entri = satu tombol + hak akses + kategori
 * (indeks cmbMenu). Dulu tiap tombol ditulis ulang di isCombo, isCariKosong
 * dan isCariIsi, sekarang cukup didaftarkan sekali (lihat DaftarMenuUtama).
 */
public final class MenuUtama {
    private final Container panel;
    private final Component induk;
    private final Component area;
    private final Runnable tutup;
    private final List<Entri> daftar=new ArrayList<>();
    private final Map<String,Entri> perNama=new HashMap<>();
    private int kategori=0;

    /**
     * @param panel tempat tombol ditampilkan (Panelmenu)
     * @param induk komponen yang kursornya diubah saat form dibuka
     * @param area ukuran dan posisi form yang dibuka (PanelUtama)
     * @param tutup dijalankan sebelum form dibuka (isTutup + DlgHome.dispose)
     */
    public MenuUtama(Container panel,Component induk,Component area,Runnable tutup){
        this.panel=panel;
        this.induk=induk;
        this.area=area;
        this.tutup=tutup;
    }

    /** Kategori (indeks cmbMenu) untuk entri yang ditambahkan sesudahnya. */
    public void kategori(int kategori){
        this.kategori=kategori;
    }

    /**
     * Tombol baru di kategori aktif.
     * @param ikon nama file di /48x48
     * @param akses null berarti selalu tampil
     */
    public Entri tambah(String nama,String teks,String ikon,BooleanSupplier akses){
        if(perNama.containsKey(nama)){
            throw new IllegalArgumentException("Menu "+nama+" sudah ada");
        }
        ButtonBig tombol=new ButtonBig();
        tombol.setIcon(new ImageIcon(getClass().getResource("/48x48/"+ikon)));
        tombol.setText(teks);
        tombol.setIconTextGap(0);
        tombol.setName(nama);
        tombol.setPreferredSize(new Dimension(200,90));
        Entri entri=new Entri(tombol,akses,kategori);
        daftar.add(entri);
        perNama.put(nama,entri);
        return entri;
    }

    /** Tombol yang sudah didaftarkan ikut tampil di kategori aktif. */
    public void juga(String nama){
        Entri asal=entri(nama);
        daftar.add(new Entri(asal.tombol,asal.akses,kategori));
    }

    public ButtonBig tombol(String nama){
        return entri(nama).tombol;
    }

    private Entri entri(String nama){
        Entri entri=perNama.get(nama);
        if(entri==null){
            throw new IllegalArgumentException("Menu "+nama+" tidak ada");
        }
        return entri;
    }

    /** Isi panel dengan menu satu kategori, hasilnya jumlah tombol. */
    public int tampilKategori(int kategori){
        return tampil(kategori,null);
    }

    public int tampilSemua(){
        return tampil(-1,null);
    }

    /** Menu semua kategori yang teksnya memuat kata (tidak peka huruf besar). */
    public int tampilCari(String kata){
        return tampil(-1,kata.toLowerCase().trim());
    }

    private int tampil(int kategori,String kata){
        int jml=0;
        for(Entri entri:daftar){
            if((kategori<0||entri.kategori==kategori)&&entri.boleh()&&
                    (kata==null||entri.tombol.getText().toLowerCase().trim().contains(kata))){
                // tombol di lebih dari satu kategori cukup dihitung sekali
                if(entri.tombol.getParent()!=panel){
                    jml++;
                }
                panel.add(entri.tombol);
            }
        }
        return jml;
    }

    public final class Entri {
        private final ButtonBig tombol;
        private final BooleanSupplier akses;
        private final int kategori;

        private Entri(ButtonBig tombol,BooleanSupplier akses,int kategori){
            this.tombol=tombol;
            this.akses=akses;
            this.kategori=kategori;
        }

        private boolean boleh(){
            return akses==null||akses.getAsBoolean();
        }

        /** Aksi khusus saat tombol diklik. */
        public void aksi(ActionListener aksi){
            tombol.addActionListener(aksi);
        }

        /** Klik tombol membuka form dari buat, seukuran area. */
        public <T extends Window> Pembuka<T> buka(Supplier<T> buat){
            Pembuka<T> pembuka=new Pembuka<>(buat);
            tombol.addActionListener(pembuka);
            return pembuka;
        }
    }

    public final class Pembuka<T extends Window> implements ActionListener {
        private final Supplier<T> buat;
        private Consumer<? super T> sebelum,sesudah;

        private Pembuka(Supplier<T> buat){
            this.buat=buat;
        }

        /** Dijalankan sebelum form ditampilkan, misalnya isCek(). */
        public Pembuka<T> sebelum(Consumer<? super T> sebelum){
            this.sebelum=sebelum;
            return this;
        }

        /** Dijalankan sesudah setVisible(true). */
        public Pembuka<T> sesudah(Consumer<? super T> sesudah){
            this.sesudah=sesudah;
            return this;
        }

        @Override
        public void actionPerformed(ActionEvent evt){
            tutup.run();
            induk.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            T form=buat.get();
            if(sebelum!=null){
                sebelum.accept(form);
            }
            form.setSize(area.getWidth(),area.getHeight());
            form.setLocationRelativeTo(area);
            form.setVisible(true);
            if(sesudah!=null){
                sesudah.accept(form);
            }
            induk.setCursor(Cursor.getDefaultCursor());
        }
    }
}
