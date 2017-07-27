package maiboroda.o.gv_softtest.util;

import io.realm.RealmObject;

public class RealmString extends RealmObject {
    private String string;

    public RealmString(String string) {
        this.string = string;
    }

    public RealmString() {
    }

    public String getString() {
        return string;
    }
}
