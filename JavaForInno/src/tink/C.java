package tink;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Directory {
    String name;
    List<Directory> next = new ArrayList<>();

    Directory(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return name.equals(directory.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void addNext(Directory next) {
        if (this.next.contains(next)) {
            return;
        }
        this.next.add(next);
    }

    public void removeNext() {
        this.next = null;
    }

    public void print(int tab, List<Directory> directories) {
        if (directories.contains(this)) {
            for (int i = 0; i < tab; i++) {
                System.out.print(" ");
            }
            System.out.print(name + "\n");
            if (next.size()!=0) {
                for(Directory next : next) {
                    next.print(tab + 1, directories);
                }
            }
        } else {
            return;
        }
    }
}

public class C {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        List<Directory> directories = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            String[] arr = input.split("/");

            Directory parent = null;
            for (int j = 0; j < arr.length; j++) {
                Directory current = findDirectory(directories, arr[j]);
                if (current == null) {
                    current = new Directory(arr[j]);
                    directories.add(current);
                }
                if (parent != null) {
                    parent.addNext(current);
                }
                parent = current;
            }
        }

        if (!directories.isEmpty()) {
            directories.get(0).print(0, directories);
        }
    }

    private static Directory findDirectory(List<Directory> directories, String name) {
        for (Directory directory : directories) {
            if (directory.name.equals(name)) {
                return directory;
            }
        }
        return null;
    }
}
