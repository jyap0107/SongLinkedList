/**
 * The <code>Song</code> simulates information for a Song.
 *
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 **/
public class Song {
    private String name;
    private String artist;
    private String album;
    private int length;

    /**
     * Constructs a Song Object with empty Strings and default int values
     *
     * <dt>Postcondition
     *    <dd><code>Song</code> has been initialized with empty
     *    Strings and int values of 0.</dd>
     */
    public Song() {
        name = "";
        artist = "";
        album = "";
        int length = 0;
    }
    /**
     * Constructs an Song Object with given Strings and default int values
     *
     * @param name The song name
     * @param album The album name
     * @param artist The artist name
     * @param length The length of the song in seconds
     * <dt>Postcondition
     *    <dd><code>Song</code> has been initialized to given values</dd>
     */
    public Song(String name, String artist, String album, int length) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    /**
     * Sets the name of a song.
     * @param name the name of the song to be set.
     *
     * <dt>Postcondition</dt>
     *    <code>name</code> has been instantiated.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the song
     * @return The song's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the song's artist.
     * @param artist The artist's name.
     *
     * <dt>Postcondition</dt>
     *    <code>artist/code> has been instantiated.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Returns the song's artist
     * @return the song's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the song's album name
     * @param album The song's album name
     * <dt>Postcondition</dt>
     *    <code>album</code> has been instantiated.
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Returns the album name
     * @return the song's album name
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets the song's length in seconds
     * @param length the song's length in seconds
     * <dt>Postcondition</dt>
     *    <code>length</code> has been instantiated.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns the song's length in seconds
     * @return the song's length in seconds
     */
    public int getLength() {
        return length;
    }

}
