/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGraphics;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
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

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                String type = tile.getJSONArray("fields").getJSONArray(i).getString(j);
                String wall = tile.getJSONArray("walls").getJSONArray(i).getString(j);

                boolean o = true, r = true, u = true, l = true;

                if (wall.equals("0")) {
                    o = false;
                    r = false;
                }
                if (wall.equals("1")) {
                    r = false;
                }
                if (wall.equals("2")) {
                    o = false;
                }

                if (i < 3) {
                    if (tile.getJSONArray("walls").getJSONArray(i + 1).getString(j).equals("0")
                            || tile.getJSONArray("walls").getJSONArray(i + 1).getString(j).equals("2")) {
                        u = false;
                    }
                }
                if (j > 0) {
                    if (tile.getJSONArray("walls").getJSONArray(i).getString(j - 1) .equals("0")
                            || tile.getJSONArray("walls").getJSONArray(i).getString(j - 1).equals("1")) {
                        l = false;
                    }
                }

                fields[i][j] = new Field(type, o, r, u, l);
            }
        }
    }

    public void draw(NormalGraphics ng) {
        ng.setColor(Color.BLACK);
        ng.fillRect(.02f, .02f, .08f, .96f);
        ng.fillRect(.90f, .02f, .08f, .96f);
        ng.fillRect(.10f, .02f, .80f, .08f);
        ng.fillRect(.10f, .90f, .80f, .08f);

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j].isWall()) {
                    ng.fillRect(j * .2f + .12f, i * .2f + .12f, .16f, .16f);
                } else {
                    if (fields[i][j].isWallLeft()) {
                        ng.setColor(Color.red);
                    } else {
                        ng.setColor(Color.green);
                    }
                    ng.fillCirc(j * .2f + .15f, i * .2f + .2f, .02f);

                    if (fields[i][j].isWallRight()) {
                        ng.setColor(Color.red);
                    } else {
                        ng.setColor(Color.green);
                    }
                    ng.fillCirc(j * .2f + .25f, i * .2f + .2f, .02f);

                    if (fields[i][j].isWallUp()) {
                        ng.setColor(Color.red);
                    } else {
                        ng.setColor(Color.green);
                    }
                    ng.fillCirc(j * .2f + .2f, i * .2f + .15f, .02f);

                    if (fields[i][j].isWallDown()) {
                        ng.setColor(Color.red);
                    } else {
                        ng.setColor(Color.green);
                    }
                    ng.fillCirc(j * .2f + .2f, i * .2f + .25f, .02f);

                    ng.setColor(Color.black);

                }

            }
        }

        ng.setColor(Color.red);
        ng.drawLine(1, 0, 1, 1);
    }

    class Field {

        private String type;
        private boolean isWall;
        private boolean wallUp;
        private boolean wallRight;
        private boolean wallDown;
        private boolean wallLeft;

        public Field(String type, boolean up, boolean right, boolean down, boolean left) {
            this.type = type;
            this.wallUp = up;
            this.wallRight = right;
            this.wallDown = down;
            this.wallLeft = left;
            this.isWall = type == "w";
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
