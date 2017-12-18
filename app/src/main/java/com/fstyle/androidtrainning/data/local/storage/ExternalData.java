package com.fstyle.androidtrainning.data.local.storage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Album;
import com.fstyle.androidtrainning.model.Artist;
import com.fstyle.androidtrainning.model.Track;
import java.util.ArrayList;

/**
 * Created by Administrator on 12/16/17.
 */

public class ExternalData {

    private static final String TAG = "External";
    private ArrayList<Track> arrayListTrack = new ArrayList<>();
    private ArrayList<Album> arrayListAlbum = new ArrayList<>();
    private ArrayList<Artist> arrayListArtist = new ArrayList<>();
    private ArrayList<String> audioFilePathList = new ArrayList<>();
    private Uri[] uri;
    private int countTrack;
    private int countAlbum;
    private int countArtist;
    private static final String NO_TRACK = "No Track To Add";
    private static final String SORT_ASC = " ASC";
    private static final String LIKE = " LIKE ?";

    public ArrayList<Track> getArrayListTrack() {
        return arrayListTrack;
    }

    public void setArrayListTrack(ArrayList<Track> arrayListTrack) {
        this.arrayListTrack = arrayListTrack;
    }

    public ArrayList<Album> getArrayListAlbum() {
        return arrayListAlbum;
    }

    public void setArrayListAlbum(ArrayList<Album> arrayListAlbum) {
        this.arrayListAlbum = arrayListAlbum;
    }

    public ArrayList<Artist> getArrayListArtist() {
        return arrayListArtist;
    }

    public void setArrayListArtist(ArrayList<Artist> arrayListArtist) {
        this.arrayListArtist = arrayListArtist;
    }

    public ArrayList<String> getAudioFilePathList() {
        return audioFilePathList;
    }

    public void setAudioFilePathList(ArrayList<String> audioFilePathList) {
        this.audioFilePathList = audioFilePathList;
    }

    public Uri[] getUri() {
        return uri;
    }

    public void setUri(Uri[] uri) {
        this.uri = uri;
    }

    public int getCountTrack() {
        return countTrack;
    }

    public void setCountTrack(int countTrack) {
        this.countTrack = countTrack;
    }

    public int getCountAlbum() {
        return countAlbum;
    }

    public void setCountAlbum(int countAlbum) {
        this.countAlbum = countAlbum;
    }

    public int getCountArtist() {
        return countArtist;
    }

    public void setCountArtist(int countArtist) {
        this.countArtist = countArtist;
    }

    public void scanAllMusic(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA
        };
        String sortOrder = MediaStore.Audio.Media.TITLE + SORT_ASC;
        Cursor cursor =
                contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
                        MediaStore.Audio.Media.IS_MUSIC + "=1", null, sortOrder);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int position = 0;
            countTrack = cursor.getCount();
            do {
                Track track = new Track();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String subArtist = cursor.getString(artist);
                String trackData =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                track.setName(cursor.getString(title));
                track.setNameArtist(subArtist);
                track.setPosition(position);
                track.setTrackData(trackData);
                arrayListTrack.add(track);
                audioFilePathList.add(path);
                position++;
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(context, NO_TRACK, Toast.LENGTH_SHORT).show();
        }
    }

    public void scanAllMusicBelongAlbum(Context context, String temp) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA
        };
        String where = MediaStore.Audio.Media.ALBUM + LIKE;
        String sortOrder = MediaStore.Audio.Media.TITLE + SORT_ASC;
        String[] params = new String[] { temp };
        Cursor cursor =
                contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
                        where, params, sortOrder);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int position = 0;
            do {
                Track track = new Track();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String subArtist = cursor.getString(artist);
                String trackData =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                track.setName(cursor.getString(title));
                track.setNameArtist(subArtist);
                track.setPosition(position);
                track.setTrackData(trackData);
                arrayListTrack.add(track);
                audioFilePathList.add(path);
                position++;
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(context, NO_TRACK, Toast.LENGTH_SHORT).show();
        }
    }

    public void scanAllMusicBelongArtist(Context context, String temp) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA
        };
        String where = MediaStore.Audio.Media.ALBUM + LIKE;
        String sortOrder = MediaStore.Audio.Media.TITLE + SORT_ASC;
        String[] params = new String[] { temp };
        Cursor cursor =
                contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
                        where, params, sortOrder);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int position = 0;
            do {
                Track track = new Track();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String subArtist = cursor.getString(artist);
                String trackData =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                track.setName(cursor.getString(title));
                track.setNameArtist(subArtist);
                track.setPosition(position);
                track.setTrackData(trackData);
                arrayListTrack.add(track);
                audioFilePathList.add(path);
                position++;
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(context, NO_TRACK, Toast.LENGTH_SHORT).show();
        }
    }

    public void scanAllAlbum(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_KEY, MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM_ART, MediaStore.Audio.Albums.NUMBER_OF_SONGS
        };
        String sortOrder = MediaStore.Audio.Albums.ALBUM + SORT_ASC;
        Cursor cursor =
                contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection,
                        null, null, sortOrder);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            int album = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int artist = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
            int art = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            countAlbum = cursor.getCount();
            do {
                Album albums = new Album();
                String coverPath = cursor.getString(art);
                Bitmap imgFile = BitmapFactory.decodeFile(coverPath);
                if (coverPath != null) {
                    Bitmap img = Bitmap.createBitmap(imgFile);
                    String nameArtist = cursor.getString(artist);
                    String nameAlbum = cursor.getString(album);
                    albums.setBmAlbum(img);
                    albums.setName(nameAlbum);
                    albums.setNameArtist(nameArtist);
                } else {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.img_demo_100x100);
                    String nameArtist = cursor.getString(artist);
                    String nameAlbum = cursor.getString(album);
                    albums.setBmAlbum(bitmap);
                    albums.setName(nameAlbum);
                    albums.setNameArtist(nameArtist);
                }
                arrayListAlbum.add(albums);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void scanAllArtist(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Artists.ARTIST, MediaStore.Audio.Artists.ARTIST_KEY
        };
        String sortOrder = MediaStore.Audio.Artists.ARTIST + SORT_ASC;
        Cursor cursor =
                contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, projection,
                        null, null, sortOrder);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            int artist = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
            countArtist = cursor.getCount();
            do {
                Artist at = new Artist();
                String subArtist = cursor.getString(artist);
                at.setName(subArtist);
                arrayListArtist.add(at);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
