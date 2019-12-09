/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage
 *
 *  @author: Jing Yang 
 *  Making collage of a picture, replace the image, change the color
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
        this.collageDimension=4;
        this.tileDimension=100;

        this.original=new Picture(filename);
        this.collage=new Picture(tileDimension*collageDimension,tileDimension*collageDimension);//the scale of the new picture is actually decide here  
        Color color;
        for(int col=0;col<(tileDimension*collageDimension);col++){
            for(int row=0;row<tileDimension*collageDimension;row++){
                int scol = col * original.width() / (tileDimension*collageDimension); //for the purpose of obtaining the index of pixel in the original picture (to get the correlated color for the new one), so that we know exactly which color in the new picture is coming from.   
                int srow = row * original.height() / (tileDimension*collageDimension);//the scol and scrow is actually the index of pixel of the orignial picture
                color =  original.get(scol,srow);
                collage.set(col,row,color);
            }
        }    
        
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */

    public ArtCollage (String filename, int td, int cd) {
        collageDimension=cd;
        tileDimension=td;

        original=new Picture(filename);
        collage=new Picture(tileDimension*collageDimension,tileDimension*collageDimension);

        for(int col=0;col<(tileDimension*collageDimension);col++){
            for(int row=0;row<tileDimension*collageDimension;row++){
                int scol = col * original.width() / (tileDimension*collageDimension);
                int srow = row * original.height() / (tileDimension*collageDimension);
                Color color =  original.get(scol,srow);
                collage.set(col,row,color);
            }
        }   
    }
    
    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return collage;
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        original.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture pic=new Picture(filename);
        Picture collage1=new Picture(tileDimension,tileDimension);
        for(int col=0;col<tileDimension;col++){
            for(int row=0;row<tileDimension;row++){
                int necol=col*pic.width()/tileDimension;
                int nerow=row*pic.height()/tileDimension;
                Color newcolor=pic.get(necol,nerow);
                collage1.set(col,row,newcolor);
            }
        }

        for(int col=0;col<tileDimension*collageDimension;col++){
            for(int row=0;row<tileDimension*collageDimension;row++){
                if(col/tileDimension==collageCol && row/tileDimension==collageRow){
                    int newcol=(col%tileDimension);//*collage1.width()/tileDimension;
                    int newrow=(row%tileDimension);//*collage1.height()/tileDimension;
                    Color newColor=collage1.get(newcol,newrow);
                    collage.set(col,row,newColor);
                }
            }
        }
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {
        Color color1;
        for(int col=0;col<collageDimension*tileDimension;col++){
            for(int row=0;row<collageDimension*tileDimension;row++){
                int newCol=(col%tileDimension)*original.width()/tileDimension;
                int newRow=(row%tileDimension)*original.height()/tileDimension;
                color1=original.get(newCol,newRow);
                collage.set(col,row,color1);
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        for(int col=0;col<collageDimension*tileDimension;col++){
            for(int row=0;row<collageDimension*tileDimension;row++){
                if(col/tileDimension==collageCol && row/tileDimension==collageRow){
                    
                    Color color=collage.get(col,row);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();

                    if(component.equals("red")){
                        Color red=new Color(r,0,0);
                        collage.set(col,row,red);
                    }else if(component.equals("blue")){
                        Color blue=new Color(0,0,b);
                       collage.set(col,row,blue);
                    }else if(component.equals("green")){
                        Color green=new Color(0,g,0);
                       collage.set(col,row,green);
                    }
                }
            }
        }

    }

    /*
     * Greyscale tile at (collageCol, collageRow)
     * (see Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void greyscaleTile (int collageCol, int collageRow) {
        for(int col=0;col<collageDimension*tileDimension;col++){
            for(int row=0;row<collageDimension*tileDimension;row++){
                if(col/tileDimension==collageCol && row/tileDimension==collageRow){
                    Color color= collage.get(col, row);
                    Color gray  = Luminance.toGray(color);
                    collage.set(col, row, gray);
                }
            }
        }
    }


    // Test client 
    public static void main (String[] args) {
        // ArtCollage art = new ArtCollage(args[0]);
        // art.showCollagePicture();
        //art.getWidth(args[0]);
        ArtCollage art = new ArtCollage(args[0], 200, 3);
        art.makeCollage();

        //art.showCollagePicture();

        //art.greyscaleTile(1, 0);
        //art.showCollagePicture();

        // art.colorizeTile("blue",1,2);
        // art.showCollagePicture();

        // art.replaceTile(args[1],1,1);
        // art.showCollagePicture();
        
    }
}
