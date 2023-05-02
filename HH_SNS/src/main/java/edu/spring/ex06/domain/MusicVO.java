package edu.spring.ex06.domain;

public class MusicVO {
	private int musicId;
	private String musicTitle;
	private String musicArtist;
	private String musicLocation;
	private String musicArt;
	
	public MusicVO() {}

	public MusicVO(int musicId, String musicTitle, String musicArtist, String musicLocation, String musicArt) {
		super();
		this.musicId = musicId;
		this.musicTitle = musicTitle;
		this.musicArtist = musicArtist;
		this.musicLocation = musicLocation;
		this.musicArt = musicArt;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}

	public String getMusicArtist() {
		return musicArtist;
	}

	public void setMusicArtist(String musicArtist) {
		this.musicArtist = musicArtist;
	}

	public String getMusicLocation() {
		return musicLocation;
	}

	public void setMusicLocation(String musicLocation) {
		this.musicLocation = musicLocation;
	}

	public String getMusicArt() {
		return musicArt;
	}

	public void setMusicArt(String musicArt) {
		this.musicArt = musicArt;
	}

	@Override
	public String toString() {
		return "MusicVO [musicId=" + musicId + ", musicTitle=" + musicTitle + ", musicArtist=" + musicArtist
				+ ", musicLocation=" + musicLocation + ", musicArt=" + musicArt + "]";
	}
}
