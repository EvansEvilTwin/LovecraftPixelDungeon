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

package com.overgrownpixel.overgrownpixeldungeon.journal;

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;

public class Journal {
	
	public static final String JOURNAL_FILE = "journal.dat";
	
	private static boolean loaded = false;
	
	public static void loadGlobal(){
		if (loaded){
			return;
		}
		
		Bundle bundle;
		try {
			bundle = FileUtils.bundleFromFile( JOURNAL_FILE );
			
		} catch (IOException e){
			bundle = new Bundle();
		}
		
		Catalog.restore( bundle );
		Document.restore( bundle );
		
		loaded = true;
	}
	
	//package-private
	static boolean saveNeeded = false;
	
	public static void saveGlobal(){
		if (!saveNeeded){
			return;
		}
		
		Bundle bundle = new Bundle();
		
		Catalog.store(bundle);
		Document.store(bundle);
		
		try {
			FileUtils.bundleToFile( JOURNAL_FILE, bundle );
			saveNeeded = false;
		} catch (IOException e) {
			OvergrownPixelDungeon.reportException(e);
		}
		
	}

}
