/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
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

package com.overgrownpixel.overgrownpixeldungeon.levels.traps;

import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Ooze;
import com.overgrownpixel.overgrownpixeldungeon.effects.Splash;

public class OozeTrap extends Trap {

	{
		color = GREEN;
		shape = DOTS;
	}

	@Override
	public void activate() {
		Char ch = Actor.findChar( pos );

		if (ch != null && !ch.flying){
			Buff.affect(ch, Ooze.class).set( 20f );
			Splash.at( pos, 0x000000, 5);
		}
	}
}
