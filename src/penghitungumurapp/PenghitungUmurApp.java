package penghitungumurapp;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.time.ZoneId; // Untuk menangani zona waktu

public class PenghitungUmurApp extends JFrame {

    private JLabel lblTanggalLahir;
    private JSpinner dateSpinner;
    private JButton btnHitung;
    private JLabel lblUmur;
    private JTextField txtUmur;

    public PenghitungUmurApp() {
        // Mengatur frame
        setTitle("Aplikasi Penghitung Umur");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat komponen-komponen GUI
        lblTanggalLahir = new JLabel("Tanggal Lahir:");
        
        // Membuat SpinnerDateModel dengan tipe tanggal
        SpinnerDateModel model = new SpinnerDateModel();
        dateSpinner = new JSpinner(model);
        
        // Atur format agar hanya menampilkan tanggal
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy");
        dateSpinner.setEditor(editor);
        
        btnHitung = new JButton("Hitung Umur");
        lblUmur = new JLabel("Umur:");
        txtUmur = new JTextField();
        txtUmur.setEditable(false); // Mengatur agar JTextField hanya bisa menampilkan hasil (tidak bisa diubah langsung).

        // Mengatur layout menggunakan null layout (absolute layout)
        setLayout(null);

        // Mengatur posisi komponen
        lblTanggalLahir.setBounds(20, 20, 100, 20);
        dateSpinner.setBounds(120, 20, 150, 20);
        btnHitung.setBounds(90, 60, 150, 25);
        lblUmur.setBounds(20, 100, 100, 20);
        txtUmur.setBounds(120, 100, 150, 20);

        // Menambahkan komponen ke frame
        add(lblTanggalLahir);
        add(dateSpinner);
        add(btnHitung);
        add(lblUmur);
        add(txtUmur);

        // Menambahkan ActionListener untuk tombol Hitung Umur
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungUmur();
            }
        });
    }

    // Fungsi untuk menghitung umur
    private void hitungUmur() {
        // Mendapatkan tanggal lahir yang dipilih oleh pengguna
        Date date = (Date) dateSpinner.getValue();
        
        // Konversi dari Date ke LocalDate dengan mengabaikan jam
        LocalDate tanggalLahir = date.toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate();  // Hanya mengambil tanggal, tidak melibatkan waktu

        // Mengambil tanggal saat ini
        LocalDate today = LocalDate.now();

        // Menghitung umur dalam tahun, bulan, dan hari
        Period period = Period.between(tanggalLahir, today);

        // Menampilkan umur
        String umur = String.format("%d tahun, %d bulan, %d hari", period.getYears(), period.getMonths(), period.getDays());
        txtUmur.setText(umur);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PenghitungUmurApp().setVisible(true);
            }
        });
    }
}