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
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class HeSoGiaThue {
    private String thoiDiem;
    private double heSoThoiDiem;
    private int buoi;
    
    private final String url = "D:\\Data\\HeSoGiaThue.txt";
    File file = new File(url);
    
    public HeSoGiaThue(int buoi) throws FileNotFoundException, IOException{
        
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                if (scanner.nextInt() == buoi){
                    this.buoi = buoi;
                    this.thoiDiem = scanner.next();
                    this.heSoThoiDiem = Double.parseDouble(scanner.next());
                    break;
                }
                else {
                    scanner.nextLine();                         
                }
            }
           
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * @return the thoiDiem
     */
    public String getThoiDiem() {
        return thoiDiem;
    }

    /**
     * @return the heSoThoiDiem
     */
    public double getHeSoThoiDiem() {
        return heSoThoiDiem;
    }

    /**
     * @param heSoThoiDiem the heSoThoiDiem to set
     * @throws java.io.FileNotFoundException
     */
    public void setHeSoThoiDiem(double heSoThoiDiem) throws FileNotFoundException, IOException {
        String[][] line = new String[3][3];
        try (Scanner scanner = new Scanner(file)){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    line[i][j] = scanner.next();
                }
                if (i == this.buoi){                  
                    line[i][2] = Double.toString(heSoThoiDiem);
                }
            }
               
        } catch (Exception ex){
            System.out.print(ex);
        }
        
        file.delete();
        File newFile = new File(url);
        try (FileWriter fileWriter = new FileWriter(newFile, true)){
            try (PrintWriter writer = new PrintWriter(fileWriter)){
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        writer.printf("%s\s",line[i][j]);
                    }
                    writer.println();
                }             
            } catch (Exception ex){
                System.out.print(ex);
            }
        } catch (Exception ex){
            System.out.print(ex);
        }
    }


    /**
     * @return the buoi
     */
    public int getBuoi() {
        return buoi;
    }
    
}
