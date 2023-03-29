import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;



import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
  
  /**
   * 
   */
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    // Leitura da imagem
    //InputStream inputStream = new FileInputStream("entrada/filme.jpg");
    //InputStream inputStream = new URL("https://m.media-amazon.com/images/I/91X9p6+58KL._AC_SL1500_.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // Cria uma nova imagem em memória com transparência e com tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // Cópiar imagem original pra novo imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // Configura uma fonte
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    // Escrever uma frase na nova imagem
    graphics.drawString("TOPZERA", 100, novaAltura - 100);

    // Escrever nova imagem em um arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  }
  
}
