import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

public class Mag extends JFrame {
    JTable t;
    public Mag(){
        setTitle("mag");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String[] k={"id","nazwa","ilosc","cena"};
        Object[][] d={
                {1,"banan",4,0.45},
                {2,"komputer",1,99.99},
                {3,"chusteczki",7,2.31},
                {4,"kabel",3,12.00},
                {5,"przewod",9,5.67}
        };
        DefaultTableModel m=new DefaultTableModel(d,k);
        t=new JTable(m);
        add(new JScrollPane(t),BorderLayout.CENTER);
        JButton b=new JButton("pdf");
        b.addActionListener(e->pdf());
        add(b,BorderLayout.SOUTH);
        setVisible(true);
    }
    public void pdf(){
        JFileChooser c=new JFileChooser();
        int s=c.showSaveDialog(this);
        if(s==JFileChooser.APPROVE_OPTION){
            try{
                String p=c.getSelectedFile()+".pdf";
                PdfWriter w=new PdfWriter(p);
                PdfDocument pd=new PdfDocument(w);
                Document doc=new Document(pd);
                Table tb=new Table(t.getColumnCount());
                for(int i=0;i<t.getColumnCount();i++){
                    tb.addCell(new Cell().add(new Paragraph(t.getColumnName(i))));
                }
                for(int r=0;r<t.getRowCount();r++){
                    for(int x=0;x<t.getColumnCount();x++){
                        tb.addCell(new Cell().add(new Paragraph(t.getValueAt(r,x).toString())));
                    }
                }
                doc.add(tb);
                doc.close();
                JOptionPane.showMessageDialog(this,"ok");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] a){
        new Mag();
    }
}