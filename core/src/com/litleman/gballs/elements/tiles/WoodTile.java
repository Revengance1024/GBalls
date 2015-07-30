package com.litleman.gballs.elements.tiles;

import com.litleman.gballs.elements.Tile;
import com.litleman.gballs.graphics.Textures;

/**
 * Created by andri on 29.07.2015.
 */
public class WoodTile extends Tile{

    public WoodTile(int x, int y) {
        super("tile.wood", x, y, true, 2.0f, 0.5f, Textures.woodTile);
    }


}
