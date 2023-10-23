package ch.supsi.myproj.stringpresentation;

public class CapsPresentation implements Presentable<String> {

    @Override
    public String present(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("string cannot be null nor empty");
        }

        String[] arr = str.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
        }

        return sb.toString().trim();
    }
}
