package service.util;

public class CodeConfirmGenerator {
    public static String generateCode(){
        StringBuilder code= new StringBuilder();
        for (int i = 0;i < 6;i++){
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }
}
