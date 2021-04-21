import java.util.Arrays;

public class HammingCodeApp {
    public static void main(String[] args){
        HammingCode hammingCode = new HammingCode();

        String dataWord = "1001101";

        String[] codeWord = hammingCode.Hamming_gen(dataWord);

        System.out.println("Generate Hamming Code" + "\n" + "====================" );
        System.out.println(Arrays.toString(codeWord)+ "\n");

        System.out.println("Create Hamming Checking with Return -1 for no error found" + "\n" + "====================");
        String[] error_pos = hammingCode.Hamming_check(codeWord);

        System.out.println(Arrays.toString(error_pos)+ "\n");

        String[] codeWordTest = new String[]{"1","0"," 0", "1", "0", "1", "0", "0", "1", "0", "1"};

        System.out.println("Create Hamming Checking with Return error position" + "\n" + "====================");
        System.out.println(Arrays.toString(hammingCode.Hamming_check(codeWordTest))+ "\n");
    }
}
