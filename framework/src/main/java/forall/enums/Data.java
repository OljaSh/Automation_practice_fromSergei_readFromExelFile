package forall.enums;

import static forall.utils.PropertiesUtils.*;


public enum Data {
    MAIN(getStringValue(Constants.MAIN_DATA_SOURCE));

    private String path;

    Data(final String path) {
        this.path = path;
    }

    public String toString() {
        return path;
    }
}
