package ch.supsi.myproj.stringpresentation;

public class LowerCasePresentation implements Presentable<String> {

    @Override
    public String present(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("string cannot be null nor empty");
        }

        return str.toLowerCase();
    }
}
