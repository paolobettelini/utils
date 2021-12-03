package braille;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Braille {

    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java Braille <Path> [Double]");
            System.out.println("\tPath: the path of the image");
            System.out.println("\tDouble: the brightness threshold [0;1]");
            return;
        }

        double threadhold = .5;
        if (args.length == 2) {
            try {
                threadhold = Double.parseDouble(args[1]);

                if (threadhold < 0 || threadhold > 1) {
                    System.out.println("Invalid threadhold");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid threadhold");
                return;
            }
        }

        var img = ImageIO.read(new File(args[0]));
//        img = resizeImage(img, 200, 200);

        int w = (img.getWidth() >> 1 << 1) - 2;
        int h = (img.getHeight() >> 2 << 2) - 4;

        int[] offX = {0, 0, 0, 1, 1, 1, 0, 1};
        int[] offY = {0, 1, 2, 0, 1, 2, 3, 3};

        for (int y = 0; y < h; y += 4) {
            for (int x = 0; x < w; x += 2) {
                int v = 0;
                for (int i = 0; i < 8; i++) {
                    v |= isBright(img.getRGB(x + offX[i], y + offY[i]), threadhold) << i;
                }
                System.out.print((char) (0x2800 + v));
            }
            System.out.println();
        }
    }

    private static int isBright(int rgb, double threshold) {
        return ((rgb >> 16) & 255) + ((rgb >> 8) & 255) + (rgb & 255) > threshold * 765 ? 0 : 1;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}
