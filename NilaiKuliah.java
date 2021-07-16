import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class NilaiKuliah {
    static List<List> arrayMatkuls = new ArrayList<List>();

    public static void main(String[] args) {

        int choice;
        while (true) {
            System.out.println("\nPendataan dan Perhitungan IPS (Indeks Prestasi Semester)\n");
            System.out.println("1.) Pendataan Matakuliah");
            System.out.println("2.) Perhitungan IPS.");
            System.out.println("3.) Update Grade.");
            System.out.println("4.) Keluar.");

            choice = inputIntegerWithValidation("\nMasukkan Pilihan Anda: ");
            switch (choice) {

                case 1:
                    pendataanMatakuliah();
                    break;

                case 2:
                    perhitunganIPS();
                    break;

                case 3:
                    updateGrade();
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan Anda salah!");
                    break;
            }
        }
    }

    // TODO: Method input data integer dengan validasi
    private static Integer inputIntegerWithValidation(String strLabel) {
        Scanner sc = new Scanner(System.in);
        int value = 0;
        do {
            System.out.print(strLabel);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                return value;
            } else {
                System.out.print("Input salah, ");
                sc.next();
            }
        } while (value == 0);

        return value;
    }

    private static void pendataanMatakuliah() {
        String sudahYakin = "Y";
        do {

            Scanner sc = new Scanner(System.in);
            int jumlahMatkul = inputIntegerWithValidation("Masukkan Jumlah Matakuliah : ");
            for (int i = 0; i < jumlahMatkul; i++) {
                List<String> arrInput = new ArrayList<String>();
                Scanner input = new Scanner(System.in);
                System.out.println("=============================================");
                System.out.print("Masukkan Kode Matakuliah : ");
                String kodeMatkul = input.nextLine();
                System.out.print("Masukkan Nama Matakuliah : ");
                String namaMatkul = input.nextLine();

                // TODO: Input grade dengan validasi A,B,C,D,E
                System.out.print("Masukkan Grade Matakuliah : ");
                String gradeMatkul = input.nextLine().toUpperCase();
                while (!(gradeMatkul.equals("A") || gradeMatkul.equals("B") || gradeMatkul.equals("C") || gradeMatkul.equals("D") || gradeMatkul.equals("E"))) {
                    System.out.print("Input salah, Masukkan Grade Matakuliah : ");
                    gradeMatkul = input.nextLine().toUpperCase();
                    break;
                }

                // TODO: Input jumlah sks dengan validasi angka
                int jumlahSKS = inputIntegerWithValidation("Masukkan Jumlah SKS : ");

                arrInput.add(kodeMatkul);
                arrInput.add(namaMatkul);
                arrInput.add(gradeMatkul);
                arrInput.add(String.valueOf(jumlahSKS));
                arrayMatkuls.add(arrInput);
            }

            System.out.print("\nAnda sudah yakin (Y/N)? ");
            sudahYakin = sc.next().toUpperCase();

        } while (sudahYakin.equals("N"));

    }

    private static void perhitunganIPS() {
        if (arrayMatkuls.size() < 1) {
            System.out.println("Data kosong");
            return;
        }
        System.out.println("Matakuliah yang Anda ambil adalah :\n");
        int multipliedGradeSKS = 0;
        int sigmaGradeSKS = 0;
        int countSKS = 0;
        for (int i = 0; i < arrayMatkuls.size(); i++) {
            String strKode = (arrayMatkuls.get(i)).get(0).toString();
            String strNama = (arrayMatkuls.get(i)).get(1).toString();
            String strGrade = (arrayMatkuls.get(i)).get(2).toString().toUpperCase();
            String strSKS = (arrayMatkuls.get(i)).get(3).toString();

            System.out.println(strKode + "    " + strNama + "    " + strGrade + "    " + strSKS);
            int grade;
            switch (strGrade) {

                case "A":
                    grade = 4;
                    break;

                case "B":
                    grade = 3;
                    break;

                case "C":
                    grade = 2;
                    break;

                case "D":
                    grade = 1;
                    break;

                default:
                    grade = 0;
                    break;
            }

            multipliedGradeSKS = grade * Integer.parseInt(strSKS);
            sigmaGradeSKS = sigmaGradeSKS + multipliedGradeSKS;
            countSKS = countSKS + Integer.parseInt(strSKS);
        }

        double nilaiIPS = (double) sigmaGradeSKS / (double) countSKS;
        System.out.println("Nilai IPS Anda adalah " + nilaiIPS);
    }

    private static void updateGrade() {
        if (arrayMatkuls.size() < 1) {
            System.out.println("Data kosong");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Kode Matakuliah : ");
        String kodeMatkul = input.nextLine();
        for (int i = 0; i < arrayMatkuls.size(); i++) {
            List<String> arrInput = new ArrayList<String>();
            String strKode = (arrayMatkuls.get(i)).get(0).toString();
            String strNamaMatkul = (arrayMatkuls.get(i)).get(1).toString();
            String strJumlahSKS = (arrayMatkuls.get(i)).get(3).toString();
            if (kodeMatkul.equals(strKode)) {
                if (!kodeMatkul.isEmpty()) {
                    Scanner update = new Scanner(System.in);

                    // TODO: Input grade dengan validasi A,B,C,D,E
                    System.out.print("Masukkan Grade Matakuliah : ");
                    String gradeMatkul = update.nextLine().toUpperCase();
                    while (!(gradeMatkul.equals("A") || gradeMatkul.equals("B") || gradeMatkul.equals("C") || gradeMatkul.equals("D") || gradeMatkul.equals("E"))) {
                        System.out.print("Input salah, Masukkan Grade Matakuliah : ");
                        gradeMatkul = input.nextLine().toUpperCase();
                        break;
                    }

                    arrInput.add(kodeMatkul);
                    arrInput.add(strNamaMatkul);
                    arrInput.add(gradeMatkul);
                    arrInput.add(String.valueOf(strJumlahSKS));

                    arrayMatkuls.set(i, arrInput);


                    for (int j = 0; j < arrayMatkuls.size(); j++) {
                        String strUpdateKode = (arrayMatkuls.get(j)).get(0).toString();
                        String strUpdateNama = (arrayMatkuls.get(j)).get(1).toString();
                        String strUpdateGrade = (arrayMatkuls.get(j)).get(2).toString().toUpperCase();
                        String strUpdateSKS = (arrayMatkuls.get(j)).get(3).toString();

                        System.out.println(strUpdateKode + "    " + strUpdateNama + "    " + strUpdateGrade + "    " + strUpdateSKS);
                    }
                } else {
                    System.out.println("kode tidak ditemukan.");
                }
            }
        }
    }
}
