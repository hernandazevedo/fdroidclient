import android.util.Log;

import org.apache.commons.net.util.Base64;
import org.fdroid.fdroid.Hasher;
import org.fdroid.fdroid.Utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Formatter;

/**
 * Created by pos on 9/15/17.
 */

public class Teste {


    public static void main(String args[]){
//        String myRepoFingerPrint = "19FEBE0ED4B9D8FCC91271C7182FB9E510EF57749F56B07E1EE1C1BDAFC5A2AB";
        String myPubKey = "308204ed308202d5a0030201020204151a52cc300d06092a864886f70d01010b050030273110300e060355040b1307462d44726f6964311330110603550403130a70696c657175696e686f301e170d3137303931353134303832365a170d3435303133313134303832365a30273110300e060355040b1307462d44726f6964311330110603550403130a70696c657175696e686f30820222300d06092a864886f70d01010105000382020f003082020a0282020100aef5381f6182dd5b350fff705c7fa711b7a22d4ec51994b2865fd075259e2b86cdad55e29a6c05c1b21a4bf85f240605a6ebda0aba112edf86a32597b6cb3d78bc9073a3bab622c91a21878adc193cf2e72f02d6d16e2fd4eb8f8cfca42d37ddf0ec4c38c9e93391c988457abbefcdb0a73ff00d9b7f5c8effd3c22625f9a41ecbd0dbff1d416a7b92f1f6bc7cd46f73388aa29ed2b1db246569840468fa8940b0c008059ff99d0758890cb776426a8b8384f46883bca43c0acf4ca6572de0fa48a2428dc9d678c181e408ea2da90bdee329e865e6d5da49436c1a302e0249f843c1f75135f9afe3faff003e532c23a4d9587ce9b18edf265c8685d539639241d98c81e7555cd0b0e209e1bf556f6ba3e1ac1bb97027972bf6e1487b0484b2898a07961a91c71a4a8766b9b0b75627783972a74507bd057b0e5a9db5b8324d1cc3ecfde867ad84394a1076f5debc9cfeb67bf2c806fe27b08b6f5e0dfb655d4820c92efc0ffa0587c24485f0501962e52038deb5c63f0a08fdc77a836786626f3ba0dbd35e414b42a7afe932cd31a0793988f838eefff8801320a638514a656ba387bcf76b500b9ea258c60c24c6af5b1c358aba0a28eabf21f1a332ffa2604f45533f651cc8d78107ad171fdeaec0ccf4540f5551816a9b8a901c3490cd5ded1ea806de5374c1dc807188541328e729b19250f8f2b5bc6e252a6fb9eaf831190203010001a321301f301d0603551d0e04160414dee3282b8c79499e6443b150b9054a542d4d81bf300d06092a864886f70d01010b050003820201004eb4434188952290640591cd7c140e4ff1d22759b87cd08988b117b8ff37c59eeccc130e4a0ce962baeba75768d8bbbc5ffb6eb2234ade066ca52095e9a01632138fc5a5d4665c601b17f535cc3c53da61f5fa45307971b99caf05e7bd4f2966aa69b69da6d8f60e4313233ee9a35ce6538385fca9d33a1ee2c7fe451bc46d661e722a9fd97971bbfcf4a994164fdcf489aa6e51111d66099a91fdef12bc8427fb8814b8ee43e9ce8d5f6aa479327aac213b7d22644a51490263432d4e839526925d7444aa779b35d8659fa6c9c285efd43d569c341dc465f180a37ebc5fd06f42278634e4faf4271c1924ed2ef72b110cf00e88bbcc19a03f4ab74383f98d0d3be70429c6f244a98cb06eded4218a0244f2307dff823bc0569d688c4f15180b279e442a686cba0baf144c0dca404b0a97b71acefe9d51dfc470471406bfc2425d4731f0a3883a99e8a30c87359752ff778eeeb5de642a7722f1e6a90b4118a5af7028b6c80b695c20f8904792e1747a4d98e06c5e50741a013e15c06c6aeeae9c6ef1a5ce77a817582cff2b1a9c3364c16caefb29fc3b84b225341ea6e5410ec8ee0c09e471eb6ed22099927952f9bfa84e0cedf388aa967ad60c5489fa2a043e61d90def94a29074304f09b3a5365d8a0fba0bedb837fd69a0793586c2ce3ff619a293c86f9f646ec79b7b11811b3dc41717dc9e6535b7cbeec14c13b28bf2";

//        String fdriudPubKey = "3082035e30820246a00302010202044c49cd00300d06092a864886f70d01010505003071310b300906035504061302554b3110300e06035504081307556e6b6e6f776e3111300f0603550407130857657468657262793110300e060355040a1307556e6b6e6f776e3110300e060355040b1307556e6b6e6f776e311930170603550403131043696172616e2047756c746e69656b73301e170d3130303732333137313032345a170d3337313230383137313032345a3071310b300906035504061302554b3110300e06035504081307556e6b6e6f776e3111300f0603550407130857657468657262793110300e060355040a1307556e6b6e6f776e3110300e060355040b1307556e6b6e6f776e311930170603550403131043696172616e2047756c746e69656b7330820122300d06092a864886f70d01010105000382010f003082010a028201010096d075e47c014e7822c89fd67f795d23203e2a8843f53ba4e6b1bf5f2fd0e225938267cfcae7fbf4fe596346afbaf4070fdb91f66fbcdf2348a3d92430502824f80517b156fab00809bdc8e631bfa9afd42d9045ab5fd6d28d9e140afc1300917b19b7c6c4df4a494cf1f7cb4a63c80d734265d735af9e4f09455f427aa65a53563f87b336ca2c19d244fcbba617ba0b19e56ed34afe0b253ab91e2fdb1271f1b9e3c3232027ed8862a112f0706e234cf236914b939bcf959821ecb2a6c18057e070de3428046d94b175e1d89bd795e535499a091f5bc65a79d539a8d43891ec504058acb28c08393b5718b57600a211e803f4a634e5c57f25b9b8c4422c6fd90203010001300d06092a864886f70d0101050500038201010008e4ef699e9807677ff56753da73efb2390d5ae2c17e4db691d5df7a7b60fc071ae509c5414be7d5da74df2811e83d3668c4a0b1abc84b9fa7d96b4cdf30bba68517ad2a93e233b042972ac0553a4801c9ebe07bf57ebe9a3b3d6d663965260e50f3b8f46db0531761e60340a2bddc3426098397fda54044a17e5244549f9869b460ca5e6e216b6f6a2db0580b480ca2afe6ec6b46eedacfa4aa45038809ece0c5978653d6c85f678e7f5a2156d1bedd8117751e64a4b0dcd140f3040b021821a8d93aed8d01ba36db6c82372211fed714d9a32607038cdfd565bd529ffc637212aaa2c224ef22b603eccefb5bf1e085c191d4b24fe742b17ab3f55d4e6f05ef";
        System.out.println(calcFingerprint(unhex(myPubKey)));

        try {
            X509Certificate certificate = loadCertificate(CertificateFactory.getInstance("X509"), new File("/home/pos/Downloads/example.cer"));
            String hex =  Hasher.hex(certificate);
            System.out.println(hex);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static X509Certificate loadCertificate(CertificateFactory cf,File f) throws CertificateException, IOException {
        FileInputStream in=new FileInputStream(f);
        try {
            X509Certificate c=(X509Certificate)cf.generateCertificate(in);
            c.checkValidity();
            return c;
        }
        finally {
            in.close();
        }
    }


    private static String hex(byte[] sig) {
        byte[] csig = new byte[sig.length * 2];
        for (int j = 0; j < sig.length; j++) {
            byte v = sig[j];
            int d = (v >> 4) & 0xf;
            csig[j * 2] = (byte) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            d = v & 0xf;
            csig[j * 2 + 1] = (byte) (d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return new String(csig);
    }

    private static String calcFingerprint(byte[] key) {
        if (key == null) {
            return null;
        }
        if (key.length < 256) {
            return null;
        }
        String ret = null;
        try {
            // keytool -list -v gives you the SHA-256 fingerprint
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(key);
            byte[] fingerprint = digest.digest();
            Formatter formatter = new Formatter(new StringBuilder());
            for (byte aFingerprint : fingerprint) {
                formatter.format("%02X", aFingerprint);
            }
            ret = formatter.toString();
            formatter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static byte[] unhex(String data) {
        byte[] rawdata = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i++) {
            char halfbyte = data.charAt(i);
            int value;
            if ('0' <= halfbyte && halfbyte <= '9') {
                value = halfbyte - '0';
            } else if ('a' <= halfbyte && halfbyte <= 'f') {
                value = halfbyte - 'a' + 10;
            } else if ('A' <= halfbyte && halfbyte <= 'F') {
                value = halfbyte - 'A' + 10;
            } else {
                throw new IllegalArgumentException("Bad hex digit");
            }
            rawdata[i / 2] += (byte) (i % 2 == 0 ? value << 4 : value);
        }
        return rawdata;
    }

}
