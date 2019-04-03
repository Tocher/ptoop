package plugins;

import button.ButtonListener;
import pluginLoader.Plugin;
import shapes.base.Shape;
import shapesDrawer.ShapesDrawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchivePlugin implements Plugin {
    public void load(JPanel panel) {
        // archive
        JButton archiveButton = new JButton("Архивировать");

        archiveButton.addActionListener(new ButtonListener() {
            public void actionPerformed(ActionEvent e) {
                zip();
            }
        });

        panel.add(archiveButton);

        // load archive
        JButton loadArchiveButton = new JButton("Загрузить");
        loadArchiveButton.addActionListener(new ButtonListener() {
            public void actionPerformed(ActionEvent e) {
                unzip();
            }
        });

        panel.add(loadArchiveButton);
    }

    private void zip(){
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("archive.zip"));

            ZipEntry entry1=new ZipEntry("shapes.txt");
            zout.putNextEntry(entry1);

            zout.write(this.getShapesBytes());

            zout.closeEntry();

            zout.finish();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    private void unzip(){
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream("archive.zip"));

            while(zin.getNextEntry()!=null){

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                for (int c = zin.read(); c != -1; c = zin.read()) {
                    baos.write(c);
                }

                getShapesFromBytes(baos.toByteArray());

                zin.closeEntry();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    private byte[] getShapesBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] shapeBytes = new byte[0];

        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ShapesDrawer.getInstance().getSelectedShapes());
            out.flush();
            return bos.toByteArray();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return shapeBytes;
    }

    private void getShapesFromBytes(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;

        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();

            if (o instanceof ArrayList) {
                ShapesDrawer.getInstance().getShapesList().addAll((List<Shape>) o);

                ShapesDrawer.getInstance().updateModel();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
