/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.BlobEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.journal.Notes;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.AlchemyScene;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Alchemy extends Blob implements AlchemyScene.AlchemyProvider {

	protected int pos;
	
	@Override
	protected void evolve() {
		int cell;
		for (int i=area.top-1; i <= area.bottom; i++) {
			for (int j = area.left-1; j <= area.right; j++) {
				cell = j + i* Dungeon.level.width();
				if (Dungeon.level.insideMap(cell)) {
					off[cell] = cur[cell];
					volume += off[cell];
					if (off[cell] > 0 && Dungeon.level.heroFOV[cell]){
						Notes.add( Notes.Landmark.ALCHEMY );
					}
					
					//for pre-0.6.2 saves
					while (off[cell] > 0 && Dungeon.level.heaps.get(cell) != null){
						
						int n;
						do {
							n = cell + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
						} while (!Dungeon.level.passable[n]);
						Dungeon.level.drop( Dungeon.level.heaps.get(cell).pickUp(), n ).sprite.drop( pos );
					}
				}
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( Speck.factory( Speck.BUBBLE ), 0.33f, 0 );
	}
	
	public static int alchPos;
	
	//1 volume is kept in reserve
	
	@Override
	public int getEnergy() {
		return Math.max(0, cur[alchPos] - 1);
	}
	
	@Override
	public void spendEnergy(int reduction) {
		cur[alchPos] = Math.max(1, cur[alchPos] - reduction);
	}
}
