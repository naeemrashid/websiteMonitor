import java.io.IOException;
import java.io.OutputStream;
 
import javax.swing.JTextArea;

import com.jfoenix.controls.JFXTextArea;
 
/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * @author www.codejava.net
 *
 */
public class custom extends OutputStream {
    private JFXTextArea textArea;
     
    public custom(JFXTextArea area) {
        this.textArea = area;
    }
     
    @Override
    public void write(int b) throws IOException {
        textArea.appendText(String.valueOf((char)b));
    }
}