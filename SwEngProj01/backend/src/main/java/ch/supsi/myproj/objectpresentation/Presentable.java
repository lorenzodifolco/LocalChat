package ch.supsi.myproj.objectpresentation;

public interface Presentable<ModelObject> {

    <T extends ModelObject> String present(T modelObject);

}
