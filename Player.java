/**
 * A program that takes in user input to fill a Playlist.
 */
/**
 * The <code>Player</code> simulates a Playlist that takes
 * in user input.
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 *    CSE214.R04
 **/

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SongLinkedList list = new SongLinkedList();
        boolean stop = false;
        String menu = "Menu:\n" +
                "(A) Add Song to Playlist\n" +
                "(F) Go to Next Song\n" +
                "(B) Go to Previous Song\n" +
                "(R) Remove Song from Playlist\n" +
                "(L) Play a Song\n" +
                "(C) Clear the Playlist\n" +
                "(S) Shuffle Playlist\n" +
                "(Z) Random Song\n" +
                "(P) Print Playlist\n" +
                "(T) Get the total amount of songs in the playlist\n" +
                "(Q) Exit the playlist\n" +
                " \n" +
                "Enter an option: ";
        while (!stop) {
            System.out.println(menu);
            String input = in.next().toUpperCase();
            if (input.equals("A")) {
                in.nextLine();
                System.out.print("Enter song title: ");
                String title = in.nextLine();
                if (title.isEmpty()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                //in.nextLine();
                System.out.print("Enter artist(s) of the song: ");
                String artist = in.nextLine();
                if (artist.isEmpty()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                //in.nextLine();
                System.out.print("Enter album: ");
                String album = in.nextLine();
                if (album.isEmpty()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                //in.nextLine();
                System.out.print("Enter length (in seconds): ");
                if (!in.hasNextInt()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                int seconds = in.nextInt();
                Song newSong = new Song(title, artist, album, seconds);
                try {
                    if (list.getSize() == 0) {
                        list.addSongFront(newSong);
                    }
                    else {
                        list.insertAfterCursor(newSong);
                    }
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Invalid input: Song not found.");
                }
                System.out.println("'" + list.getCursor().getSong().getName() +
                  "' by " + list.getCursor().getSong().getArtist() + " has " +
                  "been added to your playlist.");
            }
            else if (input.equals("F")) {
                list.cursorForwards();
                System.out.println("Cursor moved to next song.");
            }
            else if (input.equals("B")) {
                list.cursorBackwards();
                System.out.println("Cursor moved to previous song.");
            }
            else if (input.equals("R")) {
                System.out.println("'" + list.getCursor().getSong().getName() +
                  "' by " + list.getCursor().getSong().getArtist() + " has " +
                  "been removed from your playlist.");
                list.removeNode();
            }
            else if (input.equals("L")) {
                in.nextLine();
                System.out.print("Enter name of song to play: ");
                String name = in.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                try {
                    list.play(name);
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Song not found.");
                }
            }
            else if (input.equals("C")) {
                if (list.getSize() == 0) {
                    System.out.println("Invalid input. The list is empty.");
                    continue;
                }
                list.deleteAll();
                System.out.println("Playlist cleared.");
            }
            else if (input.equals("S")) {
                if (list.getSize() == 0) {
                    System.out.println("Invalid input. The list is empty.");
                    continue;
                }
                list.shuffle();
                System.out.println("Playlist shuffled.");
            }
            else if (input.equals("Z")) {
                if (list.getSize() == 0) {
                    System.out.println("Invalid input. The list is empty.");
                    continue;
                }
                list.random();
                System.out.println("Playing a random song... ");
                System.out.println("'" + list.getCursor().getSong().getName() +
                  "' by " + list.getCursor().getSong().getArtist() + " is now" +
                  "playing.");
            }
            else if (input.equals("P")) {
                list.printPlaylist();
                System.out.println("");
            }
            else if (input.equals("T")) {
                System.out.println("Your playlist contains " + list.getSize() +
                        " song(s).");
            }
            else if (input.equals("Q")) {
                stop = true;
                continue;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
        in.close();
    }
}

