package service.cssEditor;

import javax.servlet.http.HttpServletRequest;

public class CssEditor {

    public static void pressedButton(String carType, HttpServletRequest req){
        req.setAttribute(carType+"Form", "background-color: grey;\n" +
                "  border-top: 4px solid blue;");
        req.setAttribute(carType+"Button", "background-color: grey;");
    }

}
