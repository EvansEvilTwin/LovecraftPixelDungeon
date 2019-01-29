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

package com.lovecraftpixel.lovecraftpixeldungeon.items.rings;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;

import java.text.DecimalFormat;

public class RingOfPoison extends Ring {
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats", new DecimalFormat("#.##").format(100f * (Math.pow(1.5f, soloBonus()) - 1f)));
		} else {
			return Messages.get(this, "typical_stats", new DecimalFormat("#.##").format(30f));
		}
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Poison();
	}
	
	public static float poisonMultiplier( Char target ){
		return (float)Math.pow(1.5f, getBonus(target, Poison.class));
	}
	
	public class Poison extends RingBuff {
	}
}