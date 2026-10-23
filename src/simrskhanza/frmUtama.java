/*
  Dilarang keras memperjualbelikan/mengambil keuntungan dari Software 
  ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package simrskhanza;

import kepegawaian.DlgPenggajian;
import laporan.DlgMutasiBerkas;
import laporan.DlgBerkasRawat;
import laporan.DlgRetensi;
import inventory.DlgResepObat;
import bridging.INACBGCariCoderNIK;
import bridging.INACBGHybrid;
import informasi.InformasiAnalisaKamin;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import informasi.InformasiJadwal;
import informasi.InformasiKamar;
import informasi.InformasiKamarInap;
import informasi.InformasiTarifLab;
import informasi.InformasiTarifOperasi;
import informasi.InformasiTarifRadiologi;
import informasi.InformasiTarifRalan;
import informasi.InformasiTarifRanap;
import inventory.DlgCariPenjualan;
import inventory.DlgPenjualan;
import inventory.DlgReturJual;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import informasi.InformasiTelusurKunjunganPasien;
import inventory.DlgDaftarPermintaanResep;
import inventory.DlgPengeluaranApotek;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import laporan.frmPengaduan;
import kepegawaian.DlgBelum;
import kepegawaian.DlgBulanan2;
import kepegawaian.DlgKegiatanIlmiah;
import kepegawaian.DlgKehadiran;
import kepegawaian.DlgPulang;
import kepegawaian.DlgRiwayatJabatan;
import kepegawaian.DlgRiwayatNaikGaji;
import kepegawaian.DlgRiwayatPendidikan;
import kepegawaian.DlgRiwayatPenelitian;
import kepegawaian.DlgRiwayatPenghargaan;
import informasi.InformasiStokDarah;
import fungsi.cacheigd;
import fungsi.cacherawatinap;
import fungsi.cacherawatjalan;
import fungsi.cacheregistrasi;
import java.net.InetAddress;
import kepegawaian.PengajuanCutiPegawai;
import kepegawaian.DlgRiwayatSuratPeringatan;
import permintaan.DlgBookingPeriksa;
import perpustakaan.PerpustakaanCariEbook;
import perpustakaan.PerpustakaanCariInventaris;
import perpustakaan.PerpustakaanEbook;
import perpustakaan.PerpustakaanPenelitian;
import setting.WindowInputPassword;
import smsui.frmSmsView;
import tranfusidarah.UTDCariPenyerahanDarah;


/**
 *
 * @author perpustakaan
 */
public class frmUtama extends javax.swing.JFrame {
    private final Connection koneksi=koneksiDB.condb();
    private final sekuel Sequel=new sekuel();
    private final validasi Valid=new validasi();
    private final INACBGHybrid inacbgklaim=new INACBGHybrid(this,false);
    private final INACBGCariCoderNIK cariNIK=new INACBGCariCoderNIK(this,false);
    private static frmUtama myInstance;
    private PreparedStatement ps;
    private ResultSet rs;
    private final Properties prop = new Properties();  
    private int jmlmenu=0,grid=0,tinggi=0,i=0,menuawal=0;
    private String coder_nik="",pilihpage="",judulform="",tampilkantni=Sequel.cariIsi("select set_tni_polri.tampilkan_tni_polri from set_tni_polri"),
            AKTIFKANTRACKSQL=koneksiDB.AKTIFKANTRACKSQL();
    
    private frmUtama() {
        super();
        initComponents();
        initMenu();
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/yaski24.png")).getImage());
        
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.setSize(screen.width,screen.height);
        edAdmin.setDocument(new batasInput((byte)100).getKata(edAdmin));
        edPwd.setDocument(new batasInput((byte)100).getKata(edPwd));
        DlgLogin.setSize(299,180);
        DlgLogin.setLocationRelativeTo(null);
        jMenu1.setOpaque(false);
        jMenu5.setOpaque(false);
        jMenu6.setOpaque(false);
        jMenu7.setOpaque(false);
        jMenu4.setOpaque(false);
        
        lblTgl.setText(tanggal.getSelectedItem().toString());
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("Notif Setting : "+e);
        }
        
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            LblIP.setText(""+inetAddress.getHostAddress());
            akses.setalamatip(""+inetAddress.getHostAddress());
        } catch (Exception e) {
            System.out.println("Notif IP : "+e);
        }
        
        FlayMenu.setVisible(false);
        TCari.setVisible(false);
        
        try{    
            if(prop.getProperty("MENUTRANSPARAN").equals("yes")){
                DlgHome.setOpacity(0.7f);
                DlgLogin.setOpacity(0.74f);
            }     
        }catch(Exception e){            
        } 
    }
    
    public static frmUtama getInstance() {
        if (myInstance == null) 
            myInstance = new frmUtama();

        return myInstance;
    }
     

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DlgLogin = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        internalFrame3 = new widget.InternalFrame();
        panelGlass1 = new usu.widget.glass.PanelGlass();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edAdmin = new widget.PasswordBox();
        edPwd = new widget.PasswordBox();
        BtnLogin = new widget.Button();
        BtnCancel = new widget.Button();
        jLabel6 = new javax.swing.JLabel();
        DlgHome = new javax.swing.JDialog();
        panelMenu = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        label36 = new widget.Label();
        button1 = new widget.Button();
        label35 = new widget.Label();
        cmbMenu = new widget.ComboBox();
        TCari = new widget.TextBox();
        ChkInput = new widget.CekBox();
        scrollPane2 = new widget.ScrollPane();
        Panelmenu = new widget.panelGlass();
        tanggal = new widget.Tanggal();
        btnDataPenjualan = new widget.ButtonBig();
        btnInputPenjualan = new widget.ButtonBig();
        btnDataPenyerahanDarah = new widget.ButtonBig();
        btnResepObatDepan = new widget.ButtonBig();
        internalFrame1 = new widget.InternalFrame();
        BtnMenu = new widget.ButtonBig();
        jSeparator4 = new javax.swing.JSeparator();
        BtnToolReg = new widget.ButtonBig();
        btnToolIGD = new widget.ButtonBig();
        jSeparator5 = new javax.swing.JSeparator();
        btnToolLab = new widget.ButtonBig();
        btnToolRad = new widget.ButtonBig();
        BtnToolJualObat = new widget.ButtonBig();
        jSeparator9 = new javax.swing.JSeparator();
        BtnToolKamnap = new widget.ButtonBig();
        BtnToolKasir = new widget.ButtonBig();
        jSeparator7 = new javax.swing.JSeparator();
        BtnLog = new widget.ButtonBig();
        BtnClose = new widget.ButtonBig();
        internalFrame4 = new widget.InternalFrame();
        lblStts = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblUser = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTgl = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        LblIP = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        lblUser1 = new javax.swing.JLabel();
        PanelUtama = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        PanelWall = new usu.widget.glass.PanelGlass();
        panelJudul = new usu.widget.glass.PanelGlass();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FlayMenu = new usu.widget.glass.PanelGlass();
        MenuBar = new widget.MenuBar();
        jMenu1 = new javax.swing.JMenu();
        MnLogin = new javax.swing.JMenuItem();
        MnGantiPassword = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        MenuKeluar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MnBelumDatang1 = new javax.swing.JMenuItem();
        MnBelumDatang = new javax.swing.JMenuItem();
        MnSudahPulang = new javax.swing.JMenuItem();
        MnRekapBulanan = new javax.swing.JMenuItem();
        MnRekapHadir = new javax.swing.JMenuItem();
        MnPengajuanCutiPegawai = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        MnRekapBulanan1 = new javax.swing.JMenuItem();
        MnRekapBulanan3 = new javax.swing.JMenuItem();
        MnRekapBulanan2 = new javax.swing.JMenuItem();
        MnRekapBulanan4 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        MnTarif = new javax.swing.JMenu();
        MnSudahPulang1 = new javax.swing.JMenuItem();
        MnSudahPulang3 = new javax.swing.JMenuItem();
        MnSudahPulang6 = new javax.swing.JMenuItem();
        MnSudahPulang4 = new javax.swing.JMenuItem();
        MnSudahPulang2 = new javax.swing.JMenuItem();
        MnSudahPulang5 = new javax.swing.JMenuItem();
        MnTarif1 = new javax.swing.JMenu();
        MnRekapHadir1 = new javax.swing.JMenuItem();
        MnInfoBed = new javax.swing.JMenuItem();
        MnInfoBed2 = new javax.swing.JMenuItem();
        MnInfoBed1 = new javax.swing.JMenuItem();
        MnInfoBed3 = new javax.swing.JMenuItem();
        MnInfoBed5 = new javax.swing.JMenuItem();
        MnInfoMobileJKN = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        MnKoleksiPenelitian = new javax.swing.JMenuItem();
        MnCariEbook = new javax.swing.JMenuItem();
        MnCariInventarisPerpustakaan = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        MnAnjungan = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        MnRekapHadir9 = new javax.swing.JMenuItem();
        MnRekapHadir3 = new javax.swing.JMenuItem();
        MnRekapHadir10 = new javax.swing.JMenuItem();
        MnRekapHadir4 = new javax.swing.JMenuItem();
        MnInfoBed4 = new javax.swing.JMenuItem();
        MnAntrianFarmasi = new javax.swing.JMenu();
        MnRekapHadir5 = new javax.swing.JMenuItem();
        MnRekapHadir7 = new javax.swing.JMenuItem();
        MnRekapHadir8 = new javax.swing.JMenuItem();
        MnRekapHadir11 = new javax.swing.JMenuItem();
        MnAntrianLaborat = new javax.swing.JMenu();
        MnInfoLaborat = new javax.swing.JMenuItem();
        MnInfoLaborat1 = new javax.swing.JMenuItem();
        MnInfoLaborat3 = new javax.swing.JMenuItem();
        MnInfoLaborat2 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        MnRekapHadir6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        DlgLogin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgLogin.setName("DlgLogin"); // NOI18N
        DlgLogin.setUndecorated(true);
        DlgLogin.setResizable(false);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaAtas(new java.awt.Color(0, 168, 106));
        internalFrame2.setWarnaBawah(new java.awt.Color(0, 158, 96));
        internalFrame2.setLayout(null);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(100, 125, 90), 1, true), ":: Silahkan Anda Login ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setRequestFocusEnabled(false);
        internalFrame3.setWarnaAtas(new java.awt.Color(205, 240, 205));
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 255, 245));
        internalFrame3.setLayout(null);

        panelGlass1.setBackground(java.awt.Color.red);
        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 100)));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);
        panelGlass1.setWarna(new java.awt.Color(255, 255, 210));
        panelGlass1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(50, 50, 50));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID Admin :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass1.add(jLabel4);
        jLabel4.setBounds(2, 12, 75, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(50, 50, 50));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Password :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass1.add(jLabel5);
        jLabel5.setBounds(2, 40, 75, 23);

        edAdmin.setForeground(new java.awt.Color(50, 50, 50));
        edAdmin.setToolTipText("Silahkan masukkan ID Admin");
        edAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edAdmin.setName("edAdmin"); // NOI18N
        edAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edAdminKeyPressed(evt);
            }
        });
        panelGlass1.add(edAdmin);
        edAdmin.setBounds(80, 12, 193, 23);

        edPwd.setForeground(new java.awt.Color(50, 50, 50));
        edPwd.setToolTipText("Silahkan masukkan password");
        edPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edPwd.setName("edPwd"); // NOI18N
        edPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edPwdKeyPressed(evt);
            }
        });
        panelGlass1.add(edPwd);
        edPwd.setBounds(80, 40, 193, 23);

        internalFrame3.add(panelGlass1);
        panelGlass1.setBounds(-1, 25, 342, 76);

        BtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/lock.png"))); // NOI18N
        BtnLogin.setMnemonic('Z');
        BtnLogin.setText("Log-in");
        BtnLogin.setToolTipText("Alt+Z");
        BtnLogin.setName("BtnLogin"); // NOI18N
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnLogin);
        BtnLogin.setBounds(12, 110, 105, 32);

        BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnCancel.setMnemonic('Y');
        BtnCancel.setText("Batal");
        BtnCancel.setToolTipText("Alt+Y");
        BtnCancel.setName("BtnCancel"); // NOI18N
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnCancel);
        BtnCancel.setBounds(177, 110, 105, 32);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/LaST (Cobalt) Lock n Gear.png"))); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        internalFrame3.add(jLabel6);
        jLabel6.setBounds(105, 5, 135, 145);

        internalFrame2.add(internalFrame3);
        internalFrame3.setBounds(2, 12, 295, 155);

        DlgLogin.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        DlgHome.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgHome.setAlwaysOnTop(true);
        DlgHome.setBackground(new java.awt.Color(255, 253, 253));
        DlgHome.setIconImage(null);
        DlgHome.setName("DlgHome"); // NOI18N
        DlgHome.setUndecorated(true);
        DlgHome.setResizable(false);

        panelMenu.setBackground(new java.awt.Color(255, 253, 253));
        panelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(223, 233, 213)), "::[ Menu Utama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        panelMenu.setName("panelMenu"); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(2412, 3653));
        panelMenu.setWarnaAtas(new java.awt.Color(255, 252, 252));
        panelMenu.setWarnaBawah(new java.awt.Color(255, 252, 252));
        panelMenu.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(253, 255, 253));
        panelisi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 240, 230)));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        label36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(1, 23));
        panelisi2.add(label36);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        button1.setGlassColor(new java.awt.Color(255, 255, 255));
        button1.setMinimumSize(new java.awt.Dimension(28, 23));
        button1.setName("button1"); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(25, 23));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelisi2.add(button1);

        label35.setText("Tampilkan Menu :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(105, 23));
        panelisi2.add(label35);

        cmbMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[A] Registrasi, Tagihan Ranap & Ralan, Pelayanan & Billing Pasien", "[B] Tindakan & Obat & BHP Via Barcode, Lab Kesehatan Lingkungan", "[C] Presensi, Manajemen & Penggajian Pegawai Rumah Sakit", "[D] Transaksi Inventory Obat, BHP Medis, Alat Kesehatan Pasien", "[E] Transaksi Inventory Barang Non Medis, Penunjang ( Lab & RO )", "[F] Transaksi Inventory Barang Dapur Kering & Basah", "[G] Aset, Inventaris Barang & Instalasi Kesehatan Lingkungan", "[H] Menejemen Parkir Kendaraan Pasien & Karyawan", "[I] Olah Data Tagihan Rawat Inap & Rawat Jalan", "[J] Olah Data Penyakit, Laporan DKK, Laporal RL & Laporan Internal", "[K] Tarif Pelayanan, Menejemen Keuangan & Akuntansi", "[L] Bridging VClaim, Aplicare, PCare, INACBG, Kemenkes & Pihak Ke 3", "[M] Olah Data Rekam Medis Pasien", "[N] Unit Pelayanan Tranfusi Darah", "[O] Analisa, Dashboard & Info Grafik", "[P] Manajemen Surat Masuk & Keluar, Pengumuman E-Pasien", "[Q] Manajemen Perpustakaan & Koleksi Pustaka Digital", "[R] Pre-Construction & Infection Control Risk Assessment", "[S] Toko / Minimarket / Koperasi", "[T] Pengelolaan Data Filantropi, CSR, Zakat, Infaq & Shodaqoh", "[U] Pengaturan Program Aplikasi HMS" }));
        cmbMenu.setName("cmbMenu"); // NOI18N
        cmbMenu.setPreferredSize(new java.awt.Dimension(470, 23));
        cmbMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMenuItemStateChanged(evt);
            }
        });
        panelisi2.add(cmbMenu);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(470, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setOpaque(false);
        ChkInput.setPreferredSize(new java.awt.Dimension(25, 23));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        panelisi2.add(ChkInput);

        panelMenu.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 240, 230)));
        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setName("scrollPane2"); // NOI18N

        Panelmenu.setBorder(null);
        Panelmenu.setMinimumSize(new java.awt.Dimension(1975, 2826));
        Panelmenu.setName("Panelmenu"); // NOI18N
        Panelmenu.setLayout(new java.awt.GridLayout(0, 12));
        scrollPane2.setViewportView(Panelmenu);

        panelMenu.add(scrollPane2, java.awt.BorderLayout.CENTER);

        DlgHome.getContentPane().add(panelMenu, java.awt.BorderLayout.CENTER);

        tanggal.setEditable(false);
        tanggal.setForeground(new java.awt.Color(50, 70, 50));
        tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13/10/2026" }));
        tanggal.setDisplayFormat("dd/MM/yyyy");
        tanggal.setName("tanggal"); // NOI18N
        tanggal.setOpaque(false);

        btnDataPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/1485357971_desktop_computer.png"))); // NOI18N
        btnDataPenjualan.setText("Data Penjualan Obat & BHP");
        btnDataPenjualan.setIconTextGap(0);
        btnDataPenjualan.setName("btnDataPenjualan"); // NOI18N
        btnDataPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenjualanActionPerformed(evt);
            }
        });

        btnInputPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/cashbox.png"))); // NOI18N
        btnInputPenjualan.setText("Input Penjualan Obat & BHP");
        btnInputPenjualan.setIconTextGap(0);
        btnInputPenjualan.setName("btnInputPenjualan"); // NOI18N
        btnInputPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnInputPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPenjualanActionPerformed(evt);
            }
        });

        btnDataPenyerahanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/kwrite.png"))); // NOI18N
        btnDataPenyerahanDarah.setText("Data Penyerahan Darah");
        btnDataPenyerahanDarah.setIconTextGap(0);
        btnDataPenyerahanDarah.setName("btnDataPenyerahanDarah"); // NOI18N
        btnDataPenyerahanDarah.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPenyerahanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenyerahanDarahActionPerformed(evt);
            }
        });

        btnResepObatDepan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/stock_task.png"))); // NOI18N
        btnResepObatDepan.setText("No. Resep");
        btnResepObatDepan.setIconTextGap(0);
        btnResepObatDepan.setName("btnResepObatDepan"); // NOI18N
        btnResepObatDepan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnResepObatDepan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepObatDepanActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::[ Khanza SIMKES 2022 ]::");
        setBackground(new java.awt.Color(255, 254, 254));
        setIconImages(null);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(40, 42));
        internalFrame1.setVerifyInputWhenFocusTarget(false);
        internalFrame1.setWarnaAtas(new java.awt.Color(245, 255, 245));
        internalFrame1.setWarnaBawah(new java.awt.Color(200, 235, 200));
        internalFrame1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 2));

        BtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/menu.png"))); // NOI18N
        BtnMenu.setMnemonic('M');
        BtnMenu.setText("Menu");
        BtnMenu.setToolTipText("Alt+M");
        BtnMenu.setEnabled(false);
        BtnMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnMenu.setIconTextGap(2);
        BtnMenu.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnMenu.setName("BtnMenu"); // NOI18N
        BtnMenu.setPreferredSize(new java.awt.Dimension(78, 38));
        BtnMenu.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMenuActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnMenu);

        jSeparator4.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator4.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        jSeparator4.setOpaque(true);
        jSeparator4.setPreferredSize(new java.awt.Dimension(1, 36));
        internalFrame1.add(jSeparator4);

        BtnToolReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        BtnToolReg.setMnemonic('R');
        BtnToolReg.setText("Registrasi");
        BtnToolReg.setToolTipText("Alt+R");
        BtnToolReg.setEnabled(false);
        BtnToolReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolReg.setIconTextGap(2);
        BtnToolReg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnToolReg.setName("BtnToolReg"); // NOI18N
        BtnToolReg.setPreferredSize(new java.awt.Dimension(100, 38));
        BtnToolReg.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolRegActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolReg);

        btnToolIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Doctor.png"))); // NOI18N
        btnToolIGD.setMnemonic('D');
        btnToolIGD.setText("IGD/UGD");
        btnToolIGD.setToolTipText("Alt+D");
        btnToolIGD.setEnabled(false);
        btnToolIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolIGD.setIconTextGap(2);
        btnToolIGD.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnToolIGD.setName("btnToolIGD"); // NOI18N
        btnToolIGD.setPreferredSize(new java.awt.Dimension(94, 38));
        btnToolIGD.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolIGDActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolIGD);

        jSeparator5.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator5.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 36));
        internalFrame1.add(jSeparator5);

        btnToolLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-search24.png"))); // NOI18N
        btnToolLab.setMnemonic('O');
        btnToolLab.setText("Laborat");
        btnToolLab.setToolTipText("Alt+O");
        btnToolLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolLab.setIconTextGap(2);
        btnToolLab.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnToolLab.setName("btnToolLab"); // NOI18N
        btnToolLab.setPreferredSize(new java.awt.Dimension(88, 38));
        btnToolLab.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolLabActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolLab);

        btnToolRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Surgeon.png"))); // NOI18N
        btnToolRad.setMnemonic('A');
        btnToolRad.setText("Radiologi");
        btnToolRad.setToolTipText("Alt+A");
        btnToolRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolRad.setIconTextGap(2);
        btnToolRad.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnToolRad.setName("btnToolRad"); // NOI18N
        btnToolRad.setPreferredSize(new java.awt.Dimension(97, 38));
        btnToolRad.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolRadActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolRad);

        BtnToolJualObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/shopping-cart-insert24.png"))); // NOI18N
        BtnToolJualObat.setMnemonic('j');
        BtnToolJualObat.setText("Farmasi");
        BtnToolJualObat.setToolTipText("Alt+J");
        BtnToolJualObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolJualObat.setIconTextGap(2);
        BtnToolJualObat.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnToolJualObat.setName("BtnToolJualObat"); // NOI18N
        BtnToolJualObat.setPreferredSize(new java.awt.Dimension(90, 38));
        BtnToolJualObat.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolJualObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolJualObatActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolJualObat);

        jSeparator9.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator9.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        jSeparator9.setOpaque(true);
        jSeparator9.setPreferredSize(new java.awt.Dimension(1, 36));
        internalFrame1.add(jSeparator9);

        BtnToolKamnap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/home24.png"))); // NOI18N
        BtnToolKamnap.setMnemonic('K');
        BtnToolKamnap.setText("Rawat Inap");
        BtnToolKamnap.setToolTipText("Alt+K");
        BtnToolKamnap.setEnabled(false);
        BtnToolKamnap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKamnap.setIconTextGap(2);
        BtnToolKamnap.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnToolKamnap.setName("BtnToolKamnap"); // NOI18N
        BtnToolKamnap.setPreferredSize(new java.awt.Dimension(107, 38));
        BtnToolKamnap.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKamnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKamnapActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKamnap);

        BtnToolKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnToolKasir.setMnemonic('S');
        BtnToolKasir.setText("Rawat Jalan");
        BtnToolKasir.setToolTipText("Alt+S");
        BtnToolKasir.setEnabled(false);
        BtnToolKasir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKasir.setIconTextGap(2);
        BtnToolKasir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnToolKasir.setName("BtnToolKasir"); // NOI18N
        BtnToolKasir.setPreferredSize(new java.awt.Dimension(110, 38));
        BtnToolKasir.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKasirActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKasir);

        jSeparator7.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator7.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        jSeparator7.setOpaque(true);
        jSeparator7.setPreferredSize(new java.awt.Dimension(1, 36));
        internalFrame1.add(jSeparator7);

        BtnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/login2.png"))); // NOI18N
        BtnLog.setMnemonic('L');
        BtnLog.setText("Log In");
        BtnLog.setToolTipText("Alt+L");
        BtnLog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnLog.setIconTextGap(2);
        BtnLog.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnLog.setName("BtnLog"); // NOI18N
        BtnLog.setPreferredSize(new java.awt.Dimension(78, 38));
        BtnLog.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnLog);

        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Delete.png"))); // NOI18N
        BtnClose.setMnemonic('U');
        BtnClose.setText("Keluar");
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnClose.setIconTextGap(2);
        BtnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose.setName("BtnClose"); // NOI18N
        BtnClose.setPreferredSize(new java.awt.Dimension(80, 38));
        BtnClose.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnClose);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.PAGE_START);

        internalFrame4.setBackground(new java.awt.Color(235, 215, 195));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(330, 25));
        internalFrame4.setWarnaAtas(new java.awt.Color(205, 240, 205));
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 255, 245));
        internalFrame4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 1));

        lblStts.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblStts.setForeground(new java.awt.Color(50, 50, 50));
        lblStts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStts.setText("Status Admin :");
        lblStts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblStts.setName("lblStts"); // NOI18N
        lblStts.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame4.add(lblStts);

        jSeparator1.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator1.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setOpaque(true);
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 21));
        internalFrame4.add(jSeparator1);

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblUser.setForeground(new java.awt.Color(50, 50, 50));
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("Log Out");
        lblUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUser.setName("lblUser"); // NOI18N
        lblUser.setPreferredSize(new java.awt.Dimension(170, 23));
        internalFrame4.add(lblUser);

        jSeparator2.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator2.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setOpaque(true);
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 21));
        internalFrame4.add(jSeparator2);

        lblTgl.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblTgl.setForeground(new java.awt.Color(50, 50, 50));
        lblTgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTgl.setText("Tanggal");
        lblTgl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTgl.setName("lblTgl"); // NOI18N
        lblTgl.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame4.add(lblTgl);

        jSeparator3.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator3.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        jSeparator3.setOpaque(true);
        jSeparator3.setPreferredSize(new java.awt.Dimension(1, 21));
        internalFrame4.add(jSeparator3);

        LblIP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        LblIP.setForeground(new java.awt.Color(50, 50, 50));
        LblIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblIP.setText("Alamat IP");
        LblIP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LblIP.setName("LblIP"); // NOI18N
        LblIP.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame4.add(LblIP);

        jSeparator6.setBackground(new java.awt.Color(0, 158, 96));
        jSeparator6.setForeground(new java.awt.Color(0, 158, 96));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 158, 96)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        jSeparator6.setOpaque(true);
        jSeparator6.setPreferredSize(new java.awt.Dimension(1, 21));
        internalFrame4.add(jSeparator6);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(50, 50, 50));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
        jLabel7.setText(" Didesain & dibuat oleh Khanza.Soft Media");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setIconTextGap(3);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(287, 23));
        internalFrame4.add(jLabel7);

        lblUser1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblUser1.setForeground(new java.awt.Color(50, 50, 50));
        lblUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser1.setText("826.v6");
        lblUser1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUser1.setName("lblUser1"); // NOI18N
        lblUser1.setPreferredSize(new java.awt.Dimension(170, 23));
        internalFrame4.add(lblUser1);

        getContentPane().add(internalFrame4, java.awt.BorderLayout.PAGE_END);

        PanelUtama.setName("PanelUtama"); // NOI18N
        PanelUtama.setOpaque(false);
        PanelUtama.setLayout(new java.awt.BorderLayout());

        scrollPane1.setBorder(null);
        scrollPane1.setName("scrollPane1"); // NOI18N

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/wallpaper.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PanelWallMouseMoved(evt);
            }
        });
        PanelWall.setLayout(new java.awt.BorderLayout());

        panelJudul.setBackground(new java.awt.Color(255, 255, 255));
        panelJudul.setOpaqueImage(false);
        panelJudul.setPreferredSize(new java.awt.Dimension(200, 170));
        panelJudul.setRound(false);
        panelJudul.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 50, 50));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Your Businis Solution");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(430, 30));
        panelJudul.add(jLabel11);
        jLabel11.setBounds(210, 90, 680, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(50, 50, 50));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/yaski_icon.png"))); // NOI18N
        jLabel8.setText("Khanza HMS+, Hospital Management System");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel8.setName("jLabel8"); // NOI18N
        panelJudul.add(jLabel8);
        jLabel8.setBounds(40, 0, 1040, 150);

        PanelWall.add(panelJudul, java.awt.BorderLayout.PAGE_END);

        FlayMenu.setBackground(new java.awt.Color(255, 255, 255));
        FlayMenu.setOpaqueImage(false);
        FlayMenu.setPreferredSize(new java.awt.Dimension(200, 110));
        FlayMenu.setRound(false);
        FlayMenu.setLayout(new java.awt.GridLayout(1, 0, 4, 5));
        PanelWall.add(FlayMenu, java.awt.BorderLayout.PAGE_START);

        scrollPane1.setViewportView(PanelWall);

        PanelUtama.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelUtama, java.awt.BorderLayout.CENTER);

        MenuBar.setBackground(new java.awt.Color(124, 42, 90));
        MenuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MenuBar.setForeground(new java.awt.Color(255, 255, 253));
        MenuBar.setToolTipText("");
        MenuBar.setName("MenuBar"); // NOI18N
        MenuBar.setPreferredSize(new java.awt.Dimension(227, 25));

        jMenu1.setBackground(new java.awt.Color(204, 0, 51));
        jMenu1.setBorder(null);
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/85.png"))); // NOI18N
        jMenu1.setMnemonic('A');
        jMenu1.setText("Program");
        jMenu1.setToolTipText("Alt+A");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(80, 26));

        MnLogin.setBackground(new java.awt.Color(255, 255, 254));
        MnLogin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLogin.setForeground(new java.awt.Color(50, 90, 40));
        MnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/login2.png"))); // NOI18N
        MnLogin.setText("Log In");
        MnLogin.setName("MnLogin"); // NOI18N
        MnLogin.setPreferredSize(new java.awt.Dimension(170, 30));
        MnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogActionPerformed(evt);
            }
        });
        jMenu1.add(MnLogin);

        MnGantiPassword.setBackground(new java.awt.Color(255, 255, 254));
        MnGantiPassword.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPassword.setForeground(new java.awt.Color(50, 90, 40));
        MnGantiPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/unlock.png"))); // NOI18N
        MnGantiPassword.setText("Ubah Password");
        MnGantiPassword.setEnabled(false);
        MnGantiPassword.setName("MnGantiPassword"); // NOI18N
        MnGantiPassword.setPreferredSize(new java.awt.Dimension(170, 30));
        MnGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPasswordBtnLogActionPerformed(evt);
            }
        });
        jMenu1.add(MnGantiPassword);

        jSeparator14.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator14.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator14.setName("jSeparator14"); // NOI18N
        jSeparator14.setPreferredSize(new java.awt.Dimension(0, 1));
        jMenu1.add(jSeparator14);

        MenuKeluar.setBackground(new java.awt.Color(255, 255, 254));
        MenuKeluar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuKeluar.setForeground(new java.awt.Color(50, 90, 40));
        MenuKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Delete.png"))); // NOI18N
        MenuKeluar.setText("Keluar");
        MenuKeluar.setName("MenuKeluar"); // NOI18N
        MenuKeluar.setPreferredSize(new java.awt.Dimension(170, 30));
        MenuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        jMenu1.add(MenuKeluar);

        MenuBar.add(jMenu1);

        jMenu5.setBackground(new java.awt.Color(20, 0, 20));
        jMenu5.setBorder(null);
        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientMale.png"))); // NOI18N
        jMenu5.setMnemonic('P');
        jMenu5.setText("Presensi Pegawai");
        jMenu5.setToolTipText("Alt+P");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu5.setIconTextGap(3);
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(126, 26));

        MnBelumDatang1.setBackground(new java.awt.Color(255, 255, 254));
        MnBelumDatang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelumDatang1.setForeground(new java.awt.Color(50, 90, 40));
        MnBelumDatang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/female-user-search24.png"))); // NOI18N
        MnBelumDatang1.setText("Presensi Pegawai");
        MnBelumDatang1.setName("MnBelumDatang1"); // NOI18N
        MnBelumDatang1.setPreferredSize(new java.awt.Dimension(170, 30));
        MnBelumDatang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumDatang1ActionPerformed(evt);
            }
        });
        jMenu5.add(MnBelumDatang1);

        MnBelumDatang.setBackground(new java.awt.Color(255, 255, 254));
        MnBelumDatang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelumDatang.setForeground(new java.awt.Color(50, 90, 40));
        MnBelumDatang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/couple24.png"))); // NOI18N
        MnBelumDatang.setText("Belum Datang");
        MnBelumDatang.setName("MnBelumDatang"); // NOI18N
        MnBelumDatang.setPreferredSize(new java.awt.Dimension(170, 30));
        MnBelumDatang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumDatangActionPerformed(evt);
            }
        });
        jMenu5.add(MnBelumDatang);

        MnSudahPulang.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-remove24.png"))); // NOI18N
        MnSudahPulang.setText("Sudah Pulang");
        MnSudahPulang.setName("MnSudahPulang"); // NOI18N
        MnSudahPulang.setPreferredSize(new java.awt.Dimension(170, 30));
        MnSudahPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulangActionPerformed(evt);
            }
        });
        jMenu5.add(MnSudahPulang);

        MnRekapBulanan.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapBulanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulanan.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapBulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Doctor.png"))); // NOI18N
        MnRekapBulanan.setText("Rekap Bulanan");
        MnRekapBulanan.setName("MnRekapBulanan"); // NOI18N
        MnRekapBulanan.setPreferredSize(new java.awt.Dimension(170, 30));
        MnRekapBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulananActionPerformed(evt);
            }
        });
        jMenu5.add(MnRekapBulanan);

        MnRekapHadir.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-search24.png"))); // NOI18N
        MnRekapHadir.setText("Rekap Kehadiran");
        MnRekapHadir.setName("MnRekapHadir"); // NOI18N
        MnRekapHadir.setPreferredSize(new java.awt.Dimension(170, 30));
        MnRekapHadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadirActionPerformed(evt);
            }
        });
        jMenu5.add(MnRekapHadir);

        MnPengajuanCutiPegawai.setBackground(new java.awt.Color(255, 255, 254));
        MnPengajuanCutiPegawai.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengajuanCutiPegawai.setForeground(new java.awt.Color(50, 90, 40));
        MnPengajuanCutiPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        MnPengajuanCutiPegawai.setText("Pengajuan Cuti");
        MnPengajuanCutiPegawai.setEnabled(false);
        MnPengajuanCutiPegawai.setName("MnPengajuanCutiPegawai"); // NOI18N
        MnPengajuanCutiPegawai.setPreferredSize(new java.awt.Dimension(170, 30));
        MnPengajuanCutiPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengajuanCutiPegawaiActionPerformed(evt);
            }
        });
        jMenu5.add(MnPengajuanCutiPegawai);

        MenuBar.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(20, 0, 20));
        jMenu6.setBorder(null);
        jMenu6.setForeground(new java.awt.Color(255, 255, 255));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart.png"))); // NOI18N
        jMenu6.setMnemonic('I');
        jMenu6.setText("Informasi");
        jMenu6.setToolTipText("Alt+I");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu6.setName("jMenu6"); // NOI18N
        jMenu6.setPreferredSize(new java.awt.Dimension(88, 26));

        MnRekapBulanan1.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapBulanan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulanan1.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapBulanan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnRekapBulanan1.setText("Pasien Kamar Inap");
        MnRekapBulanan1.setName("MnRekapBulanan1"); // NOI18N
        MnRekapBulanan1.setPreferredSize(new java.awt.Dimension(220, 30));
        MnRekapBulanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulanan1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnRekapBulanan1);

        MnRekapBulanan3.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapBulanan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulanan3.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapBulanan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnRekapBulanan3.setText("Telusur Kunjungan Pasien");
        MnRekapBulanan3.setName("MnRekapBulanan3"); // NOI18N
        MnRekapBulanan3.setPreferredSize(new java.awt.Dimension(220, 30));
        MnRekapBulanan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulanan3ActionPerformed(evt);
            }
        });
        jMenu6.add(MnRekapBulanan3);

        MnRekapBulanan2.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapBulanan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulanan2.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapBulanan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnRekapBulanan2.setText("Penggunaan Kamar Inap");
        MnRekapBulanan2.setName("MnRekapBulanan2"); // NOI18N
        MnRekapBulanan2.setPreferredSize(new java.awt.Dimension(220, 30));
        MnRekapBulanan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulanan2ActionPerformed(evt);
            }
        });
        jMenu6.add(MnRekapBulanan2);

        MnRekapBulanan4.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapBulanan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulanan4.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapBulanan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnRekapBulanan4.setText("Ketersediaan Stok Darah UTD");
        MnRekapBulanan4.setName("MnRekapBulanan4"); // NOI18N
        MnRekapBulanan4.setPreferredSize(new java.awt.Dimension(220, 30));
        MnRekapBulanan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulanan4ActionPerformed(evt);
            }
        });
        jMenu6.add(MnRekapBulanan4);

        jSeparator10.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator10.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator10.setName("jSeparator10"); // NOI18N
        jSeparator10.setPreferredSize(new java.awt.Dimension(0, 1));
        jMenu6.add(jSeparator10);

        MnTarif.setBackground(new java.awt.Color(255, 255, 254));
        MnTarif.setForeground(new java.awt.Color(50, 90, 40));
        MnTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarif.setText("Tarif");
        MnTarif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTarif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTarif.setName("MnTarif"); // NOI18N
        MnTarif.setPreferredSize(new java.awt.Dimension(220, 30));

        MnSudahPulang1.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang1.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang1.setText("Kamar");
        MnSudahPulang1.setName("MnSudahPulang1"); // NOI18N
        MnSudahPulang1.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang1ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang1);

        MnSudahPulang3.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang3.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang3.setText("Laboratorium");
        MnSudahPulang3.setName("MnSudahPulang3"); // NOI18N
        MnSudahPulang3.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang3ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang3);

        MnSudahPulang6.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang6.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang6.setText("Radiologi");
        MnSudahPulang6.setName("MnSudahPulang6"); // NOI18N
        MnSudahPulang6.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang6ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang6);

        MnSudahPulang4.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang4.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang4.setText("Operasi & VK");
        MnSudahPulang4.setName("MnSudahPulang4"); // NOI18N
        MnSudahPulang4.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang4ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang4);

        MnSudahPulang2.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang2.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang2.setText("Rawat Jalan");
        MnSudahPulang2.setName("MnSudahPulang2"); // NOI18N
        MnSudahPulang2.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang2ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang2);

        MnSudahPulang5.setBackground(new java.awt.Color(255, 255, 254));
        MnSudahPulang5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahPulang5.setForeground(new java.awt.Color(50, 90, 40));
        MnSudahPulang5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnSudahPulang5.setText("Rawat Inap");
        MnSudahPulang5.setName("MnSudahPulang5"); // NOI18N
        MnSudahPulang5.setPreferredSize(new java.awt.Dimension(150, 30));
        MnSudahPulang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahPulang5ActionPerformed(evt);
            }
        });
        MnTarif.add(MnSudahPulang5);

        jMenu6.add(MnTarif);

        MnTarif1.setBackground(new java.awt.Color(255, 255, 254));
        MnTarif1.setForeground(new java.awt.Color(50, 90, 40));
        MnTarif1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnTarif1.setText("Dashboard");
        MnTarif1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTarif1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTarif1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTarif1.setName("MnTarif1"); // NOI18N
        MnTarif1.setPreferredSize(new java.awt.Dimension(220, 30));

        MnRekapHadir1.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir1.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnRekapHadir1.setText("Jadwal Praktek Dokter");
        MnRekapHadir1.setName("MnRekapHadir1"); // NOI18N
        MnRekapHadir1.setPreferredSize(new java.awt.Dimension(200, 30));
        MnRekapHadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir1ActionPerformed(evt);
            }
        });
        MnTarif1.add(MnRekapHadir1);

        MnInfoBed.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoBed.setText("Info Bed 1");
        MnInfoBed.setName("MnInfoBed"); // NOI18N
        MnInfoBed.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoBed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBedActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoBed);

        MnInfoBed2.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed2.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoBed2.setText("Info Bed 2");
        MnInfoBed2.setName("MnInfoBed2"); // NOI18N
        MnInfoBed2.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoBed2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBed2ActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoBed2);

        MnInfoBed1.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed1.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoBed1.setText("Info Jadwal Dokter");
        MnInfoBed1.setName("MnInfoBed1"); // NOI18N
        MnInfoBed1.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoBed1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBed1ActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoBed1);

        MnInfoBed3.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed3.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoBed3.setText("Info Bed & Jadwal Dokter");
        MnInfoBed3.setName("MnInfoBed3"); // NOI18N
        MnInfoBed3.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoBed3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBed3ActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoBed3);

        MnInfoBed5.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed5.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoBed5.setText("Jadwal Operasi");
        MnInfoBed5.setName("MnInfoBed5"); // NOI18N
        MnInfoBed5.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoBed5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBed5ActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoBed5);

        MnInfoMobileJKN.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoMobileJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoMobileJKN.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoMobileJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnInfoMobileJKN.setText("Info Pasien Mobile JKN");
        MnInfoMobileJKN.setName("MnInfoMobileJKN"); // NOI18N
        MnInfoMobileJKN.setPreferredSize(new java.awt.Dimension(200, 30));
        MnInfoMobileJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoMobileJKNActionPerformed(evt);
            }
        });
        MnTarif1.add(MnInfoMobileJKN);

        jMenu6.add(MnTarif1);

        jSeparator15.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator15.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator15.setName("jSeparator15"); // NOI18N
        jSeparator15.setPreferredSize(new java.awt.Dimension(0, 1));
        jMenu6.add(jSeparator15);

        MnKoleksiPenelitian.setBackground(new java.awt.Color(255, 255, 254));
        MnKoleksiPenelitian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKoleksiPenelitian.setForeground(new java.awt.Color(50, 90, 40));
        MnKoleksiPenelitian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnKoleksiPenelitian.setText("Koleksi Penelitian");
        MnKoleksiPenelitian.setName("MnKoleksiPenelitian"); // NOI18N
        MnKoleksiPenelitian.setPreferredSize(new java.awt.Dimension(220, 30));
        MnKoleksiPenelitian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKoleksiPenelitianActionPerformed(evt);
            }
        });
        jMenu6.add(MnKoleksiPenelitian);

        MnCariEbook.setBackground(new java.awt.Color(255, 255, 254));
        MnCariEbook.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariEbook.setForeground(new java.awt.Color(50, 90, 40));
        MnCariEbook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnCariEbook.setText("Cari Koleksi Ebook");
        MnCariEbook.setName("MnCariEbook"); // NOI18N
        MnCariEbook.setPreferredSize(new java.awt.Dimension(220, 30));
        MnCariEbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariEbookActionPerformed(evt);
            }
        });
        jMenu6.add(MnCariEbook);

        MnCariInventarisPerpustakaan.setBackground(new java.awt.Color(255, 255, 254));
        MnCariInventarisPerpustakaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariInventarisPerpustakaan.setForeground(new java.awt.Color(50, 90, 40));
        MnCariInventarisPerpustakaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        MnCariInventarisPerpustakaan.setText("Cari Inventaris Perpustakaan");
        MnCariInventarisPerpustakaan.setName("MnCariInventarisPerpustakaan"); // NOI18N
        MnCariInventarisPerpustakaan.setPreferredSize(new java.awt.Dimension(220, 30));
        MnCariInventarisPerpustakaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariInventarisPerpustakaanActionPerformed(evt);
            }
        });
        jMenu6.add(MnCariInventarisPerpustakaan);

        MenuBar.add(jMenu6);

        jMenu7.setBackground(new java.awt.Color(20, 0, 20));
        jMenu7.setBorder(null);
        jMenu7.setForeground(new java.awt.Color(255, 255, 255));
        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/My-Tickets24.png"))); // NOI18N
        jMenu7.setMnemonic('N');
        jMenu7.setText("Anjungan & Antrian");
        jMenu7.setToolTipText("Alt+N");
        jMenu7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu7.setName("jMenu7"); // NOI18N
        jMenu7.setPreferredSize(new java.awt.Dimension(137, 26));

        MnAnjungan.setBackground(new java.awt.Color(255, 255, 254));
        MnAnjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAnjungan.setForeground(new java.awt.Color(50, 90, 40));
        MnAnjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/family24.png"))); // NOI18N
        MnAnjungan.setText("Anjungan Registrasi Mandiri");
        MnAnjungan.setName("MnAnjungan"); // NOI18N
        MnAnjungan.setPreferredSize(new java.awt.Dimension(205, 30));
        MnAnjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAnjunganActionPerformed(evt);
            }
        });
        jMenu7.add(MnAnjungan);

        jSeparator12.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator12.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator12.setName("jSeparator12"); // NOI18N
        jSeparator12.setPreferredSize(new java.awt.Dimension(0, 1));
        jMenu7.add(jSeparator12);

        MnRekapHadir9.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir9.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir9.setText("Cetak Antrian Loket");
        MnRekapHadir9.setName("MnRekapHadir9"); // NOI18N
        MnRekapHadir9.setPreferredSize(new java.awt.Dimension(205, 30));
        MnRekapHadir9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir9ActionPerformed(evt);
            }
        });
        jMenu7.add(MnRekapHadir9);

        MnRekapHadir3.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir3.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir3.setText("Antrian Loket");
        MnRekapHadir3.setName("MnRekapHadir3"); // NOI18N
        MnRekapHadir3.setPreferredSize(new java.awt.Dimension(205, 30));
        MnRekapHadir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir3ActionPerformed(evt);
            }
        });
        jMenu7.add(MnRekapHadir3);

        MnRekapHadir10.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir10.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir10.setText("Antrian Loket 2");
        MnRekapHadir10.setName("MnRekapHadir10"); // NOI18N
        MnRekapHadir10.setPreferredSize(new java.awt.Dimension(205, 30));
        MnRekapHadir10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir10ActionPerformed(evt);
            }
        });
        jMenu7.add(MnRekapHadir10);

        MnRekapHadir4.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir4.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir4.setText("Antrian Unit/Poliklinik");
        MnRekapHadir4.setName("MnRekapHadir4"); // NOI18N
        MnRekapHadir4.setPreferredSize(new java.awt.Dimension(205, 30));
        MnRekapHadir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir4ActionPerformed(evt);
            }
        });
        jMenu7.add(MnRekapHadir4);

        MnInfoBed4.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoBed4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoBed4.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoBed4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnInfoBed4.setText("Antrian Unit/Poliklinik 2");
        MnInfoBed4.setName("MnInfoBed4"); // NOI18N
        MnInfoBed4.setPreferredSize(new java.awt.Dimension(205, 30));
        MnInfoBed4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoBed4ActionPerformed(evt);
            }
        });
        jMenu7.add(MnInfoBed4);

        MnAntrianFarmasi.setBackground(new java.awt.Color(255, 255, 254));
        MnAntrianFarmasi.setForeground(new java.awt.Color(50, 90, 40));
        MnAntrianFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnAntrianFarmasi.setText("Antrian Apotek");
        MnAntrianFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAntrianFarmasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAntrianFarmasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAntrianFarmasi.setName("MnAntrianFarmasi"); // NOI18N
        MnAntrianFarmasi.setPreferredSize(new java.awt.Dimension(205, 30));

        MnRekapHadir5.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir5.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir5.setText("Apotek 1");
        MnRekapHadir5.setName("MnRekapHadir5"); // NOI18N
        MnRekapHadir5.setPreferredSize(new java.awt.Dimension(180, 30));
        MnRekapHadir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir5ActionPerformed(evt);
            }
        });
        MnAntrianFarmasi.add(MnRekapHadir5);

        MnRekapHadir7.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir7.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir7.setText("Apotek 2");
        MnRekapHadir7.setName("MnRekapHadir7"); // NOI18N
        MnRekapHadir7.setPreferredSize(new java.awt.Dimension(180, 30));
        MnRekapHadir7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir7ActionPerformed(evt);
            }
        });
        MnAntrianFarmasi.add(MnRekapHadir7);

        MnRekapHadir8.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir8.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir8.setText("Apotek 2 Racikan");
        MnRekapHadir8.setName("MnRekapHadir8"); // NOI18N
        MnRekapHadir8.setPreferredSize(new java.awt.Dimension(180, 30));
        MnRekapHadir8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir8ActionPerformed(evt);
            }
        });
        MnAntrianFarmasi.add(MnRekapHadir8);

        MnRekapHadir11.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir11.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnRekapHadir11.setText("Apotek 2 Non Racikan");
        MnRekapHadir11.setName("MnRekapHadir11"); // NOI18N
        MnRekapHadir11.setPreferredSize(new java.awt.Dimension(180, 30));
        MnRekapHadir11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir11ActionPerformed(evt);
            }
        });
        MnAntrianFarmasi.add(MnRekapHadir11);

        jMenu7.add(MnAntrianFarmasi);

        MnAntrianLaborat.setBackground(new java.awt.Color(255, 255, 254));
        MnAntrianLaborat.setForeground(new java.awt.Color(50, 90, 40));
        MnAntrianLaborat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnAntrianLaborat.setText("Antrian Laborat");
        MnAntrianLaborat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAntrianLaborat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAntrianLaborat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAntrianLaborat.setName("MnAntrianLaborat"); // NOI18N
        MnAntrianLaborat.setPreferredSize(new java.awt.Dimension(205, 30));

        MnInfoLaborat.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoLaborat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoLaborat.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoLaborat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnInfoLaborat.setText("Laborat Patologi Klinis");
        MnInfoLaborat.setName("MnInfoLaborat"); // NOI18N
        MnInfoLaborat.setPreferredSize(new java.awt.Dimension(235, 30));
        MnInfoLaborat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoLaboratActionPerformed(evt);
            }
        });
        MnAntrianLaborat.add(MnInfoLaborat);

        MnInfoLaborat1.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoLaborat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoLaborat1.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoLaborat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnInfoLaborat1.setText("Laborat Patologi Anatomi");
        MnInfoLaborat1.setName("MnInfoLaborat1"); // NOI18N
        MnInfoLaborat1.setPreferredSize(new java.awt.Dimension(235, 30));
        MnInfoLaborat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoLaborat1ActionPerformed(evt);
            }
        });
        MnAntrianLaborat.add(MnInfoLaborat1);

        MnInfoLaborat3.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoLaborat3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoLaborat3.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoLaborat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnInfoLaborat3.setText("Laborat Mikrobiologi & Bio Molekuler");
        MnInfoLaborat3.setName("MnInfoLaborat3"); // NOI18N
        MnInfoLaborat3.setPreferredSize(new java.awt.Dimension(235, 30));
        MnInfoLaborat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoLaborat3ActionPerformed(evt);
            }
        });
        MnAntrianLaborat.add(MnInfoLaborat3);

        jMenu7.add(MnAntrianLaborat);

        MnInfoLaborat2.setBackground(new java.awt.Color(255, 255, 254));
        MnInfoLaborat2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInfoLaborat2.setForeground(new java.awt.Color(50, 90, 40));
        MnInfoLaborat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        MnInfoLaborat2.setText("Antrian Radiologi");
        MnInfoLaborat2.setName("MnInfoLaborat2"); // NOI18N
        MnInfoLaborat2.setPreferredSize(new java.awt.Dimension(205, 30));
        MnInfoLaborat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoLaborat2ActionPerformed(evt);
            }
        });
        jMenu7.add(MnInfoLaborat2);

        jSeparator13.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator13.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator13.setName("jSeparator13"); // NOI18N
        jSeparator13.setPreferredSize(new java.awt.Dimension(0, 1));
        jMenu7.add(jSeparator13);

        MnRekapHadir6.setBackground(new java.awt.Color(255, 255, 254));
        MnRekapHadir6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHadir6.setForeground(new java.awt.Color(50, 90, 40));
        MnRekapHadir6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        MnRekapHadir6.setText("Verifikasi Digital Klaim");
        MnRekapHadir6.setName("MnRekapHadir6"); // NOI18N
        MnRekapHadir6.setPreferredSize(new java.awt.Dimension(205, 30));
        MnRekapHadir6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHadir6ActionPerformed(evt);
            }
        });
        jMenu7.add(MnRekapHadir6);

        MenuBar.add(jMenu7);

        jMenu4.setBackground(new java.awt.Color(20, 0, 20));
        jMenu4.setBorder(null);
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/63.png"))); // NOI18N
        jMenu4.setMnemonic('G');
        jMenu4.setText("Tentang Program");
        jMenu4.setToolTipText("Alt+G");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(121, 26));
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        MenuBar.add(jMenu4);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        isTutup();
        DlgHome.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed
        isTutup();
        DlgHome.dispose();
        int jawab=JOptionPane.showConfirmDialog(null, "Yakin anda mau keluar dari program ini ????","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(jawab==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        edAdmin.setText("");
        edPwd.setText("");
        DlgLogin.dispose();
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void BtnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogActionPerformed
        FlayMenu.setVisible(false);
        switch (BtnLog.getText().trim()) {
            case "Log Out":
                BtnToolReg.setEnabled(false);
                BtnToolKamnap.setEnabled(false);
                BtnToolKasir.setEnabled(false);
                btnToolIGD.setEnabled(false);
                MnGantiPassword.setEnabled(false);
                MnPengajuanCutiPegawai.setEnabled(false);        
                akses.setpenjualan_obatfalse();
                akses.setpenjualan_obatfalse();
                akses.setutd_penyerahan_darahfalse();
                akses.setresep_dokterfalse();
                akses.setresep_obatfalse();
                akses.setpermintaanlabfalse();
                akses.setperiksalabfalse();
                akses.setperiksalabpafalse();
                akses.setperiksalabmbfalse();
                akses.setpermintaanradiologifalse();
                akses.setperiksaradiologifalse();
                edAdmin.setText("");
                edPwd.setText("");
                BtnLog.setText("Log In");
                MnLogin.setText("Log In");
                lblStts.setText("Status Admin : ");
                lblUser.setText("Log Out");
                BtnMenu.setEnabled(false);
                akses.setLogOut();
                isTutup();
                break;
            case "Log In":
                isTutup();
                DlgLogin.setVisible(true);
                edAdmin.requestFocus();
                break;
        }
    }//GEN-LAST:event_BtnLogActionPerformed

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        if(edAdmin.getText().trim().equals("")){
            Valid.textKosong(edAdmin,"ID User");
        }else if(edPwd.getText().trim().equals("")){
            Valid.textKosong(edPwd,"Password");
        }else {
            try {   
                cacheigd.reset();
                cacherawatjalan.reset();
                cacherawatinap.reset();
                cacheregistrasi.reset();
                akses.setData(edAdmin.getText(),edPwd.getText());        
                if(akses.getjml1()>=1){    
                    BtnMenu.setEnabled(true);
                    BtnToolReg.setEnabled(true);
                    BtnToolKamnap.setEnabled(true);
                    BtnToolKasir.setEnabled(true); 
                    btnToolIGD.setEnabled(true);
                    btnPermintaanLab.setEnabled(true);
                    btnPermintaanLabPA.setEnabled(true);
                    btnPermintaanLabMB.setEnabled(true);
                    btnLaboratorium.setEnabled(true);
                    btnLaboratoriumPA.setEnabled(true);
                    btnLaboratoriumMB.setEnabled(true);
                    btnPermintaanRadiologi.setEnabled(true);
                    btnPeriksaRadiologi.setEnabled(true);
                    btnInputPenjualan.setEnabled(true);
                    btnDataPenjualan.setEnabled(true);
                    btnDataPenyerahanDarah.setEnabled(true);
                    btnDaftarPermintaanResep.setEnabled(true);
                    btnResepObatDepan.setEnabled(true);
                    MnGantiPassword.setEnabled(false);
                    MnPengajuanCutiPegawai.setEnabled(false);

                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    MnLogin.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText("Admin Utama");
                    if(AKTIFKANTRACKSQL.equals("yes")){
                        Sequel.menyimpan("tracker","'Admin Utama',current_date(),current_time()","Login");
                    }
                }else if(akses.getjml2()>=1){  
                    BtnMenu.setEnabled(true);
                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    MnLogin.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText(akses.getkode());
                    MnGantiPassword.setEnabled(true);
                    MnPengajuanCutiPegawai.setEnabled(true);
                    BtnToolReg.setEnabled(akses.getregistrasi());
                    if((akses.getkamar_inap()==true)||(akses.getbilling_ranap()==true)||(akses.gettindakan_ranap()==true)){
                        BtnToolKamnap.setEnabled(true);
                    }else{
                        BtnToolKamnap.setEnabled(akses.getkamar_inap());
                    }
                    
                    if((akses.getkasir_ralan()==true)||(akses.getbilling_ralan()==true)){
                        BtnToolKasir.setEnabled(true);
                    }else{
                        BtnToolKasir.setEnabled(akses.getkasir_ralan());
                    }
                        
                    if((akses.getpermintaan_radiologi()==true)||(akses.getperiksa_radiologi()==true)){
                        btnPermintaanRadiologi.setEnabled(true);
                    }else{
                        btnPermintaanRadiologi.setEnabled(akses.getpermintaan_radiologi());
                    }
                    
                    if((akses.getpermintaan_lab()==true)||(akses.getperiksa_lab()==true)||(akses.getpemeriksaan_lab_pa()==true)||(akses.getpemeriksaan_lab_mb()==true)){
                        btnPermintaanLab.setEnabled(true);
                        btnPermintaanLabPA.setEnabled(true);
                        btnPermintaanLabMB.setEnabled(true);
                    }else{
                        btnPermintaanLab.setEnabled(akses.getpermintaan_lab());
                        btnPermintaanLabPA.setEnabled(akses.getpermintaan_lab());
                        btnPermintaanLabMB.setEnabled(akses.getpermintaan_lab());
                    }
                        
                    btnToolIGD.setEnabled(akses.getigd());                
                    btnLaboratorium.setEnabled(akses.getperiksa_lab());
                    btnLaboratoriumPA.setEnabled(akses.getpemeriksaan_lab_pa());
                    btnLaboratoriumMB.setEnabled(akses.getpemeriksaan_lab_pa());
                    btnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());  
                    btnInputPenjualan.setEnabled(akses.getpenjualan_obat());
                    btnDataPenjualan.setEnabled(akses.getpenjualan_obat());
                    btnDataPenyerahanDarah.setEnabled(akses.getutd_penyerahan_darah());
                    btnDaftarPermintaanResep.setEnabled(akses.getresep_dokter());
                    btnResepObatDepan.setEnabled(akses.getresep_obat());
                    if(AKTIFKANTRACKSQL.equals("yes")){
                        Sequel.menyimpan("tracker","'"+edAdmin.getText()+"',current_date(),current_time()","Login");
                    }
                }else if((akses.getjml1()==0)&&(akses.getjml2()==0)){
                    JOptionPane.showMessageDialog(null,"Maaf, Gagal login. ID User atau password ada yang salah ...!");
                    BtnToolReg.setEnabled(false);
                    BtnToolKamnap.setEnabled(false);
                    BtnToolKasir.setEnabled(false);
                    MnGantiPassword.setEnabled(false);  
                    MnPengajuanCutiPegawai.setEnabled(false);
                    btnToolIGD.setEnabled(false);
                    btnPermintaanLab.setEnabled(false);
                    btnPermintaanLabPA.setEnabled(false);
                    btnPermintaanLabMB.setEnabled(false);
                    btnLaboratorium.setEnabled(false);
                    btnLaboratoriumPA.setEnabled(false);
                    btnLaboratoriumMB.setEnabled(false);
                    btnPermintaanRadiologi.setEnabled(false);
                    btnPeriksaRadiologi.setEnabled(false);    
                    btnInputPenjualan.setEnabled(false);
                    btnDataPenjualan.setEnabled(false);
                    btnDataPenyerahanDarah.setEnabled(false);
                    btnDaftarPermintaanResep.setEnabled(false);
                    btnResepObatDepan.setEnabled(false);
                    edAdmin.setText("");
                    edPwd.setText("");           
                     
                    BtnMenu.setEnabled(false);

                    edAdmin.requestFocus();
                    BtnLog.setText("Log In");
                    MnLogin.setText("Log In");
                    lblStts.setText("Status Admin : ");
                    lblUser.setText("Log Out");   
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }
        }
    }//GEN-LAST:event_BtnLoginActionPerformed

    private void BtnToolKamnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKamnapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKamarInap kamarinap=new DlgKamarInap(null,false);
        kamarinap.isCek();
        kamarinap.emptTeks();  
        kamarinap.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kamarinap.setLocationRelativeTo(PanelUtama);
        kamarinap.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnToolKamnapActionPerformed

private void edAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edAdminKeyPressed
        Valid.pindah(evt,BtnCancel, edPwd);
}//GEN-LAST:event_edAdminKeyPressed

private void edPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edPwdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnLoginActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            edAdmin.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnLogin.requestFocus();
        }
}//GEN-LAST:event_edPwdKeyPressed

private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
    isTutup();
    DlgHome.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
    DlgHome.setLocationRelativeTo(PanelUtama);
    DlgHome.setVisible(true);
    if(menuawal==0){
        isCombo();
        setLayout();
        menuawal=1;
    }else{
        isTampil();
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnMenuActionPerformed

private void BtnToolKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKasirActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgKasirRalan kasirralan=new DlgKasirRalan(this,false);
        kasirralan.isCek();      
        kasirralan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.setLocationRelativeTo(PanelUtama);
        kasirralan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnToolKasirActionPerformed

private void BtnToolRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolRegActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgReg reg=new DlgReg(this,false);
        //reg.emptTeks();    
        reg.isCek();
        reg.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        reg.setLocationRelativeTo(PanelUtama);
        reg.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());   
}//GEN-LAST:event_BtnToolRegActionPerformed

private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    if(this.getState()==1){
        isTutup();
    }
}//GEN-LAST:event_formWindowStateChanged

private void MnGantiPasswordBtnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPasswordBtnLogActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
    isTutup();
    WindowInputPassword barcode=new WindowInputPassword(this,true);
    barcode.SetUserPass(lblUser.getText(),edPwd.getText());
    barcode.setLocationRelativeTo(PanelUtama);
    barcode.setVisible(true);
    DlgHome.dispose();
    this.setCursor(Cursor.getDefaultCursor());
    
}//GEN-LAST:event_MnGantiPasswordBtnLogActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        DlgHome.dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void cmbMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMenuItemStateChanged
        isTampil();
    }//GEN-LAST:event_cmbMenuItemStateChanged

    void btnResepObatActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResepObat resep=new DlgResepObat(this,false);
        resep.tampil2();
        resep.emptTeks();
        resep.isCek();
        resep.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        resep.setLocationRelativeTo(PanelUtama);
        resep.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnReturJualActionPerformed(java.awt.event.ActionEvent evt) {
        akses.setform("DlgReturJual");
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturJual returjual=new DlgReturJual(this,false);
        returjual.emptTeks();
        returjual.isCek();
        returjual.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        returjual.setLocationRelativeTo(PanelUtama);
        returjual.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnKasirActionPerformed(java.awt.event.ActionEvent evt) {
        BtnToolKasirActionPerformed(evt);
    }

    void btnPenggajianActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenggajian penggajian=new DlgPenggajian(this,false);
        try {
            if(akses.getpegawai_admin()==true){
                penggajian.LoadPenggajian("http://" +koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&level=admin");
            }else if(akses.getpegawai_user()==true){
                penggajian.LoadPenggajian("http://" +koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&level=user");
            }            
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
        
        penggajian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penggajian.setLocationRelativeTo(PanelUtama);
        penggajian.setVisible(true);
        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnBelumDatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumDatangActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        isTutup();
        DlgBelum belum=new DlgBelum(this,false);
        belum.tampil();
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBelumDatangActionPerformed

    private void MnSudahPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulangActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        isTutup();
        DlgPulang pulang=new DlgPulang(this,false);
        pulang.tampil();
        pulang.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        pulang.setLocationRelativeTo(PanelUtama);
        pulang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulangActionPerformed

    private void MnRekapBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulananActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        isTutup();
        DlgBulanan2 rekapbulanan2=new DlgBulanan2(this,false);
        rekapbulanan2.tampil();
        rekapbulanan2.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        rekapbulanan2.setLocationRelativeTo(PanelUtama);
        rekapbulanan2.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapBulananActionPerformed

    private void MnRekapHadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadirActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        isTutup();
        DlgKehadiran hadir=new DlgKehadiran(this,false);
        hadir.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        hadir.setLocationRelativeTo(PanelUtama);
        hadir.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadirActionPerformed

    void btnRekapPresensiActionPerformed(java.awt.event.ActionEvent evt) {
        MnRekapHadirActionPerformed(evt);
    }

    private void btnToolLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolLabActionPerformed
        isTutup();
        FlayMenu.removeAll();        
        FlayMenu.add(btnPermintaanLab);
        FlayMenu.add(btnPermintaanLabPA);
        FlayMenu.add(btnPermintaanLabMB);
        FlayMenu.add(btnLaboratorium);
        FlayMenu.add(btnLaboratoriumPA);
        FlayMenu.add(btnLaboratoriumMB);
        if((akses.getpermintaan_lab()==true)||(akses.getperiksa_lab()==true)||(akses.getpemeriksaan_lab_pa()==true)||(akses.getpemeriksaan_lab_mb()==true)){
            btnPermintaanLab.setEnabled(true);
            btnPermintaanLabPA.setEnabled(true);
            btnPermintaanLabMB.setEnabled(true);
        }else{
            btnPermintaanLab.setEnabled(akses.getpermintaan_lab());
            btnPermintaanLabPA.setEnabled(akses.getpermintaan_lab());
            btnPermintaanLabMB.setEnabled(akses.getpermintaan_lab());
        }
        btnLaboratorium.setEnabled(akses.getperiksa_lab());
        btnLaboratoriumPA.setEnabled(akses.getpemeriksaan_lab_pa());
        btnLaboratoriumMB.setEnabled(akses.getpemeriksaan_lab_mb());
        FlayMenu.setVisible(true); 
    }//GEN-LAST:event_btnToolLabActionPerformed

    private void MnSudahPulang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiKamar belum=new InformasiKamar(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang1ActionPerformed

    private void MnRekapBulanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulanan1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();  
        InformasiKamarInap informasikamar=new InformasiKamarInap(this,true);
        informasikamar.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        informasikamar.setLocationRelativeTo(PanelUtama);
        informasikamar.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapBulanan1ActionPerformed

    private void MnRekapHadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiJadwal belum=new InformasiJadwal(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadir1ActionPerformed

    private void MnSudahPulang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiTarifRalan belum=new InformasiTarifRalan(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang2ActionPerformed

    private void MnSudahPulang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiTarifLab belum=new InformasiTarifLab(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang3ActionPerformed

    private void MnSudahPulang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiTarifOperasi belum=new InformasiTarifOperasi(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang4ActionPerformed

    private void MnSudahPulang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiTarifRanap belum=new InformasiTarifRanap(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang5ActionPerformed

    private void MnSudahPulang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahPulang6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiTarifRadiologi belum=new InformasiTarifRadiologi(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSudahPulang6ActionPerformed

    private void btnToolIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolIGDActionPerformed
        btnIGDActionPerformed(evt);
    }//GEN-LAST:event_btnToolIGDActionPerformed

    void btnIGDActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIGD igd=new DlgIGD(this,false);
        //igd.emptTeks();
        igd.isCek();
        igd.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        igd.setLocationRelativeTo(PanelUtama);
        igd.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnBelumDatang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumDatang1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("presensi");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBelumDatang1ActionPerformed

    private void btnToolRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolRadActionPerformed
        isTutup();
        FlayMenu.removeAll();        
        FlayMenu.add(btnPermintaanRadiologi);
        FlayMenu.add(btnPeriksaRadiologi);
        if((akses.getpermintaan_radiologi()==true)||(akses.getperiksa_radiologi()==true)){
            btnPermintaanRadiologi.setEnabled(true);
        }else{
            btnPermintaanRadiologi.setEnabled(akses.getpermintaan_radiologi());
        }
        btnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        FlayMenu.setVisible(true); 
    }//GEN-LAST:event_btnToolRadActionPerformed

    void btnSMSActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHome.dispose();
        new frmSmsView().setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnRekapBulanan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulanan2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        isTutup();
        DlgHome.dispose();    
        InformasiAnalisaKamin analisakamin=new InformasiAnalisaKamin(this,true);
        analisakamin.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        analisakamin.setLocationRelativeTo(PanelUtama);
        analisakamin.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapBulanan2ActionPerformed

    void btnfee_rujukan_ranapActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void MnAnjunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAnjunganActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar anjunganmandiri.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        } 
    }//GEN-LAST:event_MnAnjunganActionPerformed

    private void MnRekapHadir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir3ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar antrianloket.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        } 
    }//GEN-LAST:event_MnRekapHadir3ActionPerformed

    private void MnRekapHadir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir4ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar antrianpoli.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        } 
    }//GEN-LAST:event_MnRekapHadir4ActionPerformed

    void btnRetensiRMActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRetensi retensi=new DlgRetensi(this,false);
        try {
            retensi.loadURL("http://" +koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"medrec/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
        
        retensi.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        retensi.setLocationRelativeTo(PanelUtama);
        retensi.setVisible(true);        
        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }
        
        setToolbar();
    }//GEN-LAST:event_formComponentMoved

    private void BtnToolJualObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolJualObatActionPerformed
        isTutup();
        FlayMenu.removeAll();        
        FlayMenu.add(btnInputPenjualan);
        FlayMenu.add(btnDataPenjualan);
        FlayMenu.add(btnDataPenyerahanDarah);
        FlayMenu.add(btnDaftarPermintaanResep);
        FlayMenu.add(btnResepObatDepan);
        btnInputPenjualan.setEnabled(akses.getpenjualan_obat());
        btnDataPenjualan.setEnabled(akses.getpenjualan_obat());
        btnDataPenyerahanDarah.setEnabled(akses.getutd_penyerahan_darah());
        btnDaftarPermintaanResep.setEnabled(akses.getresep_dokter());
        btnResepObatDepan.setEnabled(akses.getresep_obat());
        FlayMenu.setVisible(true);       
    }//GEN-LAST:event_BtnToolJualObatActionPerformed

    private void PanelWallMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelWallMouseMoved
        setToolbar();
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }
    }//GEN-LAST:event_PanelWallMouseMoved

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setToolbar();
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        isTampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        isTampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        isTampil();
                    }
                }
            });
        } 
        
        cariNIK.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    if(cariNIK.getTable().getSelectedRow()!= -1){                   
                        coder_nik=cariNIK.getTable().getValueAt(cariNIK.getTable().getSelectedRow(),2).toString();
                        isTutup();
                        try {
                            inacbgklaim.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"inacbg/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&page="+pilihpage+"&codernik="+coder_nik);                    
                        } catch (Exception ex) {
                            System.out.println("Notifikasi : "+ex);
                        }

                        inacbgklaim.setJudul(judulform);
                        inacbgklaim.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
                        inacbgklaim.setLocationRelativeTo(PanelUtama);                    
                        inacbgklaim.setVisible(true);        
                        DlgHome.dispose();
                    }                         
                }                
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }//GEN-LAST:event_formWindowOpened

    private void MnInfoBedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBedActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("bed3.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBedActionPerformed

    private void btnInputPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenjualan penjualan=new DlgPenjualan(this,false);
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnInputPenjualanActionPerformed

    private void btnDataPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPenjualan penjualan=new DlgCariPenjualan(this,false);
        penjualan.emptTeks();
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPenjualanActionPerformed

    private void btnDataPenyerahanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenyerahanDarahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UTDCariPenyerahanDarah carijual=new UTDCariPenyerahanDarah(null,false);
        carijual.emptTeks();
        carijual.isCek();
        carijual.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        carijual.setLocationRelativeTo(PanelUtama);
        carijual.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPenyerahanDarahActionPerformed

    private void MnInfoBed1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBed1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("jadwaldokter2.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBed1ActionPerformed

    private void MnInfoBed2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBed2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("bed4.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBed2ActionPerformed

    void btnInaCBGKlaimBaruOtomatisActionPerformed(java.awt.event.ActionEvent evt) {
        if(akses.getkode().equals("Admin Utama")){
            pilihpage="KlaimBaruOtomatis";
            judulform="::[ Otomatisasi Klaim Pasien Baru Dari Data SEP Ke INACBG ]::";
            isTutup();
            DlgHome.dispose();
            cariNIK.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
            cariNIK.setLocationRelativeTo(PanelUtama);
            cariNIK.setVisible(true);
        }else{
            coder_nik=Sequel.cariIsi("select inacbg_coder_nik.no_ik from inacbg_coder_nik where inacbg_coder_nik.nik=?",akses.getkode());
            if(!coder_nik.equals("")){
                isTutup();
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    inacbgklaim.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"inacbg/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&page=KlaimBaruOtomatis&codernik="+coder_nik);                    
                } catch (Exception ex) {
                    System.out.println("Notifikasi : "+ex);
                }

                inacbgklaim.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
                inacbgklaim.setLocationRelativeTo(PanelUtama);
                inacbgklaim.setJudul("::[ Otomatisasi Klaim Pasien Baru Dari Data SEP Ke INACBG ]::");
                inacbgklaim.setVisible(true);        
                DlgHome.dispose();
                this.setCursor(Cursor.getDefaultCursor());
            }else{
                isTutup();
                DlgHome.dispose();
                JOptionPane.showMessageDialog(null,"Coder NIK tidak ditemukan, silahkan hubungi Admin Utama..!!");
            }            
        }
    }

    void btnInaCBGKlaimBaruManualActionPerformed(java.awt.event.ActionEvent evt) {
        if(akses.getkode().equals("Admin Utama")){
            pilihpage="KlaimBaruManual";
            judulform="::[ Klaim Manual Pasien Baru Dari Data SEP Ke INACBG ]::";
            isTutup();
            DlgHome.dispose();
            cariNIK.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
            cariNIK.setLocationRelativeTo(PanelUtama);
            cariNIK.setVisible(true);
        }else{
            coder_nik=Sequel.cariIsi("select inacbg_coder_nik.no_ik from inacbg_coder_nik where inacbg_coder_nik.nik=?",akses.getkode());
            if(!coder_nik.equals("")){
                isTutup();
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    inacbgklaim.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"inacbg/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&page=KlaimBaruManual&codernik="+coder_nik);                    
                } catch (Exception ex) {
                    System.out.println("Notifikasi : "+ex);
                }

                inacbgklaim.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
                inacbgklaim.setLocationRelativeTo(PanelUtama);
                inacbgklaim.setJudul("::[ Klaim Manual Pasien Baru Dari Data SEP Ke INACBG ]::");
                inacbgklaim.setVisible(true);        
                DlgHome.dispose();
                this.setCursor(Cursor.getDefaultCursor());
            }else{
                isTutup();
                DlgHome.dispose();
                JOptionPane.showMessageDialog(null,"Coder NIK tidak ditemukan, silahkan hubungi Admin Utama..!!");
            }            
        }
    }

    void btnMutasiBerkasActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMutasiBerkas mutasiberkas=new DlgMutasiBerkas(this,false);
        mutasiberkas.setJudul("::[ Mutasi Berkas Rekam Medis ]::","monitoringberkas/pages");
        try {
            mutasiberkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"monitoringberkas/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        mutasiberkas.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        mutasiberkas.setLocationRelativeTo(PanelUtama);        
        mutasiberkas.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        if(ChkInput.isSelected()==true){
            cmbMenu.setVisible(true);
            cmbMenu.requestFocus();
            TCari.setVisible(false);
        }else{
            cmbMenu.setVisible(false);
            TCari.setVisible(true);
            TCari.requestFocus();            
        }
        isTampil();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isTampil();
        }
    }//GEN-LAST:event_TCariKeyPressed

    void btnDaftarPermintaanResepActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDaftarPermintaanResep daftar=new DlgDaftarPermintaanResep(null,false);
        daftar.emptTeks();
        daftar.isCek();
        daftar.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        daftar.setLocationRelativeTo(PanelUtama);
        daftar.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnBerkasDigitalPerawatanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBerkasRawat berkas=new DlgBerkasRawat(this,false);
        berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
        try {
            if(akses.gethapus_berkas_digital_perawatan()==true){
                berkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");   
            }else{
                berkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/loginnonhapus.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");  
            }                  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        berkas.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        berkas.setLocationRelativeTo(PanelUtama);        
        berkas.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnInfoBed3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBed3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("bed5.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBed3ActionPerformed

    void btnInaCBGKlaimBaruManual2ActionPerformed(java.awt.event.ActionEvent evt) {
        if(akses.getkode().equals("Admin Utama")){
            pilihpage="KlaimBaruManual2";
            judulform="::[ Klaim Manual Ke INACBG ]::";
            isTutup();
            DlgHome.dispose();
            cariNIK.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
            cariNIK.setLocationRelativeTo(PanelUtama);
            cariNIK.setVisible(true);
        }else{
            coder_nik=Sequel.cariIsi("select inacbg_coder_nik.no_ik from inacbg_coder_nik where inacbg_coder_nik.nik=?",akses.getkode());
            if(!coder_nik.equals("")){
                isTutup();
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    inacbgklaim.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"inacbg/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&page=KlaimBaruManual2&codernik="+coder_nik);                    
                } catch (Exception ex) {
                    System.out.println("Notifikasi : "+ex);
                }

                inacbgklaim.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
                inacbgklaim.setLocationRelativeTo(PanelUtama);
                inacbgklaim.setJudul("::[ Klaim Manual Ke INACBG ]::");
                inacbgklaim.setVisible(true);        
                DlgHome.dispose();
                this.setCursor(Cursor.getDefaultCursor());
            }else{
                isTutup();
                DlgHome.dispose();
                JOptionPane.showMessageDialog(null,"Coder NIK tidak ditemukan, silahkan hubungi Admin Utama..!!");
            }            
        }
    }

    void btnStokKeluarMedisActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengeluaranApotek pengeluaran=new DlgPengeluaranApotek(this,false);
        pengeluaran.tampilkanpermintaan=true;
        pengeluaran.isCek();
        pengeluaran.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pengeluaran.setLocationRelativeTo(PanelUtama);
        pengeluaran.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnPengaduanActionPerformed(java.awt.event.ActionEvent evt) {
        frmPengaduan pengaduan=new frmPengaduan();
        pengaduan.setSize(800, PanelUtama.getHeight()-40);
        pengaduan.setLocationRelativeTo(null);
        pengaduan.setVisible(true);
        DlgHome.dispose();
    }

    private void MnInfoBed4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBed4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("poli.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBed4ActionPerformed

    private void btnResepObatDepanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepObatDepanActionPerformed
        btnResepObatActionPerformed(evt);
    }//GEN-LAST:event_btnResepObatDepanActionPerformed

    void btnRiwayatJabatanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatJabatan form=new DlgRiwayatJabatan(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatjabatan.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnRiwayatPendidikanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatPendidikan form=new DlgRiwayatPendidikan(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatpendidikan.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnRiwayatNaikGajiActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatNaikGaji form=new DlgRiwayatNaikGaji(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatgaji.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnKegiatanIlmiahActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKegiatanIlmiah form=new DlgKegiatanIlmiah(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatseminar.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnRiwayatPenghargaanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatPenghargaan form=new DlgRiwayatPenghargaan(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatpenghargaan.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    void btnRiwayatPenelitianActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatPenelitian form=new DlgRiwayatPenelitian(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatpenelitian.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnInfoBed5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoBed5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("jadwaloperasi.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoBed5ActionPerformed

    private void MnRekapHadir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir5ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar antrianapotek.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        }
    }//GEN-LAST:event_MnRekapHadir5ActionPerformed

    private void MnRekapBulanan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulanan3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHome.dispose();
        InformasiTelusurKunjunganPasien belum=new InformasiTelusurKunjunganPasien(this,false);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapBulanan3ActionPerformed

    private void MnRekapHadir6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("berkasrawat");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadir6ActionPerformed

    private void MnKoleksiPenelitianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKoleksiPenelitianActionPerformed
        btnPenelitianPerpustakaanActionPerformed(null);
    }//GEN-LAST:event_MnKoleksiPenelitianActionPerformed

    private void MnCariEbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariEbookActionPerformed
        btnCariEbookActionPerformed(null);
    }//GEN-LAST:event_MnCariEbookActionPerformed

    private void MnCariInventarisPerpustakaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariInventarisPerpustakaanActionPerformed
        btnCariInventarisPerpustakaanActionPerformed(null);
    }//GEN-LAST:event_MnCariInventarisPerpustakaanActionPerformed

    private void MnPengajuanCutiPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengajuanCutiPegawaiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        PengajuanCutiPegawai hadir=new PengajuanCutiPegawai(this,false);
        hadir.isCek();
        hadir.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        hadir.setLocationRelativeTo(PanelUtama);
        hadir.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPengajuanCutiPegawaiActionPerformed

    private void MnRekapBulanan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulanan4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHome.dispose();
        InformasiStokDarah belum=new InformasiStokDarah(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapBulanan4ActionPerformed

    private void MnInfoLaboratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoLaboratActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianlaborat.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoLaboratActionPerformed

    private void MnInfoLaborat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoLaborat1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianlaborat2.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoLaborat1ActionPerformed

    private void MnInfoLaborat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoLaborat2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianradiologi.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoLaborat2ActionPerformed

    private void MnRekapHadir7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianfarmasi.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadir7ActionPerformed

    private void MnRekapHadir9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir9ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar KhanzaCetakAntrianLoket.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        } 
    }//GEN-LAST:event_MnRekapHadir9ActionPerformed

    private void MnRekapHadir10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir10ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Runtime.getRuntime().exec("java -jar KhanzaAntrianLoket2.jar");
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.print("Notifikasi : "+e);
        } 
    }//GEN-LAST:event_MnRekapHadir10ActionPerformed

    private void MnInfoLaborat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoLaborat3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianlaborat3.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoLaborat3ActionPerformed

    private void MnRekapHadir8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir8ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianfarmasiracikan.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadir8ActionPerformed

    private void MnRekapHadir11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHadir11ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianfarmasinonracikan.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHadir11ActionPerformed

    private void MnInfoMobileJKNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoMobileJKNActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.panggilUrl("antrianmobilejkn.php");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoMobileJKNActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        isTutup();
        DlgAbout About=new DlgAbout(this,false);
        About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
        About.setLocationRelativeTo(PanelWall); 
        About.setVisible(true); 
    }//GEN-LAST:event_jMenu4MouseClicked

    void btnPenelitianPerpustakaanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PerpustakaanPenelitian form=new PerpustakaanPenelitian(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginperpustakaanpenelitian.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    void btnEbookPerpustakaanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PerpustakaanEbook form=new PerpustakaanEbook(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"ebook/login.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    void btnCariEbookActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PerpustakaanCariEbook form=new PerpustakaanCariEbook(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"ebook/login2.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    void btnCariInventarisPerpustakaanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PerpustakaanCariInventaris form=new PerpustakaanCariInventaris(this,false);
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    void btnBookingPeriksaActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        DlgHome.dispose();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBookingPeriksa aplikasi=new DlgBookingPeriksa();
        aplikasi.isCek();
        aplikasi.setSize(800, PanelUtama.getHeight()-40);
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    
    void btnRiwayatSuratPeringatanActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatSuratPeringatan form=new DlgRiwayatSuratPeringatan(this,false);
        try {
            form.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/loginriwayatsuratperingatan.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"");                    
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);        
        form.setVisible(true);        
        DlgHome.dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new frmUtama().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCancel;
    private widget.ButtonBig BtnClose;
    private widget.ButtonBig BtnLog;
    private widget.Button BtnLogin;
    private widget.ButtonBig BtnMenu;
    private widget.ButtonBig BtnToolJualObat;
    private widget.ButtonBig BtnToolKamnap;
    private widget.ButtonBig BtnToolKasir;
    private widget.ButtonBig BtnToolReg;
    private widget.CekBox ChkInput;
    private javax.swing.JDialog DlgHome;
    private javax.swing.JDialog DlgLogin;
    private usu.widget.glass.PanelGlass FlayMenu;
    private javax.swing.JLabel LblIP;
    private widget.MenuBar MenuBar;
    private javax.swing.JMenuItem MenuKeluar;
    private javax.swing.JMenuItem MnAnjungan;
    private javax.swing.JMenu MnAntrianFarmasi;
    private javax.swing.JMenu MnAntrianLaborat;
    private javax.swing.JMenuItem MnBelumDatang;
    private javax.swing.JMenuItem MnBelumDatang1;
    private javax.swing.JMenuItem MnCariEbook;
    private javax.swing.JMenuItem MnCariInventarisPerpustakaan;
    private javax.swing.JMenuItem MnGantiPassword;
    private javax.swing.JMenuItem MnInfoBed;
    private javax.swing.JMenuItem MnInfoBed1;
    private javax.swing.JMenuItem MnInfoBed2;
    private javax.swing.JMenuItem MnInfoBed3;
    private javax.swing.JMenuItem MnInfoBed4;
    private javax.swing.JMenuItem MnInfoBed5;
    private javax.swing.JMenuItem MnInfoLaborat;
    private javax.swing.JMenuItem MnInfoLaborat1;
    private javax.swing.JMenuItem MnInfoLaborat2;
    private javax.swing.JMenuItem MnInfoLaborat3;
    private javax.swing.JMenuItem MnInfoMobileJKN;
    private javax.swing.JMenuItem MnKoleksiPenelitian;
    private javax.swing.JMenuItem MnLogin;
    private javax.swing.JMenuItem MnPengajuanCutiPegawai;
    private javax.swing.JMenuItem MnRekapBulanan;
    private javax.swing.JMenuItem MnRekapBulanan1;
    private javax.swing.JMenuItem MnRekapBulanan2;
    private javax.swing.JMenuItem MnRekapBulanan3;
    private javax.swing.JMenuItem MnRekapBulanan4;
    private javax.swing.JMenuItem MnRekapHadir;
    private javax.swing.JMenuItem MnRekapHadir1;
    private javax.swing.JMenuItem MnRekapHadir10;
    private javax.swing.JMenuItem MnRekapHadir11;
    private javax.swing.JMenuItem MnRekapHadir3;
    private javax.swing.JMenuItem MnRekapHadir4;
    private javax.swing.JMenuItem MnRekapHadir5;
    private javax.swing.JMenuItem MnRekapHadir6;
    private javax.swing.JMenuItem MnRekapHadir7;
    private javax.swing.JMenuItem MnRekapHadir8;
    private javax.swing.JMenuItem MnRekapHadir9;
    private javax.swing.JMenuItem MnSudahPulang;
    private javax.swing.JMenuItem MnSudahPulang1;
    private javax.swing.JMenuItem MnSudahPulang2;
    private javax.swing.JMenuItem MnSudahPulang3;
    private javax.swing.JMenuItem MnSudahPulang4;
    private javax.swing.JMenuItem MnSudahPulang5;
    private javax.swing.JMenuItem MnSudahPulang6;
    private javax.swing.JMenu MnTarif;
    private javax.swing.JMenu MnTarif1;
    private javax.swing.JPanel PanelUtama;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.panelGlass Panelmenu;
    private widget.TextBox TCari;
    private widget.ButtonBig btnDataPenjualan;
    private widget.ButtonBig btnDataPenyerahanDarah;
    private widget.ButtonBig btnInputPenjualan;
    private widget.ButtonBig btnResepObatDepan;
    private widget.ButtonBig btnToolIGD;
    private widget.ButtonBig btnToolLab;
    private widget.ButtonBig btnToolRad;
    private widget.Button button1;
    private widget.ComboBox cmbMenu;
    private widget.PasswordBox edAdmin;
    private widget.PasswordBox edPwd;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label35;
    private widget.Label label36;
    private javax.swing.JLabel lblStts;
    private javax.swing.JLabel lblTgl;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUser1;
    private usu.widget.glass.PanelGlass panelGlass1;
    private usu.widget.glass.PanelGlass panelJudul;
    private widget.InternalFrame panelMenu;
    private widget.panelisi panelisi2;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Tanggal tanggal;
    // End of variables declaration//GEN-END:variables
    private widget.ButtonBig btnPermintaanLab,btnPermintaanLabPA,btnPermintaanLabMB,btnLaboratorium,btnLaboratoriumPA,btnLaboratoriumMB,btnPermintaanRadiologi,btnPeriksaRadiologi,btnDaftarPermintaanResep;
    private MenuUtama menu;
    
    private void initMenu(){
        menu=new MenuUtama(Panelmenu,this,PanelUtama,() -> {
            isTutup();
            DlgHome.dispose();
        });
        new DaftarMenuUtama(menu,this).isi();
        btnPermintaanLab=menu.tombol("btnPermintaanLab");
        btnPermintaanLabPA=menu.tombol("btnPermintaanLabPA");
        btnPermintaanLabMB=menu.tombol("btnPermintaanLabMB");
        btnLaboratorium=menu.tombol("btnLaboratorium");
        btnLaboratoriumPA=menu.tombol("btnLaboratoriumPA");
        btnLaboratoriumMB=menu.tombol("btnLaboratoriumMB");
        btnPermintaanRadiologi=menu.tombol("btnPermintaanRadiologi");
        btnPeriksaRadiologi=menu.tombol("btnPeriksaRadiologi");
        btnDaftarPermintaanResep=menu.tombol("btnDaftarPermintaanResep");
    }

    boolean isTNI(){
        return tampilkantni.equals("Yes");
    }
    
    public void isWall(){
        try{            
            ps=koneksi.prepareStatement("select setting.nama_instansi,setting.alamat_instansi,setting.kabupaten,setting.propinsi,setting.aktifkan,setting.wallpaper,setting.kontak,setting.email,setting.logo,setting.kode_ppk,setting.kode_ppkkemenkes from setting");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    jLabel8.setText(rs.getString(1));
                    this.setTitle("SIM "+rs.getString("nama_instansi"));
                    jLabel11.setText(rs.getString(2) +", "+rs.getString(3) +", "+rs.getString(4) +" ");
                    akses.setnamars(rs.getString("nama_instansi"));
                    akses.setalamatrs(rs.getString("alamat_instansi"));
                    akses.setkabupatenrs(rs.getString("kabupaten"));
                    akses.setpropinsirs(rs.getString("propinsi"));
                    akses.setkontakrs(rs.getString("kontak"));
                    akses.setemailrs(rs.getString("email"));
                    akses.setkodeppkbpjs(rs.getString("kode_ppk"));
                    akses.setkodeppkkemenkes(rs.getString("kode_ppkkemenkes"));
                    if(rs.getString(5).equals("Yes")){
                        Blob blob = rs.getBlob(6);
                        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(blob.getBytes(1, (int) (blob.length()))));
                        repaint();
                    }
                }  
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }                 
        }catch(Exception e){
            System.out.println("Notifikasi : Silahkan hubungi administrator ");
        }
    }

    private void isTutup() {
        FlayMenu.setVisible(false);
        akses.setform("frmUtama");
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.dispose();
            }
        }
    }
    
    private void setToolbar(){
        if(internalFrame1.getWidth()<(BtnMenu.getWidth()+BtnToolReg.getWidth()+btnToolIGD.getWidth()+
                btnToolLab.getWidth()+btnToolRad.getWidth()+BtnToolJualObat.getWidth()+BtnToolKamnap.getWidth()+
                BtnToolKasir.getWidth()+BtnLog.getWidth()+BtnClose.getWidth()+8)){
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(),90));
        }else{
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(),44));
        }
    }
    
    public void isTampil(){        
        Panelmenu.removeAll();
        
        if(ChkInput.isSelected()==true){
            isCombo();  
        }else{
            if(TCari.getText().equals("")){
                jmlmenu=menu.tampilSemua();
            }else{
                jmlmenu=menu.tampilCari(TCari.getText());
            }    
        }
            
        setLayout();              
    }
    
    private void setLayout() {
        if(jmlmenu<=1){
            grid=1;
        }else if(jmlmenu<=4){
            grid=2;
        }else if(jmlmenu<=9){
            grid=3;
        }else if(jmlmenu<=16){
            grid=4;
        }else if(jmlmenu>16){
            grid=5;
        }
        
        if(jmlmenu<=20){
            tinggi=scrollPane2.getHeight()-5;
        }else if(jmlmenu<=25){
            tinggi=scrollPane2.getHeight()+(scrollPane2.getHeight()/4);
        }else if(jmlmenu>25){
            tinggi=1;
            for(i=25;i<jmlmenu;i++){
                if(i%5==0){
                    tinggi++;
                }
            }
            tinggi=scrollPane2.getHeight()+((scrollPane2.getHeight()/4)*tinggi);
        }
        
        Panelmenu.setLayout(new GridLayout(0,grid));
        Panelmenu.setPreferredSize(new Dimension(scrollPane2.getWidth()-10,tinggi));
        Panelmenu.revalidate();
        Panelmenu.repaint(); 
    }
    
    

    private void isCombo() {
        jmlmenu=menu.tampilKategori(cmbMenu.getSelectedIndex());
    }

    /** Dipisah dari initKhanza() karena metode itu sudah mentok
     * batas 64KB bytecode per metode. */
    /** Dipisah dari initKhanza() karena metode itu sudah mentok
     * batas 64KB bytecode per metode. */
}
