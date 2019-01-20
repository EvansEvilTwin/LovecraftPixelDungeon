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
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;

public class Blizzard extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		int cell;
		
		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		Freezing freeze = (Freezing)Dungeon.level.blobs.get( Freezing.class );
		
		Inferno inf = (Inferno)Dungeon.level.blobs.get( Inferno.class );
		
		for (int i = area.left; i < area.right; i++) {
			for (int j = area.top; j < area.bottom; j++) {
				cell = i + j * Dungeon.level.width();
				if (cur[cell] > 0) {
					
					if (fire != null)   fire.clear(cell);
					if (freeze != null) freeze.clear(cell);
					
					if (inf != null && inf.volume > 0 && inf.cur[cell] > 0){
						inf.clear(cell);
						off[cell] = cur[cell] = 0;
						continue;
					}
					
					Freezing.freeze(cell);
					Freezing.freeze(cell);
					
				}
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour( Speck.factory( Speck.BLIZZARD, true ), 0.4f );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
	
	
}
