package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;

public class GameFile extends File {

    public interface Writer { String[] write(); }
    public interface Reader { void read(String line); }
    public interface Replacer { String replace(String s); }

    public GameFile(String pathname) {
        super("./res/" + pathname);
    }

    public void forEachLine(Reader reader) {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(this));

            String line;
            while ((line = bReader.readLine()) != null)
                reader.read(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readLines() {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(this));

            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = bReader.readLine()) != null)
                lines.add(line);

            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void write(String line) {
        try {
            FileWriter writer = new FileWriter(this);

            writer.write(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line) { write(line + "\n"); }

    public void clear() {
        try {
            delete();
            createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceLines(Replacer replacer) {
        ArrayList<String> lines = readLines();
        for (int i = 0; i < lines.size(); i++) {
            String newLine = replacer.replace(lines.get(i));
            lines.set(i, newLine);
        }
    }
}
