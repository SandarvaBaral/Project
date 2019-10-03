/*
    Sandarva Baral
    03/10/2019

 */
public class MegaBytesConverter {
    public static void main(String[] args) {

        printMegaBytesAndKiloBytes(0);

    }
    /*
        XX = Kilobytes
        YY = megabytes
        ZZ = remaining kilobytes %

        XX KB = YY MB and ZZ KB

        1 MB = 1024 kB
     */
    public static void printMegaBytesAndKiloBytes(int kiloBytes) {

        if(kiloBytes < 0) {
            System.out.println("Invalid Value");
        }else if( kiloBytes >= 0) {
            long result = kiloBytes / 1024;
            long remainder = kiloBytes % 1024;
            System.out.println(kiloBytes + " KB = " + result + " MB and " + remainder + " KB" );
        }
    }
}
