/**
 * The <code>SongLinkedList</code> simulates a Linked List for a
 * Song.
 *
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 **/

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size = 0;

    /**
     * Returns the cursor's current Node
     * @return The cursor
     */
    public SongNode getCursor() {
        return cursor;
    }

    /**
     * Constructs an empty linked list of SongNode objects.
     * <dt>Postcondition</dt>
     *    An empty <code>SongLinkedList</code> has been created
     */
    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Adds a song to the front of the linked list.
     * @param newSong The song to add in front.
     * @throws IllegalArgumentException
     * <dt>Postcondition</dt>
     *    The <code>newSong</code> has been added to the front of the list.
     */
    public void addSongFront(Song newSong) throws IllegalArgumentException {
        SongNode newSongNode = new SongNode(newSong);
        newSongNode.setNext(head);
        if (!(tail == null)) {
            head.setPrev(newSongNode);
        }
        head = newSongNode;
        head.setPrev(null);
        if (tail == null) {
            tail = head;
        }
        cursor = head;
        size++;
    }

    /**
     * Plays the wav file matching the given song name
     * <dt>Precondition</dt>
     *    The file name must match a song in the playlist, and there must be
     *    an associated file
     * @param name The song to play
     * @throws IllegalArgumentException
     * <dt>Postcondition</dt>
     *    The song is now playing.
     */
    public void play(String name) throws IllegalArgumentException {
        name = name + ".wav";
        try {
            AudioInputStream AIS =
                    AudioSystem.getAudioInputStream(new File(name));
            Clip c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
        }
        catch (Exception ex) { }
    }

    /**
     * Moves the cursor to the next song if it exists.
     * <dt>Precondition</dt>
     *    The list is not empty.
     * <dt>Postcondition</dt>
     *    <code>cursor</code> has been moved to the next song if it exists.
     */
    public void cursorForwards() {
        if (cursor != tail) {
            cursor = cursor.getNext();
        }
    }
    /**
     * Moves the cursor to the previous song if it exists.
     * <dt>Precondition</dt>
     *    The list is not empty.
     * <dt>Postcondition</dt>
     *    <code>cursor</code> has been moved to the previous song if it exists.
     */
    public void cursorBackwards() {
        if (cursor != head) {
            cursor = cursor.getPrev();
        }
    }

    /**
     * Inserts a newSong after the cursor
     * <dt>Precondition</dt>
     *    A newSong object has been instantiated.
     * @param newSong The song to insert after the cursor
     * @throws IllegalArgumentException
     * <dt>Postcondition</dt>
     *    A song has been inputted after the cursor, with the cursor pointing
     *    to the new node.
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException{
        SongNode newNode = new SongNode(newSong);
        if (cursor == tail) {
            newNode.setPrev(cursor);
            cursor.setNext(newNode);
            newNode.setNext(null);
            tail = newNode;
            cursor = tail;
        }
        else {
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor.getPrev());
            cursor.setNext(newNode);
            newNode.getNext().setPrev(newNode);
            cursor = newNode;
        }
        size++;
    }

    /**
     * Removes the node where the cursor is pointing
     * <dt>Postcondition</dt>
     *    The song referenced by the cursor is removed, and the cursor then
     *    points to the next node, or the previous one if there is no next node.
     */
    public void removeNode() {
        if ((cursor == head) && (head == tail)) {
            cursor.setNext(null);
            head = null;
            tail = null;
        }
        else if (cursor == head) {
            cursor = cursor.getNext();
            cursor.setPrev(null);
            head = cursor;
            //cursor.getPrev().setNext(null);
        }
        else if (cursor == tail) {
            cursor = cursor.getPrev();
            //cursor.getNext().setPrev(null); //necessary? Or is the other one
            // enough to break the connection? Ask.
            cursor.setNext(null);
            tail = cursor;
        }
        else {
            SongNode temp = cursor;
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = temp.getNext();
        }
        size--;
    }

    /**
     * Retrieves the Node of the cursor
     * @return The cursor's Node
     */
    public SongNode retrieveNode() {
        SongNode s = cursor; //Doesn't change with cursor?
        this.removeNode();
        return s;
    }

    /**
     * <dt>Precondition</dt>
     *    The cursor is not null
     * Removes the Song of the SongNode referenced by the cursor
     * @return the Song of the SongNode referenced by the cursor
     */
    public Song removeCursor() {
        SongNode songToReturn = retrieveNode();
        if (cursor != tail) {
            cursor = cursor.getNext();
        }
        else {
            cursor = cursor.getPrev();
        }
        return songToReturn.getSong();
    }

    /**
     * Returns the size of the playlist
     * @return the size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns a random song and plays it.
     * @return a random song
     * <dt>Postcondition</dt>
     *    The random song is now playing.
     */
    public Song random(){
        int i = 0;
        cursor = head;
        int rand = (int) Math.random() * size;
        while (i != rand) {
            cursorForwards();
            i++;
        }
        play(cursor.getSong().getName());
        return cursor.getSong();
    }

    /**
     * Shuffles the playlist
     * <dt>Postcondition</dt>
     *    <code>SongLinkedList</code> has been shuffled
     */
    public void shuffle() {
        cursor = head;
        int tempSize = this.getSize();
        SongLinkedList temp = new SongLinkedList();
        while (tempSize > 0) {
            for (int i = 0; i < Math.random() * tempSize; i++) {
                cursorForwards();
            }
            printPlaylist();
            System.out.println("");
            Song a = this.retrieveNode().getSong();
            temp.addSongFront(a);
            cursor = head;
            tempSize--;
        }
        boolean cursorEstablished = false;
        while (temp.getSize() > 0) {
            this.addSongFront(temp.retrieveNode().getSong());
            if (cursorEstablished) {
                cursorForwards();
            }
            if (!cursorEstablished) {
                cursor = head;
                cursorEstablished = true;
            }
            temp.printPlaylist();
            System.out.println("");
            printPlaylist();

        }
        cursor = head;
        printPlaylist();
    }

    /**
     * Prints the Playlist in a tabular form, pointing to wherever the cursor
     * currently is.
     * <dt>Postcondition</dt>
     *    <code>SongLinkedList</code> has been printed, with the Song
     *    referenced by the cursor noted.
     */
    public void printPlaylist() {
        SongNode pointer = head;
        System.out.println("Playlist");
        System.out.println("Song                     | Artist                " +
          "   | Album                    | Length (s)");
        System.out.println("-------------------------------------------------" +
          "------------------------------------------");
        for (int i = 0; i < this.getSize(); i++) {
            if (pointer == cursor) {
                System.out.printf("%-26s%-27s%-25s%5s%5s",
                  pointer.getSong().getName(), pointer.getSong().getArtist(),
                  pointer.getSong().getAlbum(), pointer.getSong().getLength(),
                  "<-");
            }
            else {
                System.out.printf("%-26s%-27s%-25s%5s%5s",
                  pointer.getSong().getName(), pointer.getSong().getArtist(),
                  pointer.getSong().getAlbum(), pointer.getSong().getLength(),
                  "");
            }
            System.out.println("");
            pointer = pointer.getNext();
        }
    }

    /**
     * Deletes the entire list.
     * <dt>Postcondition</dt>
     *    <code>SongLinkedList</code> is now empty.
     */
    public void deleteAll() {
        while (size > 0) {
            removeNode();
        }
    }

    /**
     * Returns the Playlist as a String in tabular form
     * @return the Playlist as a String in tabular form
     */
    public String toString() {
        String out = "";
        SongNode pointer = new SongNode();
        pointer = head;
        out += "Playlist \n";
        out += ("Song                     | Artist                " +
                "   | Album                    | Length (s)");
        out += ("\n -------------------------------------------------" +
                "------------------------------------------ \n");
        for (int i = 0; i < this.getSize(); i++) {
            out += String.format("%-26s%-27s%-25s%5s",
              pointer.getSong().getName(), pointer.getSong().getArtist(),
              pointer.getSong().getAlbum(), pointer.getSong().getLength());
            out += "\n";
            pointer = pointer.getNext();
        }
        return out;
    }

}
