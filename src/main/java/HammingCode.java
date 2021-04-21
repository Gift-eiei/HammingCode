public class HammingCode {

    public String[] Hamming_gen(String dataWord) {

        int countBit = 0;
        //count number of redundancy bit
        for (int i = 0; i < dataWord.length() ; i++) {
            countBit++;
            if (Math.pow(2, i) > dataWord.length())
                break;
        }

        //merge bit with redundancy bit
        int newLength = dataWord.length() + countBit;
        String[] mergeBit = new String[newLength];
        int pos = 0;
        int a = 0;
        int b = dataWord.length()-1;
        while (dataWord.length() + countBit > pos && b>= 0) {
            if (Math.pow(2, a) == pos + 1) {
                mergeBit[pos] = "r";
                a++;
            } else {
                mergeBit[pos] = String.valueOf(dataWord.charAt(b));
                b--;
            }
                pos++;
        }

            //set a parity bit
            int position = 0;

            while (position < mergeBit.length){

                //for position of parity bit
                if(mergeBit[position].equals("r")){

                    int parityOne = 0; //count only bit one
                    int trackPos = 0; //track position of each sub-array of bit
                    int subPos = position;//position of selected bit for each sub-array
                    int trackCount = 0;//track number of bit for each interval following parity bit position

                    while (trackPos < mergeBit.length - position){
                            if(trackCount == position+1){
                                trackCount = 0;
                                subPos += position;
                            }
                            else{
                                if(subPos < mergeBit.length)
                                    if(mergeBit[subPos].equals("1"))
                                        parityOne++;
                                trackCount++;
                            }

                        subPos++;
                        trackPos++;

                    }

                    mergeBit[position] = parityOne % 2 == 0 ? String.valueOf('0') : String.valueOf('1');
                }
                position++;
            }

            //reverse position
            String[] codeWord = new String[mergeBit.length];
            int reversePos = 0;

            for(int i = mergeBit.length-1 ; i>=0 ; i--){
                codeWord[reversePos] = mergeBit[i];
                reversePos++;
            }

        return codeWord;
    }

    public String[] Hamming_check(String[] codeWord){

        int countBit = 0;
        //count number of redundancy bit
        for (int i = 0; i < codeWord.length ; i++) {
            if (Math.pow(2, i) > codeWord.length)
                break;
            countBit++;
        }

        //reverse position
        String[] NewCodeWord = new String[codeWord.length];
        int reversePos = 0;

        for(int i = codeWord.length-1 ; i>=0 ; i--){
            NewCodeWord[reversePos] = codeWord[i];
            reversePos++;
        }

        //set a parity bit
        int position = 0;

        int prev = 2;

        int errPos = 0;
        String[] error_pos = new String[countBit];

        while (position < NewCodeWord.length){

            //for position of parity bit

            if( position+1 == 1 || position+1 == 2 || position+1 == prev*2 ){

                if(position+1 != 1 || position+1 != 2){
                    prev = position+1;
                }
                int parityOne = 0; //count only bit one
                int trackPos = 0; //track position of each sub-array of bit
                int subPos = position;//position of selected bit for each sub-array
                int trackCount = 0;//track number of bit for each interval following parity bit position

                while (trackPos < NewCodeWord.length - position){
                    if(trackCount == position+1){
                        trackCount = 0;
                        subPos += position;
                    }
                    else{
                        if(subPos < NewCodeWord.length)
                            if(NewCodeWord[subPos].equals("1"))
                                parityOne++;
                        trackCount++;
                    }

                    subPos++;
                    trackPos++;

                }
                error_pos[errPos++] = parityOne % 2 == 0 ? String.valueOf('0') : String.valueOf('1');

            }
            position++;
        }

        //check whether it is error bit or not
        int errCount = 0;
        for(String i : error_pos){
            if(i.equals("0"))
                errCount++;
        }

        return errCount == countBit ? error_pos : new String[]{"-1"};
    }
}
