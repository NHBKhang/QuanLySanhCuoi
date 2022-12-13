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
public class ThucAn extends Menu {

    private boolean anChay = true;
    private final String url = "D:\\Data\\ThucAn.txt";
    private File file = new File(url);
    
    private String[] header = new String[] {"Ma Mon","Ten Mon","Gia Mon","An Chay"};

    public ThucAn(String maMon, String tenMon, int giaMon, boolean anChay) {
        super(maMon, tenMon, giaMon);
        this.anChay = anChay;
    }
    
    public ThucAn(String tenMon, int giaMon, boolean anChay) throws FileNotFoundException {
        this.maMon = nextMaThucAn(docMaThucAn());
        this.tenMon = tenMon; 
        this.giaMon = giaMon;
        this.anChay = anChay;
    }
    
    public ThucAn() {}

    private static List<ThucAn> monAn = new ArrayList<>();

    public void init() {
        try ( Scanner c = new Scanner(file)) {
            c.useDelimiter(",");
            while (c.hasNext()) {
                String maMon = c.next();
                String tenMon = c.next();
                int giaMon = c.nextInt();
                boolean anChay = c.nextBoolean();
                ThucAn n = new ThucAn(maMon, tenMon, giaMon, anChay);
                monAn.add(n);
                c.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Them phan tu//
    public void them() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.getMaMon() + ",");
                writer.print(this.getTenMon() + ",");
                writer.print(Integer.toString(this.getGiaMon()) + ",");
                writer.print(Boolean.toString(this.anChay) + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        getMonAn().add(this);
    }

    //Phuong Thuc Ho Tro//
    public void capNhat(String noiDung) throws IOException {
        String[] a = noiDung.split(",");
        for (int i = 0; i < a.length; i++) {
            this.capNhat(a[i].trim(), i+1);
        }
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setTenMon(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setGiaMon(Integer.parseInt(noiDung));
        } else {
            try{
                this.setAnChay(Boolean.parseBoolean(noiDung.toLowerCase()));
            } catch (Exception e){
                if (Integer.parseInt(noiDung) == 1)
                    this.setAnChay(true);
                else
                    this.setAnChay(false);
            }
        }
    }

    //  phuong thuc ho tro cap nhat va xoa
    public ThucAn tim(String maMon) {
        ThucAn food = new ThucAn();
        for (ThucAn s : getMonAn()) {
            if (s.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                food = s;
                break;
            }
        }
        return food;
    }

    public void xoa() throws IOException {
        monAn.remove(this);
        rewriteFile();
    }

    public List<ThucAn> traCuu(String noiDung, int i) throws FileNotFoundException {
        List<ThucAn> ta = new ArrayList<>();
        for (ThucAn food : getMonAn()) {
            if (i == 1) {
                if (food.tenMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    ta.add(food);
                }
            } else if (i == 2) {
                if (food.giaMon >= Integer.parseInt(noiDung)) {
                    ta.add(food);
                }
            } else {
                boolean ac;
                try{
                    ac = Boolean.parseBoolean(noiDung.toLowerCase());
                } catch (Exception e){
                    if (Integer.parseInt(noiDung) == 1)
                        ac = true;
                    else
                        ac = false;
                }
                if(food.anChay == ac){
                    ta.add(food);
                }
            }
        }
        return ta;
    }

    //Tao ma moi//
    private String nextMaThucAn(String maMon) {
        if (maMon == null) {
            maMon = "100001";
        } else {
            int nextMa = (Integer.parseInt(maMon) + 1);
            maMon = Integer.toString(nextMa);
        }
        return maMon;
    }

    private String docMaThucAn() throws FileNotFoundException {
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

    //Viet lai//
    public void rewriteFile() throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file, false)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                for (ThucAn s : getMonAn()) {
                    writer.print(s.getMaMon() + ",");
                    writer.print(s.getTenMon() + ",");
                    writer.print(Integer.toString(s.getGiaMon()) + ",");
                    writer.print(Boolean.toString(s.anChay) + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    
    public static void hienThi(List<ThucAn> thucAn, ThucAn ta){
            System.out.format(ta.getForm(), ta.getHeader());
            for (Object[] row : toTable(thucAn, thucAn.size())){
                System.out.format(ta.getForm(), row);
            }
        }
        
        public static void hienThi(ThucAn thucAn){
            System.out.format(thucAn.getForm(), thucAn.getHeader());
            Object[] row = new String[] {thucAn.maMon,
                                         thucAn.tenMon,
                                         String.valueOf(thucAn.giaMon),
                                         (thucAn.anChay == true? "Co": "Khong")};
            System.out.format(thucAn.getForm(), row);
        }
        
        public static void hienThiFile(ThucAn ta){
                System.out.format(ta.getForm(), ta.getHeader());
                for (Object[] row : toTable(getMonAn(), getMonAn().size())){
                    System.out.format(ta.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<ThucAn> an, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {an.get(i).maMon,
                                         an.get(i).tenMon,
                                         String.valueOf(an.get(i).giaMon),
                                         (an.get(i).anChay == true? "Co": "Khong")};
            }
            return table;
        }

    /**
     * @return the anChay
     */
    public boolean isAnChay() {
        return anChay;
    }

    /**
     * @param anChay the anChay to set
     */
    public void setAnChay(boolean anChay) {
        this.anChay = anChay;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the monAn
     */
    public static List<ThucAn> getMonAn() {
        return monAn;
    }

    /**
     * @param aMonAn the monAn to set
     */
    public static void setMonAn(List<ThucAn> aMonAn) {
        monAn = aMonAn;
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

    List<ThucAn> Nhap() {
        Scanner scanner = new Scanner(System.in);
        int flag = -1, chon;
        String mon;
        List<ThucAn> thucAn = new ArrayList<>();
        ThucAn ta = new ThucAn();
        hienThiFile(ta);
        System.out.println("(Chon toi thieu 5 mon)");
        do{
            do {
                System.out.print("Chon mon (vd: 1,2): ");
                mon = scanner.nextLine();
                String[] cacMon = mon.split(",");
                for (int i = 0; i < cacMon.length; i++){
                    for (int j = i + 1; j < cacMon.length; j++){
                        if (cacMon[i].equals(cacMon[j])) break;
                        try {
                            int s = Integer.parseInt(cacMon[i].trim());
                            if (s <= getMonAn().size())
                                cacMon[i] = String.format("1%05d", s);
                            flag = 0;
                        } catch (Exception e) {
                            flag = -1;
                        }
                    }
                }
                if (flag == 0){
                    for (ThucAn t: getMonAn()){
                        for (String a: cacMon){
                            if (t.getMaMon().equals(a)){
                                thucAn.add(t);
                                flag = 0;
                            }
                        }
                    }
                }
            }
            while (flag == -1 || thucAn.size() < 5);
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
        return thucAn;
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
                ThucAn food = new ThucAn();
                System.out.print("Nhap ten mon: ");
                String tenMon = scanner.nextLine().toUpperCase(), a;         
                String[] cat;
                int giaMon = 0;
                do{
                    flag = -1;
                    System.out.print("Nhap gia mon: ");
                    a = scanner.nextLine();            
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
                System.out.print("An Chay?\t1.Yes | 2.No\nChon: ");
                a = scanner.nextLine();
                cat = a.split(" ");
                boolean anChay = false;
                do{
                    flag = -1;
                    try {
                        int i = Integer.parseInt(cat[0].trim());
                        if (i == 1) {
                            anChay = true;
                            flag = 0;
                        }
                        else if(i == 2){
                            anChay = false;
                            flag = 0;
                        }
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                try {
                    ThucAn ta = new ThucAn(tenMon, giaMon, anChay);
                    food = ta;
                    System.out.println("- Tao thuc an moi thanh cong -");
                } catch (FileNotFoundException ex) {
                    System.out.println("- Tao thuc an moi khong thanh cong -");
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
                        food.them();
                        System.out.println("- Luu thuc an thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Luu thuc an khong thanh cong -");
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
            ThucAn food = new ThucAn();
            do{
                flag = -1;
                System.out.print("Chon mon (vd:1): ");
                ma = scanner.nextLine();
                String[] cat = ma.split(" ");
                try {
                    int i = Integer.parseInt(cat[0].trim());
                    ma = String.format("1%05d", i);
                    food = tim(ma);
                } catch (Exception e){
                    flag = -1;
                }
                if (food.maMon != null || food.maMon == "") flag = 0;
                else flag = -1;
            }
            while (flag == -1);
            //  cap nhat 1 thuoc tinh
            if (chon == 1){
                System.out.println("1.Ten Mon | 2.Gia Mon | 3.An Chay");
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
                String nd = "";
                if (chon == 3) nd = " (1.Khong an chay | 2.Co an chay)";
                do{
                    flag = -1;
                    do{
                        System.out.print("Nhap noi dung sua" + nd + ": ");
                        String s = scanner.nextLine();
                        try{
                            food.capNhat(s.trim(), chon);
                            flag = 0;
                        } catch (Exception e){
                            System.out.println("- Nhap sai thong tin -");
                            flag = -1;
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat mon " + food.tenMon + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
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
                            System.out.println("- Cap nhat thuc an thanh cong -");
                        } catch (Exception e){
                            System.out.println("- Cap nhat thuc an khong thanh cong -");
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
                        System.out.print("Nhap noi dung sua (vd: BO XAO, 400000, FALSE): ");
                        String s = scanner.nextLine().toUpperCase();
                        String[] cat = s.split(",");
                        if (cat.length != 3) flag = -1;
                        else{
                            try{
                                food.capNhat(s.trim());
                                flag = 0;
                            } catch (Exception e){
                                System.out.println("- Nhap sai thong tin -");
                                flag = -1;
                            }
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat mon " + food.tenMon + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
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
                            System.out.println("- Cap nhat thuc an thanh cong -");
                        } catch (IOException ex) {
                            System.out.println("Cap nhat thuc an khong thanh cong");
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
                ThucAn food = new ThucAn();
                do{
                    flag = -1;
                    System.out.print("Chon mon (vd:1): ");
                    String ma = scanner.nextLine().toUpperCase();
                    String[] cat = ma.split(" ");
                    try {
                        int i = Integer.parseInt(cat[0]);
                        ma = String.format("1%05d", i);
                        food = tim(ma);
                    } catch (Exception e){
                        flag = -1;
                    }
                    if (food.maMon != null) flag = 0;
                    else flag = -1;
                }
                while (flag == -1);
                System.out.println("Xoa mon " + food.tenMon + "?\t1.Xoa | 2.Khong va nhap lai | 3.Thoat");
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
                        food.xoa();
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
                flag = -1;
                System.out.println("1.Ten Mon | 2.Gia Mon | 3.An chay");
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
                    nd = "so (1.Khong an chay | 2.Co an chay)";
                do{
                    flag = -1;
                    System.out.print("Nhap " + nd + ": ");
                    String s = scanner.nextLine();
                    try{
                        List <ThucAn> ta = traCuu(s, chon);
                        if (ta.size() != 0){
                            hienThi(ta, this);
                            flag = 0;
                        }
                        else{
                            System.out.println("- Khong tim thay mon an nao phu hop -");
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
