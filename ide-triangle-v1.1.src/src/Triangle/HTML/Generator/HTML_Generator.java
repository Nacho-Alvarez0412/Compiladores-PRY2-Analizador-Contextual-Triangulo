/**
 * @newClass
 * @description Clase que se encarga de genenerar los HTML basado en un código dado por el compilador
 *              los archivos generados son almacenados en la carpeta HTMLs del proyecto
 * @author Ignacio Alvarez
 * @codigo I.2
 */
package Triangle.HTML.Generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import static jdk.nashorn.internal.objects.NativeError.getFileName;

/**
 *
 * @author nacho
 */
public class HTML_Generator {
    private String code;
    private ArrayList<HTML_Token> tokens;
    private String fileName;

    
    public HTML_Generator(){
        this.code = "";
        this.tokens = new ArrayList<>();
        this.fileName = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String path) {
        this.code = ReadFile(path);
        this.fileName = path.substring(path.lastIndexOf("\\")+1).replaceAll(".tri", ".html");        
    }

    public ArrayList<HTML_Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<HTML_Token> tokens) {
        this.tokens = tokens;
    }
    
    

    public String  ReadFile(String path) {
        String text = "";
        try {
          File myObj = new File(path);
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            text = text.concat(data+"\n");
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        return text;
    }
    
    public ArrayList<String> getCodeSections(){
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        
        for (int i = 0 ; i < this.code.length(); i++){
            if(isSpecial(this.code.charAt(i))){
                words.add(word);
                words.add(this.code.charAt(i)+"");
                word = "";
            }
            
            else{
                word = word.concat(this.code.charAt(i)+"");
            }
            
        }
        words.add(word);
        return words; 
    }
    public ArrayList<HTML_Token> getTokens(ArrayList<String> sections){
        ArrayList<HTML_Token> codeTokens = new ArrayList<>();
        
        for (String preToken : sections){
            HTML_Token token = new HTML_Token(preToken);
            codeTokens.add(token);
        }
        
        codeTokens = fixComments(codeTokens);
        return codeTokens;
        
    }
    private boolean isSpecial(char charAt) {
        HTML_Token tokenIdentifier = new HTML_Token();
        return (charAt == '\n') || (charAt == ' ') || (charAt == '\t') || (tokenIdentifier.isOperator(charAt+"")) || (tokenIdentifier.isSeparator(charAt+""));
    }

    private ArrayList<HTML_Token> fixComments(ArrayList<HTML_Token> codeTokens) {
        boolean commentZone = false;
        

        for (HTML_Token token : codeTokens){
            if(token.getType() == HTML_Token_Enum.COMMENT)
                commentZone = true;
            if (token.getType() == HTML_Token_Enum.ENTER)
                commentZone = false;
            if(commentZone)
                token.setType(HTML_Token_Enum.COMMENT);
        }
        

        codeTokens = unifyComments(codeTokens);
        return codeTokens;
        
        
    }

    private ArrayList<HTML_Token> unifyComments(ArrayList<HTML_Token> codeTokens) {
        

        ArrayList<HTML_Token> unifiedTokens = new ArrayList<>();
        String comment = "";
        HTML_Token tempToken = new HTML_Token(comment);
        
        for(HTML_Token token : codeTokens){
            if(token.getType() == HTML_Token_Enum.COMMENT){
                comment = comment.concat(token.toString());
            }
            else if(token.getType() != HTML_Token_Enum.COMMENT && !comment.isEmpty()){
                
                tempToken = new HTML_Token(comment);
                unifiedTokens.add(tempToken);
                comment = "";
                unifiedTokens.add(token);
            }
            else
                unifiedTokens.add(token);
            
        }
        if(!comment.isEmpty() && codeTokens.get(codeTokens.size()-1).getType() == HTML_Token_Enum.COMMENT ){
            tempToken = new HTML_Token(comment);
            unifiedTokens.add(tempToken);
        }
        return unifiedTokens;
    }
        

    

    public void generateHTML() throws IOException{
        String html = "";
        ArrayList<String> preTokens = getCodeSections();

        ArrayList<ArrayList<HTML_Token>> tokensByLines = getLines(getTokens(preTokens));
        
        
        for(ArrayList<HTML_Token> line : tokensByLines){
            html = html.concat(lineToString(line));
        }
        
        File file = new File("HTMLs\\"+this.fileName);
        file.getParentFile().mkdirs();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file)); 
            bw.write(html);
            bw.close();
        } catch(IOException e) {
        }
    }
    
    public ArrayList<ArrayList<HTML_Token>> getLines(ArrayList<HTML_Token> tokens){
        ArrayList<ArrayList<HTML_Token>> lines = new ArrayList<>();
        ArrayList<HTML_Token> temp = new ArrayList<>();
        
        for(HTML_Token token : tokens){
            temp.add(token);
            if(token.getType() == HTML_Token_Enum.ENTER){
                lines.add(temp);
                temp = new ArrayList<>();
            }
                    
        }
        lines.add(temp);
        return lines;
    
    }
    
    private String writeComment(HTML_Token comment){
        return "<span style=\"color: #99cc00;\">"+comment.toString()+"</span>"; 
    }
    
    private String writeLiteral(HTML_Token literal){
        return "<span style=\"color: #3366ff;\">"+literal.toString()+"</span> "; 
    }
    
    private String writeReserved(HTML_Token reserved){
        return "<strong>"+reserved+"</strong>" ; 
    }
    
    private String writeFormat(int tabs){
        return "<p style=\"padding-left: "+tabs+"rem; font-family: 'DejaVu Sans', monospace; \">"; 
    }
    
    private String writeEOL(){
        return "</p>"; 
    }
    
    private int getTabs(ArrayList<HTML_Token> tokens){
        if(tokens.isEmpty())
            return 0;
        else if (tokens.get(0).getType() == HTML_Token_Enum.TAB){
            tokens.remove(0);
            return 1+getTabs(tokens);
        }
        else{
            tokens.remove(0);
            return 0+getTabs(tokens);
        }
        
    }

    private String lineToString(ArrayList<HTML_Token> line) {
        
        String lineString = "";
        
        lineString = lineString.concat(writeFormat(getTabs((ArrayList<HTML_Token>) line.clone())));
        
        for(HTML_Token token : line){
            
            switch (token.getType()){
                case COMMENT:
                    lineString = lineString.concat(writeComment(token));
                    break;
                case LITERAL:
                    lineString = lineString.concat(writeLiteral(token));
                    break;
                case RESERVED:
                    lineString = lineString.concat(writeReserved(token));
                    break;
                    
                default:
                    
                    lineString = lineString.concat(token.toString());
                    break;
            }
        }
        lineString = lineString.concat(writeEOL());
        return lineString;
    }

}
