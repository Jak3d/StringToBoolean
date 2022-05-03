/**
 * prova
 */

/* To do 

    ■ File can only be read from depending class, not secure as it requires file to be in same folder.
    ■ Rimuovere i char nulli dalla lettura del file
    ■ Capire se funziona con più righe nel file (forse trasformare in matrix e usare il lettore file per numero riga?)
    ■ Creare classe di debug
    ■ Aggiungere divisore di frasi quando compare un certo simbolo

*/



import java.lang.Exception;
import java.lang.ProcessBuilder.Redirect;
import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner; 
public class prova  {
/*
    public static float somma(float a, float b){
		return a+b;
	}
    public static float sottrazione(float a, float b){
        return a-b;
    }
    public static boolean arrayTuttiZero(int[] a){
        for (int i= 0 ; i < a.length;i++ ){
            if(a[i] != 0){
                return false;
            }
        }
        return true;
    }
    */
    
    static String absoluteFirst = "";
    static String absoluteSecond ="";
    static char absoluteOperator;
    /*
    public static void Debug(int value){
        
    }
    */
    public static void checkBool(String condizione){
        boolean doubleOperator =false;
        int offset =0;
        char[] destroyedString = new char[condizione.length()];
        //smantellamento stringa
                            System.out.println("Lunghezza = "+condizione.length());
        for ( int i = 0; i < condizione.length(); i++){
            destroyedString[i]=condizione.charAt(i);
                            System.out.println("Pos "+i+"="+condizione.charAt(i));
                            System.out.println("Pos "+i+"="+destroyedString[i]);
            
        }
        //___
        String primo ="";
        char operazione = Character.MIN_VALUE;
        for (int k = 0; k < destroyedString.length ; k++){
                if(destroyedString[k] == '>'){
                    operazione = '>';
                 for (int s = 0; s < k; s++){
                    primo = primo + destroyedString[s];
                 }
                }
                else if (destroyedString[k]== '<'){
                    operazione = '<';
                 for (int s = 0; s <k; s++){
                    primo = primo + destroyedString[s];
                 }
                }
                else if (destroyedString[k]== '='){
                    
                    if(destroyedString[k+1] == '='){
                        doubleOperator = true;
                        operazione = '=';
                        for (int s = 0; s <k; s++){
                        primo = primo + destroyedString[s];
                        }
                    }

                 
                }
                else if( destroyedString[k]=='l'){ //Lesser equal then 
                    if(destroyedString[k+1]=='e'){
                        doubleOperator=true;
                        operazione='l';
                        for (int s = 0; s <k; s++){
                            primo = primo + destroyedString[s];
                            }
                    }
                }
                else if( destroyedString[k]=='g'){ //Greater equal then
                    if(destroyedString[k+1]=='e'){
                        doubleOperator=true;
                        operazione='g';
                        for (int s = 0; s <k; s++){
                            primo = primo + destroyedString[s];
                            }
                    }
                }
                 

            }
        
        int posOper = condizione.indexOf(operazione);
        System.out.println(posOper);

        String secondo = "";
        if(doubleOperator == true){
            offset = 1;
        }
        for(int k = posOper+1+offset; k < destroyedString.length; k++){
            secondo = secondo + destroyedString[k];
        }
        System.out.println("primo: "+primo+", operatore: "+operazione+", secondo: "+secondo);
        offset = 0;
        absoluteFirst = primo;
        absoluteSecond = secondo; 
        absoluteOperator = operazione;


    }
   private static int toASCII(char character){
    int valASCII = character;
    return valASCII;
   }
   private static int StringtoInt(String word){
        //Scrivere algoritmo per trasformare una somma in una iniezione (forse non serve)
        if(word != ""){
        int toInt = Integer.parseInt(word);
        return toInt;
        }
        return 0;
   }
   private static boolean combine(int primo, int secondo, int calcolo){
        if (calcolo == 60){ // <
            return (primo < secondo);
        } 
        else if (calcolo == 62){ // >
            return (primo > secondo);
        }
        else if (calcolo == 61){ // =
            return (primo == secondo);
        }
        else if (calcolo == 108){ // =
            return (primo <= secondo);
        }
        else if (calcolo == 103){ // =
            return (primo >= secondo);
        }
        else
        return false;
   }
   
   public static boolean wrapperBool(String frase){
       int operazione;
       checkBool(frase);

       operazione = toASCII(absoluteOperator);
       return combine(StringtoInt(absoluteFirst), StringtoInt(absoluteSecond), operazione);
   }
   

//_______________________________________________________________________________________________________

public static boolean boolTable(String testo){
    int m = 0;
    int nmbParentesi = 0;
    int chkParentesi = 0;
    int nOperazioni = 0;
    int counter = 0;
    char simbolo='?';
    int[] parPos = new int[testo.length()];
    String[] frasi = new String[testo.length()]; //ci sarà circa il doppio di slot rispetto alle parole effettive?
    for (int fill = 0; fill < frasi.length; fill++){
        frasi[fill] = "";
    }
    char[] dstSentence = new char[testo.length()];
    for (int i = 0; i < testo.length(); i++){
        if(testo.charAt(i) == '('){
            //....
            nmbParentesi++;
            chkParentesi++;
            parPos[counter]=i;
            counter++;
            if(testo.charAt(i+1) != '('){
                while (testo.charAt(i+1) != ')'){
                    
                    frasi[m] = frasi[m]+testo.charAt(i+1); //SONO NULLI???

                    i++;
                }
                m++;
            }
            
        }
        if(testo.charAt(i)== ')'){      
            //....
            chkParentesi--;
            parPos[counter]=i;
            counter++;
        }
        if (testo.charAt(i) == '&'){
            simbolo = '&';
        }
        if (testo.charAt(i) == '|'){
            simbolo = '|';
        }
    }
    assert(chkParentesi == 0):"Formula non correttamente scritta";
    boolean[] calcoli = new boolean[4];
    for(int k = 0;k < 4;k++){ //<4 for debug only  (si basa su lunghezza di frasi[] da sistemare)
        System.out.println(frasi[k]);
        calcoli[k]=wrapperBool(frasi[k]);
    
    }
    if (simbolo == '&'){
        return calcoli[0] && calcoli [1];
    }
    if (simbolo == '|'){
        return calcoli[0] || calcoli [1];
    }
    return false;

}



//_______________________________________________________________________________________________________



    private static String data;
    public static String getValueSheet(){
        readFile();
        System.out.println(Arrays.toString(divideFile(data)));
        return data;
    }
    

    private static void readFile(){
        try {
            File valueSheet = new File("Test.txt");
            Scanner myReader = new Scanner(valueSheet);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
              //System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }


    // Da mettere private
    // Questo array servirà come lista di operatori, come lista di numeri; 
    public static String[] divideFile(String line){
        int posizione = 0;
        int nParola = 0;
        //viene aggiunto all'array una casella null
        String[] words = new String[line.length()];
        //Words[] riempito di ""
            for (int w = 0; w < words.length; w++){
                words[w] = "";
            }
        char[] destroyedLine = new char[line.length()+1];
        //smantellamento stringa
        for ( int i = 0; i < line.length(); i++){
            destroyedLine[i]=line.charAt(i);                          
        }
        for (int k = 0; k < destroyedLine.length ; k++){
            
            if(destroyedLine[k] == '?'){
                if(destroyedLine[k+1] == '?' ){
                    
                    words[k] = "";

                    for (int s = posizione; s < k; s++){
                        words[nParola] = words[nParola] + destroyedLine[s];
                        posizione = s+3;
                }
                nParola = nParola+1;
             }
            }
        }
        return words;

    }
}
