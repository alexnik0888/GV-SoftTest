package maiboroda.o.gv_softtest.data;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import maiboroda.o.gv_softtest.util.RealmString;

public class Task extends RealmObject {
    private String title;
    private String description;
    private RealmList<RealmString> urlArray;
    private String responsible;
    private String solveUntil;
    private String registered;
    private String created;
    private String state;

    public Task(String title, String description, List<String> urlArray, String responsible, String solveUntil, String registered, String created, String state) {
        this.title = title;
        this.description = description;
        this.urlArray = new RealmList<>();
        for (String s : urlArray) {
            this.urlArray.add(new RealmString(s));
        }
        this.responsible = responsible;
        this.solveUntil = solveUntil;
        this.registered = registered;
        this.created = created;
        this.state = state;
    }

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getUrlArray() {
        List<String> list = new ArrayList<>();
        for (RealmString string : urlArray) {
            list.add(string.getString());
        }
        return list;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getSolveUntil() {
        return solveUntil;
    }

    public String getRegistered() {
        return registered;
    }

    public String getCreated() {
        return created;
    }

    public String getState() {
        return state;
    }
}
