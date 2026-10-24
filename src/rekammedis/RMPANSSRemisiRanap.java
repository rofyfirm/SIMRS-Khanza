/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;


/**
 *
 * @author perpustakaan
 */
public final class RMPANSSRemisiRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private volatile boolean ceksukses = false;
    private String TANGGALMUNDUR="yes";
    private final widget.ComboBox[] skor;
    private final widget.Label[] keterangan;
    private final String[] butir={"p1","p2","p3","n1","n4","n6","g5","g9"};
    private final String[][] uraian={
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Ada satu atau dua waham yang samar-samar, tidak terkristalisasi, dan tidak bertahan. Waham tidak mempengaruhi proses pikir, relasi sosial atau perilaku.","Sedang : Adanya rangkaian waham yang bentuknya kurang jelas dan tidak stabil atau beberapa waham yang berbentuk jelas, yang kadang-kadang mempengaruhi proses pikir, relasi sosial atau perilaku.","Agak berat : Adanya beberapa waham yang berbentuk jelas, yang dipertahankan dan kadang-kadang mempengaruhi proses pikir, relasi sosial atau perilaku.","Berat : Adanya suatu susunan waham yang stabil, yang terkristalisasi, mungkin sistematik, dipertahankan, dan jelas mempengaruhi proses pikir, relasi sosial dan perilaku.","Sangat berat : Adanya suatu susunan waham yang stabil, sangat sistemik, atau sangat banyak, dan yang mendominasi bidang (facet) utama kehidupan pasien. Sering kali mengakibatkan tindakan yang tidak serasi dan tidak bertanggung jawab, yang bahkan membahayakan keamanan pasien atau orang lain."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Proses pikir sirkumstansial, tangensial atau paralogikal. Adanya kesulitan dalam mengarahkan pikiran ke tujuan dan kadang-kadang asosiasi longgar dapat dijumpai di bawah tekanan.","Sedang : Mampu memusatkan pikiran bila komunikasi singkat dan terstruktur, tetapi menjadi longgar atau tidak relevan bila menghadapi komunikasi yang lebih kompleks atau bila di bawah tekanan minimal.","Agak berat : Secara umum mengalami kesulitan dalam menata pikiran yang terbukti dalam bentuk sering tidak relevan, tidak ada hubungan, atau asosiasi longgar bahkan walaupun tanpa tekanan.","Berat : Proses pikir (thinking) sangat menyimpang dan pada dasarnya (internally) tidak konsisten, mengakibatkan tidak relevan yang parah dan kekacauan proses pikir, yang hampir terjadi terus menerus.","Sangat berat : Pikiran (thought) sangat kacau sehingga menjadi inkoheren. Asosiasi longgar yang sangat jelas, yang mengakibatkan kegagalan total dalam komunikasi, misalnya: \"word-salad\" atau mutisme."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Satu atau dua halusinasi yang jelas tetapi jarang timbul, atau beberapa abnormalitas persepsi yang samar-samar lainnya yang tidak mengakibatkan penyimpangan (distorsi) proses pikir atau perilaku.","Sedang : Sering ada halusinasi tetapi tidak terus menerus, dan proses pikir serta perilaku pasien hanya sedikit terpengaruh.","Agak berat : Halusinasi sering, dapat meliputi lebih dari satu organ sensoris dan cenderung menyimpangkan proses pikir dan/atau mengacaukan perilaku. Pasien dapat memiliki interpretasi bersifat waham atas pengalamannya ini dan bereaksi terhadapnya secara emosional, serta kadang-kadang juga secara verbal.","Berat : Halusinasi hampir terus menerus ada, mengakibatkan kekacauan berat pada proses pikir dan perilaku. Pasien menganggapnya sebagai persepsi nyata dan fungsinya terganggu oleh seringnya bereaksi secara emosional dan verbal terhadapnya.","Sangat berat : Pasien hampir secara total mengalami preokupasi dengan halusinasi, yang jelas mendominasi proses pikir dan perilaku. Halusinasi diikuti interpretasi bersifat waham yang kaku dan memacu timbulnya respons verbal dan perilaku, termasuk kepatuhan terhadap halusinasi perintah."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Perubahan ekspresi wajah dan gerak gerik komunikatif tampak kaku, dipaksakan, dibuat-buat atau kurangnya gelombang.","Sedang : Berkurangnya corak ekspresi wajah dan sedikitnya gerak gerik ekspresif yang tampak dalam penampilan yang tumpul (dull).","Agak berat : Afek umumnya datar dengan hanya sekali-sekali tampak perubahan ekspresi wajah dan gerak gerik komunikatif sedikit.","Berat : Pendataran dan defisiensi emosi yang mencolok yang tampak hampir sepanjang waktu. Kemungkinan terdapat pelepasan afek ekstrim yang tidak bergelombang seperti \"excitement\", kemarahan atau tertawa yang tidak terkendali yang tidak serasi.","Sangat berat : Jelas tidak tampak perubahan ekspresi wajah dan adanya gerak gerik komunikatif. Pasien terus menerus menampakkan ekspresi yang \"tidak hidup\" atau berwajah \"kayu\"."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Sesekali menunjukkan minat dalam aktivitas sosial, tetapi inisiatif sangat kurang. Biasanya keterlibatan dengan orang lain hanya bila \"didekati\" oleh orang lain tersebut.","Sedang : Secara pasif ikut dalam sebagian besar aktivitas sosial tetapi dengan cara \"ogah-ogahan\" (disinterested) atau secara mekanis. Cenderung untuk ada di baris belakang.","Agak berat : Secara pasif berpartisipasi dalam hanya sedikit aktivitas sosial dan menunjukkan jelas tidak ada minat atau inisiatif. Umumnya menyendiri.","Berat : Cenderung menjadi apatis dan terisolasi, sangat jarang berpartisipasi dalam aktivitas sosial dan sekali-kali mengabaikan kebutuhan pribadi, kontak sosial yang spontan sangat sedikit.","Sangat berat : Sangat apatis, terisolasi secara sosial dan sangat mengabaikan perawatan diri."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Menunjukkan sedikit inisiatif dalam percakapan, jawaban pasien cenderung singkat dan tanpa tambahan, membutuhkan pertanyaan langsung dan pengarahan dari pewawancara.","Sedang : Arus percakapan kurang bebas dan tidak lancar atau terhenti-henti. Pertanyaan terarah sering dibutuhkan untuk mendapatkan respons yang adekuat dan untuk melanjutkan percakapan.","Agak berat : Pasien menunjukkan berkurangnya spontanitas dan keterbukaan yang mencolok, menjawab pertanyaan pewawancara dengan hanya 1 atau 2 kalimat singkat.","Berat : Respons pasien hanya terbatas terutama pada beberapa kata atau kalimat pendek untuk menghindari atau mempersingkat komunikasi (misalnya \"saya tidak tahu\", \"saya sedang tidak bebas berbicara\"), akibatnya terdapat hendaya berat dalam percakapan, dan wawancara sangat tidak produktif.","Sangat berat : Kata-kata yang diucapkan sangat terbatas, paling banter sekali-kali ada ungkapan sehingga percakapan tidak mungkin terjadi."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batas-batas normal.","Ringan : Kejanggalan ringan dalam pergerakan atau kekakuan sikap tubuh yang ringan.","Sedang : Gerakan janggal yang khusus atau terputus-putus, atau sikap tubuh tidak wajar yang dipertahankan untuk periode yang singkat.","Agak berat : Sesekali tampak ritual bizar, atau sikap tubuh yang berubah-ubah (contorted), atau suatu posisi abnormal yang dipertahankan terus menerus untuk waktu yang agak lama.","Berat : Sering tampak pengulangan ritual bizar, mannerisme, atau gerak-gerakan stereotipik, atau sikap tubuh yang berubah-ubah yang dipertahankan terus menerus untuk waktu yang agak lama.","Sangat berat : Fungsi terganggu berat oleh keterlibatan terus menerus yang jelas dalam ritual, mannerisme atau gerakan-gerakan stereotipik atau suatu sikap tubuh tidak wajar tertentu yang dipertahankan terus menerus dalam sebagian besar waktu."},
        {"Tidak ada : Definisi tidak dipenuhi.","Minimal : Patologis diragukan; mungkin suatu ujung ekstrim dari batasan normal.","Ringan : Isi pikir ganjil atau idiosinkratik, atau ide yang lazim dalam konteks yang aneh.","Sedang : Ide-ide seringkali mengalami distorsi dan sekali-sekali cukup bizar.","Agak berat : Pasien mengekspresikan banyak pikiran-pikiran asing dan fantastik (misalnya menjadi anak angkat raja, orang yang berhasil lolos dalam kematian) atau beberapa pikiran yang sangat tidak masuk akal (misalnya mempunyai ratusan anak, menerima pesan radio dari angkasa luar melalui sebuah tambalan gigi).","Berat : Pasien mengekspresikan banyak ide yang tidak logis atau tidak masuk akal atau beberapa ide yang jelas berkualitas bizar (misalnya mempunyai 3 kepala, menjadi seorang pengunjung dari planet lain).","Sangat berat : Proses pikir dipenuhi dengan ide-ide yang tidak masuk akal (absurd), bizar, dan aneh sekali (grotesque)."}
    };
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPANSSRemisiRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        skor=new widget.ComboBox[]{P1,P2,P3,N1,N4,N6,G5,G9};
        keterangan=new widget.Label[]{KetP1,KetP2,KetP3,KetN1,KetN4,KetN6,KetG5,KetG9};

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Umur","JK","Tgl.Lahir","Jenis","Tanggal","Jam",
            "P1","P2","P3","N1","N4","N6","G5","G9",
            "Total","Kode Dokter","Nama Dokter"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 20; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(35);
            }else if(i==4){
                column.setPreferredWidth(20);
            }else if(i==5){
                column.setPreferredWidth(65);
            }else if(i==6){
                column.setPreferredWidth(50);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(60);
            }else if(i<17){
                column.setPreferredWidth(30);
            }else if(i==17){
                column.setPreferredWidth(40);
            }else if(i==18){
                column.setPreferredWidth(90);
            }else if(i==19){
                column.setPreferredWidth(160);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));

        ChkInput.setSelected(false);
        isForm();
        jam();
        hitungTotal();

        try {
            TANGGALMUNDUR=koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR="yes";
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new widget.TextBox();
        Umur = new widget.TextBox();
        TanggalRegistrasi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tanggal = new widget.Tanggal();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel5 = new widget.Label();
        Jenis = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel22 = new widget.Label();
        P1 = new widget.ComboBox();
        KetP1 = new widget.Label();
        jLabel23 = new widget.Label();
        P2 = new widget.ComboBox();
        KetP2 = new widget.Label();
        jLabel24 = new widget.Label();
        P3 = new widget.ComboBox();
        KetP3 = new widget.Label();
        jLabel25 = new widget.Label();
        N1 = new widget.ComboBox();
        KetN1 = new widget.Label();
        jLabel26 = new widget.Label();
        N4 = new widget.ComboBox();
        KetN4 = new widget.Label();
        jLabel27 = new widget.Label();
        N6 = new widget.ComboBox();
        KetN6 = new widget.Label();
        jLabel28 = new widget.Label();
        G5 = new widget.ComboBox();
        KetG5 = new widget.Label();
        jLabel29 = new widget.Label();
        G9 = new widget.ComboBox();
        KetG9 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new widget.Label();
        TotalSkor = new widget.TextBox();
        ChkInput = new widget.CekBox();

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ PANSS Remisi Ranap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-02-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-02-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 410));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(1160, 545));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 146, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 70, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2026" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(74, 40, 90, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(168, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(233, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(298, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(363, 40, 23, 23);

        jLabel18.setText("Dokter :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(474, 40, 94, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(570, 40, 187, 23);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("ALt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        btnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterKeyPressed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(761, 40, 28, 23);

        jLabel5.setText("Jenis :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 70, 70, 23);

        Jenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masuk", "Keluar" }));
        Jenis.setName("Jenis"); // NOI18N
        Jenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisKeyPressed(evt);
            }
        });
        FormInput.add(Jenis);
        Jenis.setBounds(74, 70, 90, 23);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Skor : 1 = Tidak Ada, 2 = Minimal, 3 = Ringan, 4 = Sedang, 5 = Agak Berat, 6 = Berat, 7 = Sangat Berat");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(180, 70, 780, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 100, 1160, 1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("P1 - Waham :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(10, 106, 370, 23);

        P1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        P1.setName("P1"); // NOI18N
        P1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        P1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(P1);
        P1.setBounds(384, 106, 70, 23);

        KetP1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetP1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetP1.setName("KetP1"); // NOI18N
        FormInput.add(KetP1);
        KetP1.setBounds(460, 110, 690, 44);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("P2 - Kekacauan Proses Pikir :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(10, 156, 370, 23);

        P2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        P2.setName("P2"); // NOI18N
        P2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        P2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(P2);
        P2.setBounds(384, 156, 70, 23);

        KetP2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetP2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetP2.setName("KetP2"); // NOI18N
        FormInput.add(KetP2);
        KetP2.setBounds(460, 160, 690, 44);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("P3 - Perilaku Halusinasi :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(10, 206, 370, 23);

        P3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        P3.setName("P3"); // NOI18N
        P3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        P3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(P3);
        P3.setBounds(384, 206, 70, 23);

        KetP3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetP3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetP3.setName("KetP3"); // NOI18N
        FormInput.add(KetP3);
        KetP3.setBounds(460, 210, 690, 44);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("N1 - Afek Tumpul :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(10, 256, 370, 23);

        N1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        N1.setName("N1"); // NOI18N
        N1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        N1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(N1);
        N1.setBounds(384, 256, 70, 23);

        KetN1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetN1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetN1.setName("KetN1"); // NOI18N
        FormInput.add(KetN1);
        KetN1.setBounds(460, 260, 690, 44);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("N4 - Penarikan Diri dari Hubungan Sosial secara Pasif/Apatis :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(10, 306, 370, 23);

        N4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        N4.setName("N4"); // NOI18N
        N4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        N4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(N4);
        N4.setBounds(384, 306, 70, 23);

        KetN4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetN4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetN4.setName("KetN4"); // NOI18N
        FormInput.add(KetN4);
        KetN4.setBounds(460, 310, 690, 44);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("N6 - Kurangnya Spontanitas dan Arus Percakapan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(10, 356, 370, 23);

        N6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        N6.setName("N6"); // NOI18N
        N6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        N6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(N6);
        N6.setBounds(384, 356, 70, 23);

        KetN6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetN6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetN6.setName("KetN6"); // NOI18N
        FormInput.add(KetN6);
        KetN6.setBounds(460, 360, 690, 44);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("G5 - Mannerisme dan Sikap Tubuh :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(10, 406, 370, 23);

        G5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        G5.setName("G5"); // NOI18N
        G5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        G5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(G5);
        G5.setBounds(384, 406, 70, 23);

        KetG5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetG5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetG5.setName("KetG5"); // NOI18N
        FormInput.add(KetG5);
        KetG5.setBounds(460, 410, 690, 44);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("G9 - Isi Pikiran yang Tidak Biasa :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(10, 456, 370, 23);

        G9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        G9.setName("G9"); // NOI18N
        G9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkorItemStateChanged(evt);
            }
        });
        G9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkorKeyPressed(evt);
            }
        });
        FormInput.add(G9);
        G9.setBounds(384, 456, 70, 23);

        KetG9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KetG9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        KetG9.setName("KetG9"); // NOI18N
        FormInput.add(KetG9);
        KetG9.setBounds(460, 460, 690, 44);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 506, 1160, 1);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Total Skor :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(10, 512, 370, 23);

        TotalSkor.setEditable(false);
        TotalSkor.setHighlighter(null);
        TotalSkor.setName("TotalSkor"); // NOI18N
        FormInput.add(TotalSkor);
        TotalSkor.setBounds(384, 512, 70, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KdDokter.getText().trim().equals("")||NmDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else{
            if(akses.getkode().equals("Admin Utama")){
                simpan();
            }else{
                if(TanggalRegistrasi.getText().equals("")){
                    TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
                }
                if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem())==true){
                    simpan();
                }
            } 
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,G9,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
                    if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+" "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString(),Sequel.ambiltanggalsekarang())==true){
                        hapus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KdDokter.getText().trim().equals("")||NmDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else{ 
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
                        if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+" "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString(),Sequel.ambiltanggalsekarang())==true){
                            if(TanggalRegistrasi.getText().equals("")){
                                TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
                            }
                            if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem())==true){
                                ganti();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tbObat.getSelectedRow()>-1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String norawat=tbObat.getValueAt(tbObat.getSelectedRow(),0).toString();
            String kddpjp=Sequel.cariIsi("select panss_remisi_ranap.kd_dokter from panss_remisi_ranap where panss_remisi_ranap.no_rawat=? order by field(panss_remisi_ranap.jenis,'Masuk','Keluar') limit 1",norawat);
            String tgldpjp=Sequel.cariIsi("select panss_remisi_ranap.tgl_perawatan from panss_remisi_ranap where panss_remisi_ranap.no_rawat=? order by field(panss_remisi_ranap.jenis,'Masuk','Keluar') limit 1",norawat);
            String nmdpjp=Sequel.CariDokter(kddpjp);
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            String finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",kddpjp);
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+nmdpjp+"\nID "+(finger.equals("")?kddpjp:finger)+"\n"+Valid.SetTgl3(tgldpjp));
            param.put("namadokter",nmdpjp);
            Valid.MyReportqry("rptFormulirPANSSRemisiRanap.jasper","report","::[ PANSS Remisi Ranap ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,masuk.tgl_perawatan as tgl_masuk,keluar.tgl_perawatan as tgl_keluar,"+
                    "masuk.p1 as p1_masuk,masuk.p2 as p2_masuk,masuk.p3 as p3_masuk,masuk.n1 as n1_masuk,masuk.n4 as n4_masuk,masuk.n6 as n6_masuk,masuk.g5 as g5_masuk,masuk.g9 as g9_masuk,"+
                    "keluar.p1 as p1_keluar,keluar.p2 as p2_keluar,keluar.p3 as p3_keluar,keluar.n1 as n1_keluar,keluar.n4 as n4_keluar,keluar.n6 as n6_keluar,keluar.g5 as g5_keluar,keluar.g9 as g9_keluar from reg_periksa "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "left join panss_remisi_ranap masuk on masuk.no_rawat=reg_periksa.no_rawat and masuk.jenis='Masuk' "+
                    "left join panss_remisi_ranap keluar on keluar.no_rawat=reg_periksa.no_rawat and keluar.jenis='Keluar' "+
                    "where reg_periksa.no_rawat='"+norawat+"'",param);
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        runBackground(() ->tampil());
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        runBackground(() ->tampil());
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            runBackground(() ->tampil());
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnDokter);
    }//GEN-LAST:event_DetikKeyPressed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NmDokter.setText(Sequel.CariDokter(KdDokter.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Detik.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Jenis.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokterActionPerformed(null);
        }
    }//GEN-LAST:event_KdDokterKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        if (dokter == null || !dokter.isDisplayable()) {
            dokter=new DlgCariDokter(null,false);
            dokter.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dokter.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if(dokter.getTable().getSelectedRow()!= -1){
                        KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    }   
                    KdDokter.requestFocus(); 
                    dokter=null;
                }
            });

            dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dokter.setLocationRelativeTo(internalFrame1);
        }
            
        if (dokter == null) return;
        dokter.isCek();
        if (dokter.isVisible()) {
            dokter.toFront();
            return;
        }    
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void btnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterKeyPressed
        Valid.pindah(evt,Detik,Jenis);
    }//GEN-LAST:event_btnDokterKeyPressed

    private void JenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisKeyPressed
        Valid.pindah(evt,btnDokter,P1);
    }//GEN-LAST:event_JenisKeyPressed

    private void SkorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkorKeyPressed
        for(int k=0;k<skor.length;k++){
            if(evt.getSource()==skor[k]){
                if(k==0){
                    Valid.pindah(evt,Jenis,skor[k+1]);
                }else if(k==skor.length-1){
                    Valid.pindah(evt,skor[k-1],BtnSimpan);
                }else{
                    Valid.pindah(evt,skor[k-1],skor[k+1]);
                }
                break;
            }
        }
    }//GEN-LAST:event_SkorKeyPressed

    private void SkorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkorItemStateChanged
        hitungTotal();
    }//GEN-LAST:event_SkorItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        runBackground(() ->tampil());
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        runBackground(() ->tampil());
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        runBackground(() ->tampil());
                    }
                }
            });
        }
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPANSSRemisiRanap dialog = new RMPANSSRemisiRanap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox G5;
    private widget.ComboBox G9;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.ComboBox Jenis;
    private widget.TextBox KdDokter;
    private widget.Label KetG5;
    private widget.Label KetG9;
    private widget.Label KetN1;
    private widget.Label KetN4;
    private widget.Label KetN6;
    private widget.Label KetP1;
    private widget.Label KetP2;
    private widget.Label KetP3;
    private widget.Label LCount;
    private widget.ComboBox Menit;
    private widget.ComboBox N1;
    private widget.ComboBox N4;
    private widget.ComboBox N6;
    private widget.TextBox NmDokter;
    private widget.ComboBox P1;
    private widget.ComboBox P2;
    private widget.ComboBox P3;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextBox TglLahir;
    private widget.TextBox TotalSkor;
    private widget.TextBox Umur;
    private widget.Button btnDokter;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel13;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,pasien.jk,pasien.tgl_lahir,"+
                "panss_remisi_ranap.jenis,panss_remisi_ranap.tgl_perawatan,panss_remisi_ranap.jam_rawat,"+
                "panss_remisi_ranap.p1,panss_remisi_ranap.p2,panss_remisi_ranap.p3,panss_remisi_ranap.n1,panss_remisi_ranap.n4,panss_remisi_ranap.n6,panss_remisi_ranap.g5,panss_remisi_ranap.g9,"+
                "panss_remisi_ranap.kd_dokter,dokter.nm_dokter from panss_remisi_ranap "+
                "inner join reg_periksa on panss_remisi_ranap.no_rawat=reg_periksa.no_rawat "+
                "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join dokter on panss_remisi_ranap.kd_dokter=dokter.kd_dokter where "+
                "panss_remisi_ranap.tgl_perawatan between ? and ? "+
                (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or panss_remisi_ranap.kd_dokter like ? or dokter.nm_dokter like ?) ")+
                "order by panss_remisi_ranap.tgl_perawatan,panss_remisi_ranap.jam_rawat");

            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }

                rs=ps.executeQuery();
                while(rs.next()){
                    Object[] data=new Object[20];
                    data[0]=rs.getString("no_rawat");
                    data[1]=rs.getString("no_rkm_medis");
                    data[2]=rs.getString("nm_pasien");
                    data[3]=rs.getString("umurdaftar")+" "+rs.getString("sttsumur");
                    data[4]=rs.getString("jk");
                    data[5]=rs.getDate("tgl_lahir");
                    data[6]=rs.getString("jenis");
                    data[7]=rs.getString("tgl_perawatan");
                    data[8]=rs.getString("jam_rawat");
                    int total=0;
                    for(int k=0;k<butir.length;k++){
                        data[k+9]=rs.getString(butir[k]);
                        total=total+Integer.parseInt(rs.getString(butir[k]));
                    }
                    data[17]=""+total;
                    data[18]=rs.getString("kd_dokter");
                    data[19]=rs.getString("nm_dokter");
                    tabMode.addRow(data);
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        kosongkanSkor();
        Jenis.setSelectedIndex(0);
        ChkKejadian.setSelected(true);
        Tanggal.setDate(new Date());
        Jenis.requestFocus();
    }

    private void kosongkanSkor() {
        for(widget.ComboBox item:skor){
            item.setSelectedIndex(0);
        }
        hitungTotal();
    }

    private void hitungTotal() {
        try {
            int jumlah=0;
            for(int k=0;k<skor.length;k++){
                jumlah=jumlah+Integer.parseInt(skor[k].getSelectedItem().toString());
                keterangan[k].setText("<html>"+uraian[k][skor[k].getSelectedIndex()]+"</html>");
                skor[k].setToolTipText(uraian[k][skor[k].getSelectedIndex()]);
            }
            TotalSkor.setText(""+jumlah);
        } catch (Exception e) {
            TotalSkor.setText("");
        }
    }

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            Jenis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(0,2));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(3,5));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(6,8));
            for(int k=0;k<skor.length;k++){
                skor[k].setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),k+9).toString());
            }
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            ChkKejadian.setSelected(false);
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.umurdaftar,"+
                    "reg_periksa.sttsumur,reg_periksa.jam_reg from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umurdaftar")+" "+rs.getString("sttsumur"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi")+" "+rs.getString("jam_reg"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
        runBackground(() ->tampil());
    }

    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>585){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,410));
                FormInput.setVisible(true);
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-175));
                FormInput.setVisible(true);
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_medis_ranap());
        BtnHapus.setEnabled(akses.getpenilaian_awal_medis_ranap());
        BtnEdit.setEnabled(akses.getpenilaian_awal_medis_ranap());
        BtnPrint.setEnabled(akses.getpenilaian_awal_medis_ranap()); 
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            btnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(Sequel.CariDokter(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan dokter...!!");
            }
        }   
        
        if(TANGGALMUNDUR.equals("no")){
            if(!akses.getkode().equals("Admin Utama")){
                Tanggal.setEditable(false);
                Tanggal.setEnabled(false);
                ChkKejadian.setEnabled(false);
                Jam.setEnabled(false);
                Menit.setEnabled(false);
                Detik.setEnabled(false);
            }
        }
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private String[] isiData() {
        String[] isi=new String[13];
        isi[0]=TNoRw.getText();
        isi[1]=Jenis.getSelectedItem().toString();
        isi[2]=Valid.SetTgl(Tanggal.getSelectedItem()+"");
        isi[3]=Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem();
        for(int k=0;k<skor.length;k++){
            isi[k+4]=skor[k].getSelectedItem().toString();
        }
        isi[12]=KdDokter.getText();
        return isi;
    }

    private Object[] barisTabel() {
        Object[] data=new Object[20];
        data[0]=TNoRw.getText();
        data[1]=TNoRM.getText();
        data[2]=TPasien.getText();
        data[3]=Umur.getText();
        data[4]=JK.getText();
        data[5]=TglLahir.getText();
        data[6]=Jenis.getSelectedItem().toString();
        data[7]=Valid.SetTgl(Tanggal.getSelectedItem()+"");
        data[8]=Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem();
        for(int k=0;k<skor.length;k++){
            data[k+9]=skor[k].getSelectedItem().toString();
        }
        data[17]=TotalSkor.getText();
        data[18]=KdDokter.getText();
        data[19]=NmDokter.getText();
        return data;
    }

    private void ganti() {
        String[] isi=new String[15];
        System.arraycopy(isiData(),0,isi,0,13);
        isi[13]=tbObat.getValueAt(tbObat.getSelectedRow(),0).toString();
        isi[14]=tbObat.getValueAt(tbObat.getSelectedRow(),6).toString();
        if(Sequel.mengedittf("panss_remisi_ranap","no_rawat=? and jenis=?","no_rawat=?,jenis=?,tgl_perawatan=?,jam_rawat=?,"+
            "p1=?,p2=?,p3=?,n1=?,n4=?,n6=?,g5=?,g9=?,kd_dokter=?",15,isi)==true){
            Object[] data=barisTabel();
            for(int k=0;k<data.length;k++){
                tbObat.setValueAt(data[k],tbObat.getSelectedRow(),k);
            }
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from panss_remisi_ranap where no_rawat=? and jenis=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void simpan() {
        if(Sequel.menyimpantf("panss_remisi_ranap","?,?,?,?,?,?,?,?,?,?,?,?,?","Jenis Masuk/Keluar",13,isiData())==true){
            tabMode.addRow(barisTabel());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }
    }

    private void runBackground(Runnable task) {
        if (ceksukses) return;
        if (executor.isShutdown() || executor.isTerminated()) return;
        if (!isDisplayable()) return;

        ceksukses = true;
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            executor.submit(() -> {
                try {
                    task.run();
                } finally {
                    ceksukses = false;
                    SwingUtilities.invokeLater(() -> {
                        if (isDisplayable()) {
                            setCursor(Cursor.getDefaultCursor());
                        }
                    });
                }
            });
        } catch (RejectedExecutionException ex) {
            ceksukses = false;
        }
    }
    
    @Override
    public void dispose() {
        executor.shutdownNow();
        super.dispose();
    }
}
