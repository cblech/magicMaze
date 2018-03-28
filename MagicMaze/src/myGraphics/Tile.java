/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGraphics;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import org.json.JSONObject;

/**
 *
 * @author cblech
 */
class Tile {

    static BufferedReader bf;

    static JSONObject loadedTiles;
    String tileID;

    public Tile() {
        String jsonFile = "";
        try {
            Scanner sc = new Scanner(new File("./data/Tiles.json"));
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                jsonFile += str;
            }
            
            //bf = new BufferedReader(new FileReader(new File("./data/Tiles.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        

//        try {
//            System.out.println(bf.readLine());
//            while (true) {
//                jsonFile += bf.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(jsonFile);
        loadedTiles = new JSONObject(jsonFile);
    }

}
