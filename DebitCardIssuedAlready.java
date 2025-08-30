public class DebitCardIssuedAlready extends RuntimeException {
    DebitCardIssuedAlready(String desc) {
        super(desc);
    }
}
