package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("First Picture",800,500);
        //Printing a red net over yellow background
        for(int i = 0;i < 800; i++){
            for(int j = 0; j < 500; j++) {
                //Coordinates of the net
                if((i+1)%50 == 0 || (j+1)%50 == 0){
                    //Green
                    imageWriter.writePixel(i, j, new Color(144, 238, 144));
                }
                //Background
                else {
                    //Purple
                    imageWriter.writePixel(i, j, new Color(86, 81, 100));
                }
            }
        }

        imageWriter.writeToImage();

    }

//     @Test
//    void testWritePixel() {
//    }
}