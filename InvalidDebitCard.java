public class InvalidDebitCard extends RuntimeException{
    InvalidDebitCard(String desc){
        super(desc);
    }
}
