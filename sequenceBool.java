import javax.print.DocFlavor.STRING;

public class sequenceBool extends prova {
    String base=null;
    static char[] simb = new char[254]; //limite calcoli
    public sequenceBool(String s){

        super();
        devideSequence(s);
    }

    private void devideSequence(String seq){
        assert(base!=null):"Stringa ricevuta in input è vuota";
        int numPar = calcolaParentesi(seq);
        assert(numPar!=0):"Numero Parentesi nullo";
        //num par deve essere il doppio dei calcoli
        String[] segmenti = segmentArray(seq, numPar);
       //abbiamo array di sequenze



    }
    private int calcolaParentesi(String s){
        int numPar = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                numPar = numPar +1;
            }
            if(s.charAt(i)==')'){
                numPar = numPar+1;
            }

        }
        return numPar;
    }
    private int parentesiAp(String s){
        int numPar = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                numPar = numPar +1;
            }
            

        }
        return numPar;
    }
    private int parentesiCh(String s){
        int numPar = 0;
        for (int i = 0; i < s.length(); i++){
            
            if(s.charAt(i)==')'){
                numPar = numPar+1;
            }

        }
        return numPar;
    }
    private String[] segmentArray(String s, int a){
        
        String[] array = cleanArray.buildStringArr(a/2);
        if(s!= "" && a != 0){
            char[] simboli = new char[a/2];
            int j = 0;
            int k = 0;
            for (int i = 0; i < s.length();i++){
                if(s.charAt(i)=='('){
                    while ( i!= s.length() && s.charAt(i)!= ')'){ //forse problemi con un out of bounds a causa di k
                        //escludendo parentesi
                        if(s.charAt(i)=='('){
                            i++;
                        }
                        else{
                        array[k]  =array[k] + s.charAt(i);
                        i++;
                        }
                     }
                }
                if (s.charAt(i)=='&'){
                    simboli[j]=s.charAt(i);
                    j++;
                }
                if(s.charAt(i)==')'){
                    if(i+1 < s.length()  && s.charAt(i+1)!=')')
                    k++; //cambia k ogni parentesi chiusa __________________________________________(non più)
                }
            }
            setSimboliArr(simboli);
        }
        return array;
    }
    private static void setSimboliArr(char[] c){
        simb = c;
    }
    
    
    public boolean scomponi(String t) {
        String s=removeUselessParentesi(t);
        boolean stop = false;
        int i = 0;
    
        boolean incluso = false;
        boolean base = true;
        for(int o = 0; o < s.length(); o++){
            if(s.charAt(o)=='|' || s.charAt(o) == '&'){
                base = false;
            }
        }
        if(base == true){
            String mid = removePar(s);
            return prova.wrapperBool(mid);
            
        }
        else{
            while (i < s.length()) {
                for (int k = 0; k < s.length(); k++) {
                        if (s.charAt(k) == '|' ) {
                            if(parentesiAp(s.substring(0, k)) == parentesiCh(s.substring(0, k))){
                            return scomponi(s.substring(0, k)) || scomponi(s.substring(k + 1, s.length()));
                            }
                        }
                    
                }
                
        
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(i) == '&' ) {
                        if(parentesiAp(s.substring(0, i)) == parentesiCh(s.substring(0, i))){
                        return scomponi(s.substring(0, i)) && scomponi(s.substring(i + 1, s.length()));
                        }
                    }
                
                }
                // If...Then considered mostly useless by programmers, maybe will be implemented in a future time

        
                i++;
            }
        }
    
        
        return false;

        
    }
    private String removePar(String s){
        String out="";
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i)!= '(' && s.charAt(i)!= ')'){
                out = out + s.charAt(i);
            }
        }
        return out;
    }
    private String removeUselessParentesi(String s){
        
        boolean valid = checkPar(s);
        
        

        if(s.equals('('+s.substring(1, s.length()-1)+')') ){
            String sNew = removeUselessParentesi(s.substring(1, s.length()-1));
            if (checkPar(sNew)==false){
                return s;
            }
            return sNew;
        }
        else{
            return s;
             
        
        
        } 
    }
    public boolean checkPar(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='('){
                count ++;

            }
            else if (s.charAt(i)==')'){
                count--;
                if (count < 0){
                    return false;
                }
            }
        }
        if(count != 0){
            return false;
        }
        return true;
    }
}


// Ogni parentesi aperta ha una parentesi chiusa
// (5>3)
// ((5>3)&(3<2))
// ((5>3)|(3<2))
// ((5>3)|(3<2)|(1<5))
// Approccio ricorsivo

// ((5<3)|(3<2)&(1>5))


// (5<3)&(3<2)&(1>5)

// (5<3)&((3<2)&(1>5))


// FORMULA0 OR FORMULA1 OR FORMULA2
// (FORMULA0) OR ((FORMULA1) OR (FORMULA2))

// A AND B OR C AND D OR E OR F
// (A AND B) OR (C AND D OR E OR F)
// \---R1--/    ((C AND D) OR (E OR F))
//               \--R2---/    (E) OR (F)
//                (C) AND (D)
// (A) AND (B)

// (A OR B) AND C
// |------|
// (A
// B)

// Una formula può essere:

// Extra: se hai distaccato e contiene delle parentesi all'inizio e alla fine, toglile

// 0. (formula)
// 1. formula OR formula
// 2. formula AND formula
// 3. qualcosa>qualcosa
// 3. qualcosa<qualcosa
// 3. qualcosa=qualcosa


