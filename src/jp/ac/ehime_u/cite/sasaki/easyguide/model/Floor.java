package jp.ac.ehime_u.cite.sasaki.easyguide.model;

import java.io.File;
import android.content.res.Resources.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * @author Takashi SASAKI {@link "http://twitter.com/TakashiSasaki"}
 * 
 */
@SuppressWarnings("serial")
public class Floor extends ArrayList<Room> {
	private static final String floorImageName = "floor.png";
	private DirectoryName floorDirectoryName;
	private DirectoryImage floorImage;
	private File floorDirectory;

	/**
	 * @param floor_directory
	 */
	public Floor(File floor_directory) {
		super();
		this.floorDirectory = floor_directory;
		this.floorDirectoryName = new DirectoryName(floor_directory.getName());
		this.floorImage = new DirectoryImage(this.floorDirectory);
		Log.v(this.getClass().getSimpleName(), "Scanning room directories in "
				+ this.floorDirectory);
		this.EnumerateRooms();
	}// a constructor

	@SuppressWarnings("javadoc")
	public void EnumerateRooms() {
		for (File room_directory : this.floorDirectory.listFiles()) {
			if (!room_directory.isDirectory())
				continue;
			Log.v(this.getClass().getSimpleName(), "Room directory "
					+ room_directory.getAbsolutePath() + " was found.");
			this.add(new Room(room_directory));
		}// for
		this.SortByRoomNumber();
	}// EnumerateRooms

	@SuppressWarnings("javadoc")
	public void SortByRoomNumber() {
		Collections.sort(this, new Comparator<Room>() {
			@Override
			public int compare(Room arg0, Room arg1) {
				return arg0.getRoomIndex() - arg1.getRoomIndex();
			}// compare
		});// sort
	}// SortByRoomNumber

	@SuppressWarnings("javadoc")
	public Bitmap getFloorImage() {
		return this.floorImage.getImage();
	}

	@SuppressWarnings("javadoc")
	public Bitmap getFloorThumbnail() {
		return this.floorImage.getThumbnail();
	}

	@SuppressWarnings("javadoc")
	public int getFloorIndex() {
		return this.floorDirectoryName.getNumber();
	}

	@SuppressWarnings("javadoc")
	public String toString() {
		if (this.isEmpty())
			return "";
		return this.floorDirectoryName.getName();
	}

	@SuppressWarnings("javadoc")
	public int getFloorX() {
		return this.floorDirectoryName.getX();
	}

	@SuppressWarnings("javadoc")
	public int getFloorY() {
		return this.floorDirectoryName.getY();
	}

	@SuppressWarnings("javadoc")
	public File getFloorDirectory() {
		return this.floorDirectory;
	}

	@SuppressWarnings("javadoc")
	public DirectoryName getFloorDirectoryName() {
		return this.floorDirectoryName;
	}

	@SuppressWarnings("javadoc")
	@Deprecated
	public Room GetRoom(String room_name) {
		for (Room room : this) {
			if (room.toString().equals(room_name)) {
				return room;
			}
		}// for
		throw new NotFoundException("Room " + room_name
				+ " not found in Floor " + this.toString());
	}// GetRoom

	@SuppressWarnings("javadoc")
	@Deprecated
	public Room GetRoom(int room_number) {
		for (Room room : this) {
			if (room.getRoomIndex() == room_number) {
				return room;
			}
		}// for
		throw new NotFoundException("Room " + room_number
				+ " not found in Floor " + this.toString());
	}// GetRoom

	public static Floor getEmptyFloor() {
		return new Floor();
	}

	private Floor() {
		super();
	}// a constructor for empty instance

	public Room getRoomByIndex(int roomIndex) {
		for (Room r : this) {
			if (r.getRoomIndex() == roomIndex) {
				return r;
			}
		}// for
		return null;
	}// getRoomByIndex

	// @Override
	// @Deprecated
	// public Room get(int index) {
	// throw new Error("Floor#get was deprecated");
	// }// get
}// Floor
