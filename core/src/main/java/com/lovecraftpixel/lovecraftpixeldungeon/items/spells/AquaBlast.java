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

package com.lovecraftpixel.lovecraftpixeldungeon.items.spells;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Paralysis;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Splash;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class AquaBlast extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.AQUA_BLAST;
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		int cell = bolt.collisionPos;
		
		Splash.at(cell, 0x00AAFF, 10);
		
		for (int i : PathFinder.NEIGHBOURS9){
			if (i == 0 || Random.Int(5) != 0){
				int terr = Dungeon.level.map[cell + i];
				if (terr == Terrain.EMPTY || terr == Terrain.GRASS ||
						terr == Terrain.EMBERS || terr == Terrain.EMPTY_SP ||
						terr == Terrain.HIGH_GRASS || terr == Terrain.FURROWED_GRASS ||
						terr == Terrain.EMPTY_DECO) {
					Level.set(cell + i, Terrain.WATER);
					GameScene.updateMap(cell + i);
				}
			}
		}
		
		Char target = Actor.findChar(cell);
		
		if (target != null && target != hero){
			//just enough to skip their current turn
			Buff.affect(target, Paralysis.class, 0f);
		}
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 60) / 12f));
	}
	
	public static class Recipe extends com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfIdentify.class, PotionOfStormClouds.class};
			inQuantity = new int[]{1, 1};
			
			cost = 4;
			
			output = AquaBlast.class;
			outQuantity = 12;
		}
		
	}
}
