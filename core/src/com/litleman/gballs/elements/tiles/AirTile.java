package com.litleman.gballs.elements.tiles;

import com.litleman.gballs.elements.Tile;
import com.litleman.gballs.graphics.Textures;

/**
 * Created by andri on 29.07.2015.
 */
public class AirTile extends Tile {

    public AirTile(int x, int y) {
        super("tile.air", x, y, false, -1.0f, 1.0f, Textures.airTile);
    }


}
