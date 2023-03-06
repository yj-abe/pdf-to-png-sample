import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws Exception {

        ImageIO.scanForPlugins();

        byte[] pdfContent = Files.readAllBytes(Paths.get("/path/to/file.pdf"));

        try (PDDocument doc = PDDocument.load(pdfContent)) {
            PDFRenderer renderer = new PDFRenderer(doc);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            BufferedImage image = renderer.renderImageWithDPI(0, 150, ImageType.RGB);
            ImageIOUtil.writeImage(image, "png", buffer, 150, 0);
            byte[] imageContent = buffer.toByteArray();
            Files.write(Paths.get("/path/to/output.png"), imageContent);
        }
    }

}
