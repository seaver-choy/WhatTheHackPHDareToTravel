package com.wth.model;

public interface PhotoDAO {
	public Photo getPhoto(int loc_id, String photoDirectory);
	public void addPhoto(int loc_id, Photo pic);
	public void deletePhoto(int loc_id, Photo pic);
	public Photo[] getAllPhotos(int loc_id);
}