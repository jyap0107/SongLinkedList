/**
 * The <code>SongNode</code> simulates a Node for a Song.
 *
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 **/

public class SongNode {
    private Song data;
    private SongNode prev;
    private SongNode next;

    /**
     * Sets the SongNode's data
     * @param data the SongNode's data
     * <dt>Postcondition</dt>
     *    <code>data</code> has been instantiated.
     */
    public void setSong(Song data) {
        this.data = data;
    }

    /**
     * Returns the SongNode's data as a Song object
     * @return The SongNode's data as a Song object
     */
    public Song getSong() {
        return data;
    }

    /**
     * Sets the SongNode's previous link
     * @param prev the SongNode's previous link.
     * <dt>Postcondition</dt>
     *    <code>prev</code> has been instantiated.
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**
     * Returns the SongNode's previous link
     * @return the SongNode's previous link
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * Sets the SongNode's next link.
     * @param next The SongNode's next link.
     * <dt>Postcondition</dt>
     *    <code>next</code> has been instantiated.
     */
    public void setNext(SongNode next) {
        this.next = next;
    }

    /**
     * Returns the SongNode's next link
     * @return The SongNode's next link
     */
    public SongNode getNext() {
        return next;
    }

    /**
     * Constructs an empty SongNode object
     * <dt>Postcondition</dt>
     *    <code>SongNode</code> has been set to have null links and an empty
     *    Song
     */
    public SongNode() {
        data = new Song();
        prev = null;
        next = null;
    }

    /**
     * Constructs a SongNode object with a given Song
     * @param data The Song to be assigned to the SongNode
     * <dt>Postcondition</dt>
     *    <code>data</code> has been instantiated.
     */
    public SongNode(Song data) {
        this.data = data;
    }

    /**
     * Constructs a fully initialized SongNode object
     * @param prev The previous link
     * @param data The Song to be set to the SongNode object
     * @param next The next link
     * <dt>Postcondition</dt>
     *    <code>SongNode</code> has been instantiated.
     */
    public SongNode(SongNode prev, Song data, SongNode next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }


}
