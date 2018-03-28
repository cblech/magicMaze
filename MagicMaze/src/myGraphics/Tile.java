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

    private static JSONObject loadedTiles;

    private String tileID;
    
    private Field[][] fields = new Field[4][4];

    public Tile(String tileID) {
        //load Tiles json if not already done
        if (loadedTiles == null) {
            loadTilesJson();
        }

        //set TileID and init
        this.tileID = tileID;
        this.initTile();
        //System.out.println("done");
    }

    private void loadTilesJson() {
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
        System.out.println(jsonFile);
        loadedTiles = new JSONObject(jsonFile);
    }

    private void initTile() {
        JSONObject tile = loadedTiles.getJSONObject("tiles").getJSONObject(tileID);
        
        for (int i = 0; i<fields.length;i++) {
            for (int j = 0; j<fields[i].length;j++) {
                String type = tile.getJSONArray("fields").getJSONArray(i).getString(j);
                fields[i][j]=new Field(type, true, true, false, false);
            }
        }
    }

    class Field {
        private String type;
        private boolean isWall;
        private boolean wallUp;
        private boolean wallRight;
        private boolean wallDown;
        private boolean wallLeft;

        public Field(String type, boolean up,boolean right,boolean down,boolean left) {
            this.type = type;
            this.wallUp = up;
            this.wallRight = right;
            this.wallDown = down;
            this.wallLeft = left;
        }

        public boolean isWall() {
            return isWall;
        }
        
        public boolean isWallUp() {
            return wallUp;
        }

        public boolean isWallRight() {
            return wallRight;
        }

        public boolean isWallDown() {
            return wallDown;
        }

        public boolean isWallLeft() {
            return wallLeft;
        }

    }
}
