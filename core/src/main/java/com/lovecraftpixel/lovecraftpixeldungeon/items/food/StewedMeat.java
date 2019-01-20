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

package com.lovecraftpixel.lovecraftpixeldungeon.items.food;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Hunger;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;

public class StewedMeat extends Food {
	
	{
		image = ItemSpriteSheet.STEWED;
		energy = Hunger.HUNGRY/2f;
	}
	
	@Override
	public int price() {
		return 8 * quantity;
	}
	
	public static class oneMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{1};
			
			cost = 2;
			
			output = StewedMeat.class;
			outQuantity = 1;
		}
	}
	
	public static class twoMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{2};
			
			cost = 3;
			
			output = StewedMeat.class;
			outQuantity = 2;
		}
	}
	
	//red meat
	//blue meat
	
	public static class threeMeat extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{MysteryMeat.class};
			inQuantity = new int[]{3};
			
			cost = 4;
			
			output = StewedMeat.class;
			outQuantity = 3;
		}
	}

}
