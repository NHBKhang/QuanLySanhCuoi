/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ThucUong extends Menu {

    private String hangSx;
    private final String url = "D:\\Data\\ThucUong.txt";
    private File file = new File(url);
    
    private String[] header = new String[]{"Ma Mon","Ten Mon","Gia Mon","Hang San Xuat"};

    public ThucUong(String maMon, String tenMon, int giaMon, String hangSx) {
        super(maMon, tenMon, giaMon);
        this.hangSx = hangSx;
    }
    
    public ThucUong(String tenMon, int giaMon, String hangSx) throws FileNotFoundException {
        this.maMon = nextMaThucUong(docMaThucUong());
        this.tenMon = tenMon;
        this.giaMon = giaMon;
        this.hangSx = hangSx;
    }
    
    public ThucUong(){}

    private static List<ThucUong> monUong = new ArrayList<>();

    public void init() {
        try ( Scanner d = new Scanner(file)) {
            d.useDelimiter(",");
            while (d.hasNext()) {
                String maMon = d.next();
                String tenMon = d.next();
                int giaMon = d.nextInt();
                String hangSx = d.next();
                ThucUong m = new ThucUong(maMon, tenMon, giaMon, hangSx);
                monUong.add(m);
                d.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void them() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.maMon + ",");
                writer.print(this.tenMon + ",");
                writer.print(Integer.toString(this.giaMon) + ",");
                writer.print(this.hangSx + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        getMonUong().add(this);
    }

    public void capNhat(String noiDung) throws IOException {
        String[] b = noiDung.split(",");
        for (int i = 0; i < b.length; i++) {
            this.capNhat(b[i].trim(), i + 1);
        }
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setTenMon(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setGiaMon(Integer.parseInt(noiDung));
        } else {
            this.setHangSx(noiDung.toUpperCase());
        }
    }

    //Phuong Thuc Ho Tro Xoa
    public ThucUong tim(String maMon) {
        ThucUong drink = new ThucUong();
        for (ThucUong x : getMonUong()) {
            if (x.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                drink = x;
                break;
            }
        }
        return drink;
    }

    public void xoa() throws IOException {
        monUong.remove(this);
        rewriteFile();
    }

    public List<ThucUong> traCuu(String noiDung, int i) throws FileNotFoundException {
        List<ThucUong> tu = new ArrayList<>();
        for (ThucUong drink : getMonUong()) {
            if (i == 1) {
                if (drink.tenMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    tu.add(drink);
                }
            } else if (i == 2) {
                if (drink.giaMon >= Integer.parseInt(noiDung)) {
                    tu.add(drink);
                }
            } else {
                if (drink.hangSx.toLowerCase().contains(noiDung.toLowerCase())) {
                    tu.add(drink);
                }
            }
        }
        return tu;
    }

    private String nextMaThucUong(String maMon) {
        if (maMon == null) {
            maMon = "200001";
        } else {
            int nextMa = (Integer.parseInt(maMon) + 1);
            maMon = Integer.toString(nextMa);
        }
        return maMon;
    }

    private String docMaThucUong() throws FileNotFoundException {
        String maMon = null;
        File file = new File(this.url);
        if (file.length() != 0) {
            try ( Scanner scanner = new Scanner(file)) {
                scanner.useDelimiter(",");
                while (scanner.hasNextLine()) {
                    maMon = scanner.next();
                    scanner.nextLine();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return maMon;
    }

    public void rewriteFile() throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file, false)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                for (ThucUong x : getMonUong()) {
                    writer.print(x.getMaMon() + ",");
                    writer.print(x.getTenMon() + ",");
                    writer.print(Integer.toString(x.getGiaMon()) + ",");
                    writer.print(x.getHangSx() + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
     public static void hienThi(List<ThucUong> thucUong, ThucUong tu){
            System.out.format(tu.getForm(), tu.getHeader());
            for (Object[] row : toTable(thucUong, thucUong.size())){
                System.out.format(tu.getForm(), row);
            }
        }
        
        public static void hienThi(ThucUong thucUong){
            System.out.format(thucUong.getForm(), thucUong.getHeader());
            Object[] row = new String[] {thucUong.maMon,
                                         thucUong.tenMon,
                                         String.valueOf(thucUong.giaMon),
                                         thucUong.hangSx};
            System.out.format(thucUong.getForm(), row);
        }
        
        public static void hienThiFile(ThucUong tu){
                System.out.format(tu.getForm(), tu.getHeader());
                for (Object[] row : toTable(getMonUong(), getMonUong().size())){
                    System.out.format(tu.getForm(), row);
               }
        }
             
        //  tao bang
        private static Object[][] toTable(List<ThucUong> uong, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {uong.get(i).maMon,
                                         uong.get(i).tenMon,
                                         String.valueOf(uong.get(i).giaMon),
                                         uong.get(i).hangSx};
            }
            return table;
        }

    /**
     * @return the hangSx
     */
    public String getHangSx() {
        return hangSx;
    }

    /**
     * @param hangSx the hangSx to set
     */
    public void setHangSx(String hangSx) {
        this.hangSx = hangSx;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the monUong
     */
    public static List<ThucUong> getMonUong() {
        return monUong;
    }

    /**
     * @return the fileB
     */
    public File getFile() {
        return file;
    }

    /**
     * @param aMonUong the monUong to set
     */
    public static void setMonUong(List<ThucUong> aMonUong) {
        monUong = aMonUong;
    }

    /**
     * @return the header
     */
    public String[] getHeader() {
        return header;
    }

    @Override
    public String getMaMon() {
        return super.maMon;
    }

    List<ThucUong> Nhap() {
        Scanner scanner = new Scanner(System.in);
        int flag = -1, chon;
        String mon;
        List<ThucUong> thucUong = new ArrayList<>();
        ThucUong tu = new ThucUong();
        hienThiFile(tu);
        do {
            do {
                System.out.print("Chon nuoc uong (vd: 1,2): ");
                mon = scanner.nextLine();
                String[] cacMon = mon.split(",");
                for (int i = 0; i < cacMon.length; i++){;
                    try {
                        int s = Integer.parseInt(cacMon[i].trim());
                        if (s <= getMonUong().size())
                            cacMon[i] = String.format("2%05d", s);
                        flag = 0;
                    } catch (Exception e) {
                        flag = -1;
                    }
                }
                if (flag == 0){
                    for (ThucUong t: getMonUong()){
                        for (String a: cacMon){
                            if (t.getMaMon().equals(a)){
                                thucUong.add(t);
                                flag = 0;
                            }
                        }
                    }
                }
            }
            while (flag == -1 || thucUong.size() < 1);
            do{
                System.out.print("Luu lua chon? \t 1.Yes  |  2.No\nChon: ");
                String s = scanner.nextLine();
                try {
                    chon = Integer.parseInt(s);
                } catch (Exception e){
                    chon = -1;
                }
            }
            while (chon!=1&&chon!=2);
            if (chon ==1)
                break;
            else
                continue;
        }
        while (true);
        return thucUong;
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
        ThucUong drink = new ThucUong();
        if (chon == 1){
            do{
                flag = -1;
                System.out.print("Nhap ten mon: ");
                String tenMon = scanner.nextLine().toUpperCase();
                String[] cat;
                int giaMon = 0;
                do{
                    flag = -1;
                    System.out.print("Nhap gia mon: ");
                    String a = scanner.nextLine();            
                    cat = a.split(" ");
                    try {
                        giaMon = Integer.parseInt(cat[0].trim());
                        if (giaMon > 10000) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                System.out.print("Nhap hang san xuat: ");
                String hangSX = scanner.nextLine().toUpperCase();
                try {
                    ThucUong tu = new ThucUong(tenMon, giaMon, hangSX);
                    drink = tu;
                    System.out.println("- Tao thuc uong moi thanh cong -");
                } catch (FileNotFoundException ex) {
                    System.out.println("- Tao thuc uong moi khong thanh cong -");
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
                        drink.them();
                        System.out.println("- Luu thuc uong thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Luu thuc uong khong thanh cong -");
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
            String ma = "";
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
            do{
                flag = -1;
                System.out.print("Chon mon (vd:1): ");
                ma = scanner.nextLine();
                String[] cat = ma.split(" ");
                try {
                    int i = Integer.parseInt(cat[0].trim());
                    ma = String.format("2%05d", i);
                    drink = tim(ma);
                } catch (Exception e){
                    flag = -1;
                }
                if (drink.maMon != null || drink.maMon == "") flag = 0;
                else flag = -1;
            }
            while (flag == -1);
            //  cap nhat 1 thuoc tinh
            if (chon == 1){
                System.out.println("1.Ten Mon | 2.Gia Mon | 3.Hang san xuat");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 4) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                do{
                    flag = -1;
                    do{
                        System.out.print("Nhap noi dung sua: ");
                        String s = scanner.nextLine();
                        try{
                            drink.capNhat(s.trim(), chon);
                            flag = 0;
                        } catch (Exception e){
                            System.out.println("- Nhap sai thong tin -");
                            flag = -1;
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat mon " + drink.tenMon + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
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
                            System.out.println("- Cap nhat thuc uong thanh cong -");
                        } catch (Exception e){
                            System.out.println("- Cap nhat thuc uong khong thanh cong -");
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
                    flag = -1;
                    do{
                        System.out.print("Nhap noi dung sua (vd: PEPSI, 25000, PEPSICO): ");
                        String s = scanner.nextLine().toUpperCase();
                        String[] cat = s.split(",");
                        if (cat.length != 3) flag = -1;
                        else{
                            try{
                                drink.capNhat(s.trim());
                                flag = 0;
                            } catch (Exception e){
                                System.out.println("- Nhap sai thong tin -");
                                flag = -1;
                            }
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat mon " + drink.tenMon + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
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
                            System.out.println("- Cap nhat thuc uong thanh cong -");
                        } catch (IOException ex) {
                            System.out.println("Cap nhat thuc uong khong thanh cong");
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
                flag = -1;
                do{
                    flag = -1;
                    System.out.print("Chon mon (vd:1): ");
                    String ma = scanner.nextLine().toUpperCase();
                    String[] cat = ma.split(" ");
                    try {
                        int i = Integer.parseInt(cat[0].trim());
                        ma = String.format("2%05d", i);
                        drink = tim(ma);
                    } catch (Exception e){
                        flag = -1;
                    }
                    if (drink.maMon != null) flag = 0;
                    else flag = -1;
                }
                while (flag == -1);
                System.out.println("Xoa mon " + drink.tenMon + "?\t1.Xoa | 2.Khong va nhap lai | 3.Thoat");
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
                        drink.xoa();
                        System.out.println("- Xoa sanh cuoi thanh cong -");
                    } catch (Exception ex) {
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
                flag = -1;
                System.out.println("1.Ten Mon | 2.Gia Mon | 3.Hang San Xuat");
                do {
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 4) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                String nd;
                if (chon == 1)
                    nd = "ten mon can tim";
                else if (chon == 2)
                    nd = "gia mon toi thieu";
                else
                    nd = "hang san xuat can tim";
                do{
                    flag = -1;
                    System.out.print("Nhap " + nd + ": ");
                    String s = scanner.nextLine().toUpperCase();
                    try{
                        List <ThucUong> tu = traCuu(s, chon);
                        if (tu.size() != 0){
                            hienThi(tu, this);
                            flag = 0;
                        }
                        else{
                            System.out.println("- Khong tim thay thuc uong nao phu hop -");
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
