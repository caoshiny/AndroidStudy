package com.shiny.androiddemo.utils;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;

import java.io.FileOutputStream;
import java.io.IOException;

public class Mp4ParserUtil {

    public static void parser(String h264Path, String mp4Path) throws IOException {
        DataSource h264File = new FileDataSourceImpl(h264Path);
        H264TrackImpl h264Track = new H264TrackImpl(h264File);
        Movie movie = new Movie();
        movie.addTrack(h264Track);
        Container out = new DefaultMp4Builder().build(movie);
        FileOutputStream fos = new FileOutputStream(mp4Path);
        out.writeContainer(fos.getChannel());
        fos.close();
    }

}
