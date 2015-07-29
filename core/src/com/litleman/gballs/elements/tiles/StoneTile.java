package com.litleman.gballs.elements.tiles;

import com.litleman.gballs.elements.Tile;
import com.litleman.gballs.graphics.Textures;

/**
 * Created by andri on 29.07.2015.
 */
public class StoneTile extends Tile {

    public StoneTile(int x, int y) {
        super("tile.stone", x, y, true, 5.0f, 0.8f, Textures.stoneTile);
    }


}
