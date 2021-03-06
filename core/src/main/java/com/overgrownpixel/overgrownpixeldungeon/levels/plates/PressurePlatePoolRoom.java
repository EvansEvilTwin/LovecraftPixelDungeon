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

package com.overgrownpixel.overgrownpixeldungeon.levels.plates;

import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Piranha;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.RegularLevel;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.rooms.Room;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Point;

import java.util.ArrayList;

public class PressurePlatePoolRoom extends PressurePlate {

    @Override
    public void deactivate(Level level, int pos) {
        Room thisroom = null;
        if(level instanceof RegularLevel){
            for(Room room : ((RegularLevel) level).rooms()){
                for (Point p : room.getPoints()){
                    if(this.pos == level.pointToCell(p)){
                        thisroom = room;
                    }
                }
            }
            if(thisroom != null){
                ArrayList<Point> points = new ArrayList<>();
                for (int i = thisroom.left; i <= thisroom.right; i++) {
                    for (int j = thisroom.top; j <= thisroom.bottom; j++) {
                        Point p = new Point(i, j);
                        if(level.map[level.pointToCell(p)] == Terrain.EMPTY){
                            points.add(p);
                        }
                    }
                }
                for(Point p : points){
                    level.set(level.pointToCell(p), Terrain.WATER);
                    GameScene.updateMap(level.pointToCell(p));
                }
            }
        }
    }

    @Override
    public void activate(Level level, int pos) {
        Room thisroom = null;
        if(level instanceof RegularLevel){
            for(Room room : ((RegularLevel) level).rooms()){
                for (Point p : room.getPoints()){
                    if(this.pos == level.pointToCell(p)){
                        thisroom = room;
                    }
                }
            }
            if(thisroom != null){
                if(Actor.findChar(pos) == null){
                    ArrayList<Point> points = new ArrayList<>();
                    for (int i = thisroom.left; i <= thisroom.right; i++) {
                        for (int j = thisroom.top; j <= thisroom.bottom; j++) {
                            Point p = new Point(i, j);
                            if(level.map[level.pointToCell(p)] == Terrain.EMPTY_SP || level.map[level.pointToCell(p)] == Terrain.PEDESTAL){
                                points.add(p);
                            }
                        }
                    }
                    for(Point p : points){
                        level.set(level.pointToCell(p), Terrain.WATER);
                        GameScene.updateMap(level.pointToCell(p));
                    }
                    level.plates.remove(pos);
                    GameScene.updateMap(pos);
                    GLog.w(Messages.get(Piranha.class, "fool"));
                } else {
                    ArrayList<Point> points = new ArrayList<>();
                    for (int i = thisroom.left; i <= thisroom.right; i++) {
                        for (int j = thisroom.top; j <= thisroom.bottom; j++) {
                            Point p = new Point(i, j);
                            if(level.map[level.pointToCell(p)] == Terrain.WATER){
                                points.add(p);
                            }
                        }
                    }
                    for(Point p : points){
                        if(Actor.findChar(level.pointToCell(p)) != null){
                            if(Actor.findChar(level.pointToCell(p)) instanceof Piranha){
                                Actor.findChar(level.pointToCell(p)).die(this);
                            }
                        }
                        level.set(level.pointToCell(p), Terrain.EMPTY);
                        GameScene.updateMap(level.pointToCell(p));
                    }
                }
            }
        }
    }
}
