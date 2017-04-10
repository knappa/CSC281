import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A program to isolate and boost the least significant bits of an image.
 * Useful for demonstrating ideas about steganography.
 * <p>
 * Created on 4/6/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class ImageLeastSignificantBits {

  public static void main(String[] args) {

    if (args.length == 2) {
      getLSB(args[0], args[1]);
    } else {
      printUsage();
    }

  }

  private static void getLSB(String sourceFilename, String targetFilename) {

    // read from disk
    BufferedImage sourceImage = readImage(sourceFilename);
    assert sourceImage != null;

    // create the target image
    BufferedImage targetImage = new BufferedImage(sourceImage.getWidth(),
      sourceImage.getHeight(),
      BufferedImage.TYPE_INT_ARGB);

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int argb = sourceImage.getRGB(col, row);
        int a = (argb >> 24) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = argb & 0xFF;

        // get the (two) least significant bits
        int rMod = (r & 0b11);
        int gMod = (g & 0b11);
        int bMod = (b & 0b11);
        // boost for visibility
        rMod <<= 6;
        gMod <<= 6;
        bMod <<= 6;
        // note that we leave the alpha channel alone.
        // Images tend to have either a constant alpha or an engineered one,
        // so changes here would be more easily detectable.
        int argbMod = (a << 24) | (rMod << 16) | (gMod << 8) | bMod;
        targetImage.setRGB(col, row, argbMod);
      }
    }

    // write to disk
    writeImage(targetFilename, targetImage);

  }

  private static void printUsage() {
    System.err.println("Usage:");
    System.err.println("java ImageLeastSignificantBits source target");
    System.exit(-1);
  }

  private static BufferedImage readImage(String filename) {
    try {
      return ImageIO.read(new File(filename));
    } catch (IOException e) {
      System.err.println("Could not read image!");
      System.exit(1);
      assert false;
      return null; // never reached
    }
  }

  private static void writeImage(String filename, BufferedImage image) {
    try {
      ImageIO.write(image, "png", new File(filename));
    } catch (IOException e) {
      System.err.println("Could not write image!");
      System.exit(2);
    }
  }

}
