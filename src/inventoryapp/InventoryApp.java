/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventoryapp;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Barang {
    private static int idCounter = 1;
    private final int id;
    private String nama;
    private int jumlah;
    private double harga;

    public Barang(String nama, int jumlah, double harga) {
        this.id = idCounter++;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public int getJumlah() { return jumlah; }
    public double getHarga() { return harga; }

    public void setNama(String nama) { this.nama = nama; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public void setHarga(double harga) { this.harga = harga; }

    @Override
    public String toString() {
        return id + " - " + nama + " | Jumlah: " + jumlah + " | Harga: " + harga;
    }
}

class Utility {
    // Generic method untuk menampilkan daftar barang
    public static <T> void printList(ArrayList<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
    
    // Method dengan wildcard untuk menampilkan daftar barang dengan tipe apapun
    public static void displayItems(ArrayList<? extends Barang> list) {
        for (Barang b : list) {
            System.out.println(b);
        }
    }
}

public class InventoryApp {
    private final ArrayList<Barang> daftarBarang = new ArrayList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> listView = new JList<>(listModel);
    
    public InventoryApp() {
        JFrame frame = new JFrame("Manajemen Inventaris");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        JTextField namaField = new JTextField();
        JTextField jumlahField = new JTextField();
        JTextField hargaField = new JTextField();
        JButton tambahButton = new JButton("Tambah Barang");
        JButton hapusButton = new JButton("Hapus Barang");
        JButton tampilkanButton = new JButton("Tampilkan Barang");

        panel.add(new JLabel("Nama Barang:"));
        panel.add(namaField);
        panel.add(new JLabel("Jumlah:"));
        panel.add(jumlahField);
        panel.add(new JLabel("Harga:"));
        panel.add(hargaField);
        panel.add(tambahButton);
        panel.add(hapusButton);
        panel.add(tampilkanButton);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(listView), BorderLayout.CENTER);
        
        tambahButton.addActionListener(e -> {
            String nama = namaField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());
            double harga = Double.parseDouble(hargaField.getText());
            Barang barang = new Barang(nama, jumlah, harga);
            daftarBarang.add(barang);
            listModel.addElement(barang.toString());
        });

        hapusButton.addActionListener(e -> {
            int index = listView.getSelectedIndex();
            if (index != -1) {
                daftarBarang.remove(index);
                listModel.remove(index);
            }
        });
        
        tampilkanButton.addActionListener(e -> {
            Utility.displayItems(daftarBarang);
        });

        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new InventoryApp();
    }
}
