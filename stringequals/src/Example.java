/**
 * Code used to assist with answering question:
 * 
 * http://stackoverflow.com/questions/20650452/why-does-sometimes-work-with-string-trim/20650830#20650830
 * 
 * @author Steven Doolan
 */
public class Example {
    public static void main(String... args) {
        String s = "abc";
        System.out.println("s.hashCode=" + s.hashCode());
        String s1 = " abc ";
        System.out.println("s1.hashCode=" + s1.hashCode());
        String s2 = s.trim();
        System.out.println("s2.hashCode=" + s2.hashCode());
        String s3 = s1.trim();
        System.out.println("s3.hashCode=" + s3.hashCode());
        System.out.println();
        System.out.println(s == s1);
        System.out.println(s == s2);
        System.out.println(s == s3);
        System.out.println(s.equals(s3));
    }
}
