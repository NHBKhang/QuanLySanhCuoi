package com.mycompany.quanly;


import static com.mycompany.quanly.QuanLy.isValid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SanhCuoi {
	private String maSanh = null;

	private String tenSanh;

	private int viTri;

	private long sucChua;
        
        private long giaThue;
        
        private final String url = "D:\\Data\\SanhCuoi.txt";
        private File file = new File (getUrl());
        
        private final String ngatFile =",";
        
        private GiaThue gia;
        
        private static List<SanhCuoi> cacSanh = new ArrayList<>();
        
        private String form = "%-10s%-15s%-10s%-10s%-10s%n";
        private String[] header = new String[] {"Ma Sanh","Ten Sanh","Vi Tri","Suc Chua","Gia Thue"};
        
//        phuong thuc chuc nang cua sanh cuoi
	public void them() throws FileNotFoundException, IOException {
            try (FileWriter fileWriter = new FileWriter(getFile(), true)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    writer.print(this.maSanh + ngatFile);
                    writer.print(this.tenSanh + ngatFile);
                    writer.print(Integer.toString(this.viTri) + ngatFile);
                    writer.print(Long.toString(this.sucChua) + ngatFile);
                    writer.print(this.getGiaThue() + ngatFile);
                    writer.println();
                }
            } catch (Exception e){
                System.out.println(e);
            }
            cacSanh.add(this);
	}
        
        public void capNhat(String noiDung) throws IOException {
            String[] string = noiDung.split(this.ngatFile);
            for (String s: string){
                s.trim();
            }
            for (int i = 0; i < string.length; i++){
                this.capNhat(string[i], i+1);
            }
	}
        
	public void capNhat(String noiDung, int viTriSua) throws IOException {
            if (viTriSua == 1){
                this.tenSanh = noiDung.toUpperCase();
            }
            else if (viTriSua == 2){
                this.viTri = Integer.parseInt(noiDung);
            }
            else if (viTriSua == 3){
                this.sucChua = Long.parseLong(noiDung);
            }
            else{
                this.giaThue = Long.parseLong(noiDung);
            }
	}
        
        //  phuong thuc ho tro cap nhat va xoa
        public SanhCuoi tim(String maSanh){
            SanhCuoi sanh = new SanhCuoi();
            for (SanhCuoi s: cacSanh){
                if (s.maSanh.toLowerCase().equals(maSanh.toLowerCase())){
                    sanh = s;
                    break;
                }
            }
            return sanh;
        }
        //
        
	public void xoa() throws IOException {
            cacSanh.remove(this);
            rewriteFile();
	}
        
        // ghi lai file / overwrite file
        public void rewriteFile() throws IOException{
            try (FileWriter fileWriter = new FileWriter(file, false)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    for (SanhCuoi s: cacSanh){
                        writer.print(s.maSanh + ngatFile);
                        writer.print(s.tenSanh + ngatFile);
                        writer.print(Integer.toString(s.viTri) + ngatFile);
                        writer.print(Long.toString(s.sucChua) + ngatFile);
                        writer.print(s.getGiaThue() + ngatFile);
                        writer.println();
                    }
                }
            } catch (Exception e){
                 System.out.println(e);
            }
        }

	public List<SanhCuoi> traCuu(String noiDung, int i){
            List<SanhCuoi> sc = new ArrayList<>();
            for (SanhCuoi sanh: cacSanh){
                if (i == 0){
                    if (sanh.tenSanh.toLowerCase().contains(noiDung.toLowerCase())){
                        sc.add(sanh);
                    }
                }
                else if (i == 1){
                    if (sanh.viTri == Integer.parseInt(noiDung)){
                        sc.add(sanh);
                    }
                }
                else if (i == 2){
                    if (sanh.sucChua >= Long.parseLong(noiDung)){
                        sc.add(sanh);
                    }
                }
                else {
                    if (sanh.giaThue >= Long.parseLong(noiDung)){
                        sc.add(sanh);
                    }
                }
            }
            return sc;
	}
        // ket thuc chuc nang
        
        public static void hienThi(List<SanhCuoi> sanhCuoi, SanhCuoi sc){
            System.out.format(sc.getForm(), sc.getHeader());
            for (Object[] row : toTable(sanhCuoi, sanhCuoi.size())){
                System.out.format(sc.getForm(), row);
            }
        }
        
        public static void hienThi(SanhCuoi sanhCuoi){
            System.out.format(sanhCuoi.getForm(), sanhCuoi.getHeader());
            Object[] sanh = new String[] {sanhCuoi.maSanh,
                                          sanhCuoi.tenSanh,
                                          String.valueOf(sanhCuoi.viTri),
                                          String.valueOf(sanhCuoi.sucChua),
                                          String.valueOf(sanhCuoi.giaThue)};
            System.out.format(sanhCuoi.getForm(), sanh);
        }
        
        public static void hienThiFile(SanhCuoi sc){
                System.out.format(sc.getForm(), sc.getHeader());
                for (Object[] row : toTable(getCacSanh(), getCacSanh().size())){
                    System.out.format(sc.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<SanhCuoi> sanh, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {sanh.get(i).maSanh,
                                         sanh.get(i).tenSanh,
                                         String.valueOf(sanh.get(i).viTri),
                                         String.valueOf(sanh.get(i).sucChua),
                                         String.valueOf(sanh.get(i).giaThue)};
            }
            return table;
        }
        

        
    //      phuong thuc khoi tao sanh cuoi da co
            public SanhCuoi(String maSanhCuoi, String tenSanh, int viTri, long sucChua, long giaThue){
                this.maSanh = maSanhCuoi.toUpperCase();
                this.tenSanh = tenSanh.toUpperCase();
                this.viTri = viTri;
                this.sucChua = sucChua;
                this.giaThue = giaThue;
            }        
        
    //      phuong thuc khoi tao khong tham so
            public SanhCuoi(){}

        
    //      phuong thuc khoi tao sanh cuoi moi
            public SanhCuoi(String tenSanh, int viTri, long sucChua, long giaThue) throws FileNotFoundException{
                this.maSanh = nextMaSanh(docMaSanh());
                this.tenSanh = tenSanh.toUpperCase();
                this.viTri = viTri;
                this.sucChua = sucChua;
                this.giaThue = giaThue;
            }
        
//        phuong thuc ho tro tao ma so moi
        private String nextMaSanh(String maSanh){
            if (maSanh == null){
                maSanh = "S001";
            }
            else {
                String[] catMaSanh = maSanh.split("S") ;
                int nextMa = Integer.parseInt(catMaSanh[1]) + 1;
                maSanh = String.format("S%03d", nextMa);
            }
            return maSanh;  
        }
        
        private String docMaSanh() throws FileNotFoundException{
            String maSanh = null;
            File file = new File(this.url);
            if (file.length() != 0){
                try (Scanner scanner = new Scanner(file)){   
                    scanner.useDelimiter(this.ngatFile);
                    while (scanner.hasNextLine()){
                        maSanh = scanner.next();
                        scanner.nextLine();
                    }
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }
            return maSanh;
        }
        
        

    /**
     * @return the maSanh
     */
    public String getMaSanh() {
        return maSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    /**
     * @return the tenSanh
     */
    public String getTenSanh() {
        return tenSanh;
    }

    /**
     * @param tenSanh the tenSanh to set
     */
    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    /**
     * @return the viTri
     */
    public int getViTri() {
        return viTri;
    }

    /**
     * @param viTri the viTri to set
     */
    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    /**
     * @return the sucChua
     */
    public long getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(long sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the ngatFile
     */
    public String getNgatFile() {
        return ngatFile;
    }

    /**
     * @return the giaThue
     */
    public long getGiaThue() {
        return giaThue;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * @return the header
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * @return the cacSanh
     */
    public static List<SanhCuoi> getCacSanh() {
        return cacSanh;
    }

    /**
     * @param giaThue the giaThue to set
     */
    public void setGiaThue(long giaThue) {
        this.giaThue = giaThue;
    }


    public void init() throws FileNotFoundException {;
        try (Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                String maSC = scanner.next();
                String tenSanh = scanner.next();
                int viTri = Integer.parseInt(scanner.next());
                long sucChua = Long.parseLong(scanner.next());
                long giaThue = Long.parseLong(scanner.next());
                SanhCuoi sanh = new SanhCuoi(maSC, tenSanh, viTri, sucChua, giaThue);
                cacSanh.add(sanh);
                scanner.nextLine();
            }
        } catch (Exception e){
            System.out.println(e);
        } 
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(GiaThue gia) {
        this.gia = gia;
    }

    /**
     * @return the giaTong
     */
    public long getGiaTong() {
        return (long) (gia.getHeSoTong()*this.giaThue);
    }
        /**
     * @return the gia
     */
    public GiaThue getGia() {
        return gia;
    }

    public SanhCuoi Nhap() {
        Scanner scanner = new Scanner(System.in);
        int flag = -1, chon, buoi;
        String ma, date;
        SanhCuoi sanh = new SanhCuoi();
        hienThiFile(sanh);
        do{
            do{
                System.out.print("Chon sanh (vd:S001): ");
                ma = scanner.nextLine().toUpperCase();
                String[] cat = ma.split(" ");
                for (SanhCuoi s: getCacSanh()){
                    if (s.getMaSanh().equals(cat[0].trim())){
                        sanh = s;
                        flag = 0;
                        break;
                    }
                }
            }
            while (flag == -1);
            String[] c;
            do{
                System.out.print("Nhap ngay (dd/mm/yyyy): ");
                date = scanner.nextLine();
                c = date.split(" ");
            }
            while (!isValid(c[0]));
            do{
                System.out.print("1.Sang | 2.Chieu | 3.Toi\nChon Buoi: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    buoi = Integer.parseInt(cat[0]);
                    if (buoi != 1 && buoi != 2 && buoi != 3) buoi = -1;                     
                } catch (Exception e){
                    buoi = -1;
                }
            }
            while (buoi == -1);
            do{
                System.out.print("Luu lua chon? \t 1.Yes  |  2.No\nChon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(cat[0]);
                    if (chon != 1 && chon != 2) chon = -1;
                } catch (Exception e){
                    chon = -1;
                }
            }
            while (chon == -1);
            if (chon == 1)
                break;
            else
                continue;
        }
        while (true);
        GiaThue gia = null;
        try {
            gia = new GiaThue(new HeSoGiaThue(buoi - 1), date);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        sanh.setGia(gia);
        return sanh;
    }

    //   nhap cho quan ly
    public void nhap(){
        int chon = 0, flag = -1;
        Scanner scanner = new Scanner(System.in);
        hienThiFile(this);
        System.out.println("1.Them | 2.Cap nhat | 3.Xoa | 4.Tra cuu");
        do {
            System.out.print("Chon: ");
            String s = scanner.nextLine();
            String[] cat = s.split(" ");
            try {
                chon = Integer.parseInt(cat[0]);
                if (chon > 0 && chon < 5) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        //  them
        if (chon == 1){
            do{
                flag = -1;
                SanhCuoi sanh = new SanhCuoi();
                System.out.print("Nhap ten sanh cuoi: ");
                String tenSanh = scanner.nextLine().toUpperCase();
                System.out.print("Nhap vi tri sanh cuoi: ");
                String a = scanner.nextLine();            
                String[] cat = a.split(" ");
                int viTri = 0;
                do{
                    try {
                        viTri = Integer.parseInt(cat[0].trim());
                        if (viTri > 0) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                System.out.print("Nhap suc chua sanh cuoi: ");
                a = scanner.nextLine();
                cat = a.split(" ");
                long sucChua = 0, giaThue = 0;
                do{
                    flag = -1;
                    try {
                        sucChua = Long.parseLong(cat[0].trim());
                        if (sucChua > 0) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                System.out.print("Nhap gia thue sanh cuoi: ");
                a = scanner.nextLine();
                cat = a.split(" ");
                do{
                    flag = -1;
                    try {
                       giaThue = Long.parseLong(cat[0].trim());
                        if (giaThue > 0) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                try {
                    SanhCuoi sc = new SanhCuoi(tenSanh, viTri, sucChua, giaThue);
                    sanh = sc;
                    System.out.println("- Tao sanh cuoi moi thanh cong -");
                } catch (FileNotFoundException ex) {
                    System.out.println("- Tao sanh cuoi moi khong thanh cong -");
                }
                System.out.println("Luu lua chon?\t1.Luu | 2.Khong luu va nhap lai | 3.Thoat");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon <= 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    try {
                        sanh.them();
                        System.out.println("- Luu sanh cuoi thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Luu sanh cuoi khong thanh cong -");
                    }
                    break;
                }
                else if (chon == 2)
                    continue;
                else
                    return;
            }
        while (true);
        }
        //  cap nhat
        else if (chon == 2){
            System.out.println("1.Cap nhat 1 thuoc tinh | 2.Cap nhat 1 dong");
            do {
                flag = -1;
                System.out.print("Chon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(cat[0].trim());
                    if (chon > 0 && chon < 3) flag = 0;
                    else flag = -1;
                } catch (Exception e){
                    flag = -1;
                }                
            }
            while (flag == -1);
            SanhCuoi sanh = new SanhCuoi();
            do{
                flag = -1;
                System.out.print("Chon sanh (vd:S001): ");
                String ma = scanner.nextLine().toUpperCase();
                String[] cat = ma.split(" ");
                sanh = tim(cat[0].trim());
                if (sanh.maSanh != null || sanh.maSanh == "") flag = 0;
                else flag = -1;
            }
            while (flag == -1);
            //  cap nhat 1 thuoc tinh
            if (chon == 1){
                System.out.println("1.Ten Sanh | 2.Vi Tri | 3.Suc Chua | 4.Gia Thue");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 5) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                do{
                    flag = -1;
                    do{
                        flag = -1;
                        System.out.print("Nhap noi dung sua: ");
                        String s = scanner.nextLine().toUpperCase();
                        try{
                            sanh.capNhat(s.trim(), chon);
                            flag = 0;
                        } catch (Exception e){
                            System.out.println("- Nhap sai thong tin -");
                            flag = -1;
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat sanh cuoi " + sanh.tenSanh + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
                    do {
                        flag = -1;
                        System.out.print("Chon: ");
                        String st = scanner.nextLine();
                        String[] cat = st.split(" ");
                        try {
                            chon = Integer.parseInt(cat[0].trim());
                            if (chon > 0 && chon <= 3) flag = 0;
                            else flag = -1;
                        } catch (Exception e){
                            flag = -1;
                        }                
                    }
                    while (flag == -1);
                    if (chon == 1){
                        try{
                            rewriteFile();
                            flag = 0;
                            System.out.println("- Cap nhat sanh cuoi thanh cong -");
                        } catch (Exception e){
                            System.out.println("- Cap nhat sanh cuoi khong thanh cong -");
                            flag = -1;
                        }
                        break;
                    }
                    else if (chon == 2)
                        continue;
                    else
                        return;
                }
                while (flag == -1);
            }
            //  cap nhat 1 dong
            else{
                do{
                    do{
                        flag = -1;
                        System.out.print("Nhap noi dung sua (vd: ROSE, 1, 120, 1200000): ");
                        String s = scanner.nextLine().toLowerCase();
                        String[] cat = s.split(",");
                        if (cat.length != 4) flag = -1;
                        else{
                            try{
                                sanh.capNhat(s.trim());
                                flag = 0;
                            } catch (Exception e){
                                System.out.println("- Nhap sai thong tin -");
                                flag = -1;
                            }
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat sanh cuoi " + sanh.tenSanh + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
                    do {
                        flag = -1;
                        System.out.print("Chon: ");
                        String st = scanner.nextLine();
                        String[] cat = st.split(" ");
                        try {
                            chon = Integer.parseInt(cat[0]);
                            if (chon > 0 && chon <= 3) flag = 0;
                            else flag = -1;
                        } catch (Exception e){
                            flag = -1;
                        }                
                    }
                    while (flag == -1);
                    if (chon == 1){
                        try {
                            rewriteFile();
                            System.out.println("- Cap nhat sanh cuoi thanh cong -");
                        } catch (IOException ex) {
                            System.out.println("Cap nhat sanh cuoi khong thanh cong");
                        }
                    }
                    else if (chon == 2)
                        flag = -1;
                    else
                        return;
                }
                while (flag == -1);                
            }
        }
        //   xoa sanh cuoi
        else if (chon == 3){
            do{
                SanhCuoi sanh = new SanhCuoi();
                do{
                    flag = -1;
                    System.out.print("Chon sanh (vd:S001): ");
                    String ma = scanner.nextLine().toUpperCase();
                    String[] cat = ma.split(" ");
                    sanh = tim(cat[0]);
                    if (sanh.maSanh != null) flag = 0;
                    else flag = -1;
                }
                while (flag == -1);
                System.out.println("Xoa sanh cuoi " + sanh.tenSanh + "?\t1.Xoa | 2.Khong va nhap lai | 3.Thoat");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0]);
                        if (chon > 0 && chon <= 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    try {
                        sanh.xoa();
                        System.out.println("- Xoa sanh cuoi thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Xoa sanh cuoi khong thanh cong -");
                    }
                    break;
                }
                else if (chon == 2)
                    continue;
                else
                    return;
                }
            while (true);
        }
        //  tra cuu
        else{
            do{
                System.out.println("1.Ten Sanh | 2.Vi Tri | 3.Suc Chua | 4.Gia Thue");
                do {
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 5) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                String nd;
                if (chon == 1)
                    nd = "ten sanh can tim";
                else if (chon == 2)
                    nd = "vi tri can tim";
                else if (chon == 3)
                    nd = "suc chua toi thieu";
                else
                    nd = "gia thue toi thieu";
                do{
                    System.out.print("Nhap " + nd + ": ");
                    String s = scanner.nextLine();
                    try{
                        List <SanhCuoi> sc = traCuu(s, chon - 1);
                        if (sc.size() != 0){
                            hienThi(sc, this);
                            flag = 0;
                        }
                        else{
                            System.out.println("- Khong tim thay sanh cuoi nao phu hop -");
                            flag = -1;
                        }
                    } catch (Exception e){
                        System.out.println("- Nhap sai thong tin -");
                        flag = -1;
                    }
                }
                while (flag == -1);
                System.out.println("Tim thanh cong\n1.Tim lai | 2.Thoat tim kiem");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String st = scanner.nextLine();
                    String[] cat = st.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    flag = -1;
                }
                else
                    flag = 0;
            }
            while (flag == -1);
        }
    }

}
