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

package com.lovecraftpixel.lovecraftpixeldungeon.items.stones;

import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RedButton;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RenderedTextMultiline;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.IconTitle;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndBag;

public class StoneOfAugmentation extends InventoryStone {
	
	{
		mode = WndBag.Mode.ENCHANTABLE;
		image = ItemSpriteSheet.STONE_AUGMENTATION;
	}
	
	@Override
	protected void onItemSelected(Item item) {
		
		GameScene.show(new WndAugment( item));
		
	}
	
	public void apply( Weapon weapon, Weapon.Augment augment ) {
		
		weapon.augment = augment;
		useAnimation();
		ScrollOfUpgrade.upgrade(curUser);
		
	}
	
	public void apply( Armor armor, Armor.Augment augment ) {
		
		armor.augment = augment;
		useAnimation();
		ScrollOfUpgrade.upgrade(curUser);
	}
	
	@Override
	public int price() {
		return 30 * quantity;
	}
	
	public class WndAugment extends Window {
		
		private static final int WIDTH			= 120;
		private static final int MARGIN 		= 2;
		private static final int BUTTON_WIDTH	= WIDTH - MARGIN * 2;
		private static final int BUTTON_HEIGHT	= 20;
		
		public WndAugment( final Item toAugment ) {
			super();
			
			IconTitle titlebar = new IconTitle( toAugment );
			titlebar.setRect( 0, 0, WIDTH, 0 );
			add( titlebar );
			
			RenderedTextMultiline tfMesage = PixelScene.renderMultiline( Messages.get(this, "choice"), 8 );
			tfMesage.maxWidth(WIDTH - MARGIN * 2);
			tfMesage.setPos(MARGIN, titlebar.bottom() + MARGIN);
			add( tfMesage );
			
			float pos = tfMesage.top() + tfMesage.height();
			
			if (toAugment instanceof Weapon){
				for (final Weapon.Augment aug : Weapon.Augment.values()){
					if (((Weapon) toAugment).augment != aug){
						RedButton btnSpeed = new RedButton( Messages.get(this, aug.name()) ) {
							@Override
							protected void onClick() {
								hide();
								StoneOfAugmentation.this.apply( (Weapon)toAugment, aug );
							}
						};
						btnSpeed.setRect( MARGIN, pos + MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT );
						add( btnSpeed );
						
						pos = btnSpeed.bottom();
					}
				}
				
			} else if (toAugment instanceof Armor){
				for (final Armor.Augment aug : Armor.Augment.values()){
					if (((Armor) toAugment).augment != aug){
						RedButton btnSpeed = new RedButton( Messages.get(this, aug.name()) ) {
							@Override
							protected void onClick() {
								hide();
								StoneOfAugmentation.this.apply( (Armor) toAugment, aug );
							}
						};
						btnSpeed.setRect( MARGIN, pos + MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT );
						add( btnSpeed );
						
						pos = btnSpeed.bottom();
					}
				}
			}
			
			RedButton btnCancel = new RedButton( Messages.get(this, "cancel") ) {
				@Override
				protected void onClick() {
					hide();
					StoneOfAugmentation.this.collect();
				}
			};
			btnCancel.setRect( MARGIN, pos + MARGIN, BUTTON_WIDTH, BUTTON_HEIGHT );
			add( btnCancel );
			
			resize( WIDTH, (int)btnCancel.bottom() + MARGIN );
		}
		
		@Override
		public void onBackPressed() {
			StoneOfAugmentation.this.collect();
			super.onBackPressed();
		}
	}
}
