public class calcSegmento {
    public calcSegmento(String s){
        int val=numSkip(s);
        String segm = segmento(s);

    }
    public int numSkip(String s){
        int val=0;
        for (int i = 0; i<s.length(); i++){
            if(s.charAt(i)=='('){
                if(s.charAt(i+1)!='('){
                    // nel caso non ci sia parentesi subito attaccata
                    for(int k=0; k < s.length();k++){
                        if(s.charAt(k)==')'){
                            return val;
                        }
                        else{
                            val = val +1;
                        }
                    }
                    
                }
            }
        }
        return val;
    }
    private String segmento(String s){
        String segm = "";
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='('){
                while (s.charAt(i)!= ')'&& i!= s.length()){ //invertire condizioni
                    segm  =segm + s.charAt(i);
                    i++;
                }
            }
        }
        return segm;
    }
    
}
