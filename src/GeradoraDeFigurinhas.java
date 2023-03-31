import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.BasicStroke;



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
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 80);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    // Escrever uma frase na nova imagem
    String texto = "TOPZERA";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoTextoX =  (largura - larguraTexto) / 2;
    int posicaoTextoY = novaAltura - 100;
    graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    var textLayout =  new TextLayout(texto, fonte, fontRenderContext);
    Shape outline =  textLayout.getOutline(null);
    AffineTransform transform =  graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);

    var outlineStroke = new BasicStroke(largura * 0.004f);
    graphics.setStroke(outlineStroke);
    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);

    // Escrever nova imagem em um arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  }
  
}
