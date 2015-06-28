package com.syteck.worksessions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Border {

	private Location loc1, loc2;
	private World world;

	public Border() {
		
		
		
	}
	public Border(World world, Location loc1, Location loc2) {

		this.loc1 = loc1;
		this.loc2 = loc2;
		
		this.world = world;
	}

	public World getWorld() {
		
		return this.world;
		
	}
	
	public void setLocation(int i, Location loc) {

		if(i == 1) {

			this.loc1 = loc;

		} else {

			this.loc2 = loc;	

		}
	}
	
	public Location getLocation(int i) {
		
		if(i == 1) {

			return this.loc1;

		} else {

			return this.loc2;	

		}
	}
	
	public boolean isFinished() {
		
		return (loc1 != null && loc2 != null);
		
	}

	public boolean contains(Player player) {

		Location loc = player.getLocation();

		return (loc.getX() >= loc1.getX()) && (loc.getX() <= loc2.getX()) && (loc.getY() >= loc1.getY()) && (loc.getY() <= loc2.getY()) && (loc.getZ() <= loc1.getZ()) && (loc.getZ() <= loc2.getZ());
	}
	
	public boolean contains(Location loc) {
		
		return (loc.getX() >= loc1.getX()) && (loc.getX() <= loc2.getX()) && (loc.getY() >= loc1.getY()) && (loc.getY() <= loc2.getY()) && (loc.getZ() <= loc1.getZ()) && (loc.getZ() <= loc2.getZ());
		
	}

	//TODO: ONLY FOR TESTING
	public void build() {
		
		for(int ix = loc1.getBlockX(); ix < loc2.getBlockX(); ix++) {
			
			if(ix > loc1.getBlockX() && ix < loc2.getBlockX()) continue;
			
			for(int iy = loc1.getBlockY(); iy < loc2.getBlockY(); iy++) {
				
				if(iy > loc1.getBlockY() && iy < loc2.getBlockY()) continue;
				
				for(int iz = loc1.getBlockZ(); iz < loc2.getBlockZ(); iz++) {
					
					if(iz > loc1.getBlockZ() && iz < loc2.getBlockZ()) continue;
					
					world.getBlockAt(new Location(world, ix, iy, iz)).setType(Material.GLOWSTONE);
					
				}
			}
		}
	}
	
	
}
